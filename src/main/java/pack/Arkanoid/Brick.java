package pack.Arkanoid;

import java.awt.*;


public class Brick extends MyObject{

    private int id;
    private int w;
    private int h;
    private int type;
    private int hp;
    private Color color;
    private static Color[] colorList={  new Color(10,10,10),
                                        new Color(10,10,200),
                                        new Color(10,200,10),
                                        new Color(10,200,200),
                                        new Color(200,10,10),
                                        new Color(200,10,200),
                                        new Color(200,200,10),
                                        new Color(200,200,200)};

    public boolean isHitAnim() {
        return hitAnim;
    }

    public void setHitAnim(boolean hitAnim) {
        this.hitAnim = hitAnim;
    }

    private boolean hitAnim;



    public Brick(int id, int posX, int posY, int color, int type){
        w=40;
        h=20;
        this.id=id;
        this.posX=20+posX*w;
        this.posY=40+posY*h;
        this.type=type;

        this.color=colorList[color];
        switch (type){
            case 0:
                hp=-1;
                break;
            case 1:
                hp=1;
                break;
            case 2:
                hp=3;
                break;
            case 3:
                hp=3;
                break;
            case 4:
                hp=3;
                break;
        }
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


    public void hit() {
        if (type!=0){
            hp--;
        }
        if (hp==0){
            if (random.nextInt(100)<20){
                Bonus.createBonus(this);
            }
            Level.levelHP--;
            Arkanoid.bricks.remove(this);
        }
        else {
            new Thread(()->{
                hitAnim=true;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hitAnim=false;
            }).start();

        }
    }
}
