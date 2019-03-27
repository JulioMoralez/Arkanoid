package pack.Arkanoid;

import java.awt.*;
import java.util.Objects;


public class Brick {

    private int id;
    private int posX;
    private int posY;
    private int w;
    private int h;
    private int type;
    private int hp;
    private Color color;
    private static Color[] colorList={  new Color(200,0,0),
                                        new Color(50,200,0),
                                        new Color(50,100,100),
                                        new Color(50,0,200)};


    public Brick(int id, int posX, int posY, int color, int type){
        this.id=id;
        this.posX=posX;
        this.posY=posY;
        this.type=type;
        this.w=40;
        this.h=20;
        this.color=colorList[color];
        switch (type){
            case 1:
                hp=1;
                break;
            case 2:
                hp=2;
                break;
            case 3:
                hp=1;
                break;
            case 4:
                hp=1;
                break;
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brick brick = (Brick) o;
        return id == brick.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void hit() {
        if (--hp==0){
            Arkanoid.bricks.remove(this);
        }
    }
}
