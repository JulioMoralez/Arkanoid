package pack.Arkanoid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static pack.Arkanoid.Arkanoid.WINDOW_SIZE_W;
import static pack.Arkanoid.Arkanoid.WINDOW_SIZE_H;


public class Bat {

    private int type;
    private double posX;
    private double posY;
    private double dX;
    private double dY;

    private int fat;

    private int size;
    private Set<Integer> startKeyCode = new HashSet<>();
    private List<Ball> balls = new ArrayList<>();

    public void ballAdd(Ball ball){
        balls.add(ball);
    }

    Bat(int type){
        size=200;
        fat=10;
        this.type=type;
        switch (type) {
           case 1:
               posX = (WINDOW_SIZE_W - size) / 2.0;
               posY = WINDOW_SIZE_H-fat-10;
               break;
           case 2:
               posX = WINDOW_SIZE_W-fat-10;
               posY = (WINDOW_SIZE_H - size) / 2.0;;
               break;
           case 3:
               posX = (WINDOW_SIZE_W - size) / 2.0;
               posY = 30;
               break;
           case 4:
               posX = 10;
               posY = (WINDOW_SIZE_H - size) / 2.0;;
               break;
        }
    }
    public int getType() {
        return type;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
                if ((!startKeyCode.contains(keyCode)) && ((type==1) || (type==3))) {
                    dX = -1;
                    dY = 0;
                    startKeyCode.add(keyCode);
                    startKeyCode.remove(68);
                    moveTimer(keyCode);
                }
                break;
            }
            case 68: {
                if ((!startKeyCode.contains(keyCode)) && ((type==1) || (type==3))) {
                    dX = 1;
                    dY = 0;
                    startKeyCode.add(keyCode);
                    startKeyCode.remove(65);
                    moveTimer(keyCode);
                }
                break;
            }
            case 87: {
                if ((!startKeyCode.contains(keyCode)) && ((type==2) || (type==4))) {
                    dX = 0;
                    dY = -1;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 83: {
                if ((!startKeyCode.contains(keyCode)) && ((type==2) || (type==4))) {
                    dX = 0;
                    dY = 1;
                    startKeyCode.add(keyCode);
                    moveTimer(keyCode);
                }
                break;
            }
            case 32:{
               // if (type==1){
                    for (Ball ball: balls) {
                        ball.setState(1);
                        ball.getDeg(this);
                    }
             //   }
            }
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
