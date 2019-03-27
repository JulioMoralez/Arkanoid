package pack.Arkanoid;

import java.util.Random;

public class Ball extends MyObject {

    private double onBatX;
    private double onBatY;
    private Bat bat;
    private int state=0;

    public Ball(){
        state=1;
        size=20;
        speed=5;
        posX=-100;
        posY=-100;
        Random random = new Random();
        dX=-speed*Math.cos(random.nextDouble());
        dY=-speed*Math.sin(random.nextDouble());
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public void setBat(Bat bat) {
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
    }

    void moveBall(){
        if (state==0){
            posX=bat.getPosX()+onBatX;
            posY=bat.getPosY()+onBatY;
        }
        if (state==1){
            posX += dX;
            posY += dY;
            if (posX <20){
                dX=Math.abs(dX);
            }
            if (posX+size+20 > Arkanoid.WINDOW_SIZE_W){
                dX=-Math.abs(dX);
            }
            if (posY <40){
                dY=Math.abs(dY);
            }
            if (posY+size >Arkanoid.WINDOW_SIZE_H){
                Arkanoid.balls.remove(this);
            }
        }
    }

    void inversX(){
        dX=-dX;
    }

    void inversY(){

        dY=-dY;
    }
}
