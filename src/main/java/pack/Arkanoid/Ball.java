package pack.Arkanoid;

import java.util.Random;

public class Ball {

    public Ball(){
        state=1;
        size=20;
        speed=5.0;
        posX=500;
        posY=200;
        Random random = new Random();
        dx=-speed*Math.cos(random.nextDouble());
        dy=-speed*Math.sin(random.nextDouble());
    }
    private double posX;
    private double posY;
    private double onBatX;
    private double onBatY;



    private int size;
    private Bat bat;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    private int state=0;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }



    private double dx;
    private double dy;


    private double speed;

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setBat(Bat bat) {
        state=0;
        this.bat = bat;
        switch (bat.getType()){
            case 1:
                onBatX=bat.getSize()*0.3;
                onBatY=-size;
                break;
            case 2:
                onBatX=-size;
                onBatY=bat.getSize()*0.3;
                break;
            case 3:
                onBatX=bat.getSize()*0.3;
                onBatY=bat.getFat();
                break;
            case 4:
                onBatX=10;//bat.getFat();
                onBatY=-10;//bat.getSize()*0.3;
                break;
        }

    }

    void getDeg(Bat bat){

            double pos;
            double t=0;
            double batHalfSize=bat.getSize()/2.0;

            switch (bat.getType()){
                case 1:
                    pos=posX-bat.getPosX()+size/2.0;
                    t = ((90*(batHalfSize-pos)/batHalfSize)-90)*Math.PI/-180;
                    break;
                case 2:
                    pos=posY-bat.getPosY()+size/2.0;
                    t = ((-90*(batHalfSize-pos)/batHalfSize))*Math.PI/-180;
                    break;
                case 3:
                    pos=posX-bat.getPosX()+size/2.0;
                    t = ((90*(batHalfSize-pos)/batHalfSize)-90)*Math.PI/180;
                    break;
                case 4:
                    pos=posY-bat.getPosY()+size/2.0;
                    t = ((-90*(batHalfSize-pos)/batHalfSize)-180)*Math.PI/180;
                    break;
    }
            dx=-speed*Math.cos(t);
            dy=-speed*Math.sin(t);

    }

    void moveBall(){
        if (state==0){
            posX=bat.getPosX()+onBatX;
            posY=bat.getPosY()+onBatY;
        }
        if (state==1){
            posX += dx;
            posY += dy;
            if (posX <20){
                dx=Math.abs(dx);
            }
            if (posX+size+20 > Arkanoid.WINDOW_SIZE_W){
                dx=-Math.abs(dx);
            }
            if (posY <40){
                dy=Math.abs(dy);
            }
            if (posY+size >Arkanoid.WINDOW_SIZE_H){
                dy=-Math.abs(dy);
            }
        }
    }

    void inversX(){
        dx=-dx;
    }

    void inversY(){
        dy=-dy;
    }
}
