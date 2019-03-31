package pack.Arkanoid;

import java.util.Random;

public class Ball extends MyObject {
    public double getOnBatX() {
        return onBatX;
    }

    public void setOnBatX(double onBatX) {
        this.onBatX = onBatX;
    }

    public double getOnBatY() {
        return onBatY;
    }

    public void setOnBatY(double onBatY) {
        this.onBatY = onBatY;
    }

    private double onBatX;
    private double onBatY;
    private Bat bat;
    private int state;

    private double oldPosX;
    private double oldPosY;

    public double getOldPosX() {
        return oldPosX;
    }

    public void setOldPosX(double oldPosX) {
        this.oldPosX = oldPosX;
    }

    public double getOldPosY() {
        return oldPosY;
    }

    public void setOldPosY(double oldPosY) {
        this.oldPosY = oldPosY;
    }

    public double getDefaultSpeedDX() {
        return defaultSpeedDX;
    }

    public void setDefaultSpeedDX(double defaultSpeedDX) {
        this.defaultSpeedDX = defaultSpeedDX;
    }

    public double getDefaultSpeedDY() {
        return defaultSpeedDY;
    }

    public void setDefaultSpeedDY(double defaultSpeedDY) {
        this.defaultSpeedDY = defaultSpeedDY;
    }

    private double defaultSpeedDX;
    private double defaultSpeedDY;

    public double getDefaultSpeed() {
        return defaultSpeed;
    }

    private double defaultSpeed;

    public Ball(){
        state=1;
        size=24;
        speed=7;
        defaultSpeed=speed;
        posX=-100;
        posY=-100;
        Random random = new Random();
        int r = random.nextInt(100);
        dX=-speed*Math.cos(r);
        dY=-speed*Math.sin(r);
        defaultSpeedDX=dX;
        defaultSpeedDY=dY;
    }

    public Ball(double posX, double posY){
        this();
        this.posX=posX;
        this.posY=posY;
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void setBat(Bat bat, int shift) {
        state=0;
        this.bat = bat;
        switch (bat.getType()){
            case DOWN:
                onBatX=bat.getSize()*0.3;
                onBatY=-size;
                break;
            case RIGHT:
                onBatX=-size;
                onBatY=bat.getSize()*0.3;
                break;
            case UP:
                onBatX=bat.getSize()*0.3;
                onBatY=bat.getFat();
                break;
            case LEFT:
                onBatX=bat.getFat();
                onBatY=bat.getSize()*0.3;
                break;
        }

    }

    void getDeg(Bat bat){

            double pos;
            double t=0;
            double batHalfSize=bat.getSize()/2.0;

            switch (bat.getType()){
                case DOWN:
                    pos=posX-bat.getPosX()+size/2.0;
                    t = ((90*(batHalfSize-pos)/batHalfSize)-90)*Math.PI/-180;
                    break;
                case RIGHT:
                    pos=posY-bat.getPosY()+size/2.0;
                    t = ((-90*(batHalfSize-pos)/batHalfSize))*Math.PI/-180;
                    break;
                case UP:
                    pos=posX-bat.getPosX()+size/2.0;
                    t = ((90*(batHalfSize-pos)/batHalfSize)-90)*Math.PI/180;
                    break;
                case LEFT:
                    pos=posY-bat.getPosY()+size/2.0;
                    t = ((-90*(batHalfSize-pos)/batHalfSize)-180)*Math.PI/180;
                    break;
    }
            dX=-speed*Math.cos(t);
            dY=-speed*Math.sin(t);
            defaultSpeedDX=-defaultSpeed*Math.cos(t);
            defaultSpeedDY=-defaultSpeed*Math.sin(t);
    }

    void moveBall(){
        oldPosX=posX;
        oldPosY=posY;
        if (state==0){
            posX=bat.getPosX()+onBatX;
            posY=bat.getPosY()+onBatY;
        }
        if (state==1){
            posX += dX;
            posY += dY;
            if (posX <20){
                dX=Math.abs(dX);
                defaultSpeedDX=Math.abs(defaultSpeedDX);
            }
            if (posX+size+20 > Arkanoid.WINDOW_SIZE_W){
                dX=-Math.abs(dX);
                defaultSpeedDX=-Math.abs(defaultSpeedDX);
            }
            if (posY <40){
                dY=Math.abs(dY);
                defaultSpeedDY=Math.abs(defaultSpeedDY);
            }
            if (posY+size >Arkanoid.WINDOW_SIZE_H){
                Arkanoid.balls.remove(this);
            }
        }
    }

    void inversX(){
        dX=-dX;
        defaultSpeedDX=-defaultSpeedDX;
    }

    void inversY(){
        dY=-dY;
        defaultSpeedDY=-defaultSpeedDY;
    }
}
