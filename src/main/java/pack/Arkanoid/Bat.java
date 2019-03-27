package pack.Arkanoid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pack.Arkanoid.Arkanoid.*;


public class Bat extends MyObject{

    private BatType type;

    public boolean isShootReady() {
        return shootReady;
    }

    private boolean shootReady=true;
    private int shootReload;

    public double getShootReloadSize() {
        return shootReloadSize;
    }

    private double shootReloadSize;


    private int fat;

    private Set<Integer> startKeyCode = new HashSet<>();
    private List<Ball> balls = new ArrayList<>();

    public void ballAdd(Ball ball){
        balls.add(ball);
    }

    Bat(BatType type){
        size=200;
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
               posY = (WINDOW_SIZE_H - size) / 2.0;;
               break;
            case UP:
               posX = (WINDOW_SIZE_W - size) / 2.0;
               posY = 30;
               break;
            case LEFT:
               posX = 10;
               posY = (WINDOW_SIZE_H - size) / 2.0;;
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
                    dX = -1;
                    dY = 0;
                    startKeyCode.add(keyCode);
                    startKeyCode.remove(68);
                    moveTimer(keyCode);
                }
                break;
            }
            case 68: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.DOWN) || (type==BatType.UP))) {
                    dX = 1;
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
                    dY = -1;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 83: {
                if ((!startKeyCode.contains(keyCode)) && ((type==BatType.LEFT) || (type==BatType.RIGHT))) {
                    dX = 0;
                    dY = 1;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 70:{
               // if (type==1){
                    for (Ball ball: balls) {
                        ball.setState(1);
                        ball.getDeg(this);
                    }
             //   }
                break;
            }
            case 32:
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
                    System.out.println(shootReloadSize);
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
            while (startKeyCode.contains(keyCode)){
                x=posX+dX;
                y=posY+dY;
                if ((x>=20) && (x<=WINDOW_SIZE_W-size-20)){
                    posX=x;
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
}
