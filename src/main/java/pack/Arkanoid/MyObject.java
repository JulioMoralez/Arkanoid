package pack.Arkanoid;

import java.util.Random;

public abstract class MyObject {
    protected double posX=-100.0;
    protected double posY=-100.0;
    protected double speed=5.0;
    protected double dX=0.0;
    protected double dY=0.0;
    protected int size=20;
    protected Random random = new Random();

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getdX() {
        return dX;
    }

    public void setdX(double dX) {
        this.dX = dX;
    }

    public double getdY() {
        return dY;
    }

    public void setdY(double dY) {
        this.dY = dY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
