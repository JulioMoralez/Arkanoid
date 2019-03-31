package pack.Arkanoid;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import static pack.Arkanoid.Arkanoid.*;


public class Bat extends MyObject{


    private int defaultSize;
    private BatType type;
    private int modeSpeed;


    public boolean isShootReady() {
        return shootReady;
    }

    private boolean shootReady=true;
    private boolean magnit;
    private int shootReload;

    public double getShootReloadSize() {
        return shootReloadSize;
    }

    private double shootReloadSize;

    private Set<Integer> startKeyCode = new CopyOnWriteArraySet<>();


    public boolean isMagnit() {
        return magnit;
    }

    public void setMagnit(boolean magnit) {
        this.magnit = magnit;
    }

    private int fat;


    private List<Ball> balls = new CopyOnWriteArrayList<>();

    public void ballAdd(Ball ball){
        balls.add(ball);
    }

    Bat(BatType type){
        size=200;
        defaultSize=size;
        shootReloadSize=200;
        fat=10;
        this.type=type;
        switch (type) {
            case DOWN:
               posX = (WINDOW_SIZE_W - size) / 2.0;
               posY = WINDOW_SIZE_H-fat-10;
               break;
            case RIGHT:
               posX = WINDOW_SIZE_W-fat-10;
               posY = (WINDOW_SIZE_H - size) / 2.0;
               break;
            case UP:
               posX = (WINDOW_SIZE_W - size) / 2.0;
               posY = 30;
               break;
            case LEFT:
               posX = 10;
               posY = (WINDOW_SIZE_H - size) / 2.0;
               break;
        }
    }
    public BatType getType() {
        return type;
    }





    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void stopKeyCode(int keyCode) {
        startKeyCode.remove(keyCode);
    }

    public void moveBat(int keyCode) {
        switch (keyCode) {
            case 65: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.DOWN) || (type==BatType.UP))) {
                    dX = -1.6;
                    dY = 0;
                    startKeyCode.add(keyCode);
                    startKeyCode.remove(68);
                    moveTimer(keyCode);
                }
                break;
            }
            case 68: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.DOWN) || (type==BatType.UP))) {
                    dX = 1.6;
                    dY = 0;
                    startKeyCode.add(keyCode);
                    startKeyCode.remove(65);
                    moveTimer(keyCode);
                }
                break;
            }
            case 87: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.LEFT) || (type==BatType.RIGHT))) {
                    dX = 0;
                    dY = -1.6;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 83: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.LEFT) || (type==BatType.RIGHT))) {
                    dX = 0;
                    dY = 1.6;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 32:{
                    for (Ball ball : balls) {
                        ball.setState(1);
                        ball.getDeg(this);
                    balls.clear();
                }
                break;
            }
            case 16:
                shoot();
                break;
        }
    }

    private void shoot() {
        if (shootReady){
            shootReady=false;
            Arkanoid.bullets.add(new Bullet(posX,posY,type));
            switch (type){
                case UP:
                case DOWN:
                    Arkanoid.bullets.add(new Bullet(posX+size-20,posY,type));
                    break;
                case LEFT:
                case RIGHT:
                    Arkanoid.bullets.add(new Bullet(posX,posY+size-20,type));
                    break;
            }
            new Thread(()->{
                shootReload=0;
                double scale=size/30.0;
                while (shootReload<=30){
                    shootReloadSize=shootReload*scale;
                    try {
                        Thread.sleep(16);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    shootReload++;
                }

                shootReady=true;
            }).start();
        }
    }



    private void moveTimer(int keyCode){
        new Thread(() -> {
            double x;
            double y;
            while (startKeyCode.contains(keyCode) && bats.contains(this)){
                x=posX+dX;
                y=posY+dY;
                if ((Game.isBreakLevel()) && (type==BatType.DOWN)){

                    if ((x>=-size+20) && (x<=WINDOW_SIZE_W-20)){
                        posX=x;
                    }
                    else {
                        Game.setBreakLevel(false);
                        Level.levelHP=0;
                    }
                }
                else {
                    if ((x>=20) && (x<=WINDOW_SIZE_W-size-20)){
                        posX=x;
                    }
                }
                if ((y>=40) && (y<=WINDOW_SIZE_H-size-20)){
                    posY=y;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void setBonus(Bonus bonus){
        double speedKoef=1.0;
        double dKoef=1.0;
        if (type!=BatType.DOWN){
            return;
        }
        Game.setScore(Game.getScore()+1000);
        switch (bonus.getBonusType()){
            case EXPAND:
                size=(int)(defaultSize*1.5);
                shootReloadSize=size;
                if (posX+size+20>=WINDOW_SIZE_W){
                    posX=WINDOW_SIZE_W-size-20;
                }
                break;
            case DIVIDE:
                for (Ball ball:this.balls){
                    if (ball.posX+ball.size/2.0>=this.posX+(defaultSize/2.0)){
                        ball.setState(1);
                        ball.getDeg(this);
                    }
                }
                size=(int)(defaultSize/2.0);
                shootReloadSize=size;
                break;

            case LASER:
                shootReady=true;
                break;

            case SLOW:
                if (modeSpeed !=1){
                    if (modeSpeed ==0){
                        speedKoef=0.5;
                        dKoef=speedKoef;
                        modeSpeed =1;
                    }
                    else {
                        speedKoef=0.5;
                        modeSpeed =0;
                    }
                    for (Ball ball:Arkanoid.balls){
                        ball.setSpeed(ball.getSpeed()*speedKoef);
                        ball.setdX(ball.getDefaultSpeedDX()*dKoef);
                        ball.setdY(ball.getDefaultSpeedDY()*dKoef);
                    }
                }
                break;
            case FASTER:
                if (modeSpeed !=2){
                    if (modeSpeed ==0){
                        speedKoef=2.0;
                        dKoef=speedKoef;
                        modeSpeed =2;
                    }
                    else {
                        speedKoef=2.0;
                        modeSpeed =0;
                    }
                    for (Ball ball:Arkanoid.balls){
                        ball.setSpeed(ball.getSpeed()*speedKoef);
                        ball.setdX(ball.getDefaultSpeedDX()*dKoef);
                        ball.setdY(ball.getDefaultSpeedDY()*dKoef);
                    }
                }
                break;
            case BREAK:
                Game.setBreakLevel(true);
                break;

            case CATCH:
                magnit=true;
                break;

            case PLAYER:
                Game.life++;
                break;
            case QUATRO:
                if (!Game.isQuatro()){
                    Game.setQuatro(true);
                    bats.add(new Bat(BatType.LEFT));
                    bats.add(new Bat(BatType.RIGHT));
                    bats.add(new Bat(BatType.UP));
                }

                break;
            case MULTI:
                for (Ball ball:Arkanoid.balls){
                    Arkanoid.balls.add(new Ball(ball.getPosX(),ball.getPosY()));
                    Arkanoid.balls.add(new Ball(ball.getPosX(),ball.getPosY()));
                    Arkanoid.balls.add(new Ball(ball.getPosX(),ball.getPosY()));
                }
                break;

        }
    }


}
