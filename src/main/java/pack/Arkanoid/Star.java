package pack.Arkanoid;

import java.util.Random;

public class Star{

    private int id;
    private int posX;
    private int posY;
    static private Random random = new Random();

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    private int w;
    private int h;

    Star(int id, int posX, int posY, int w, int h){
        this.id=id;
        this.posX=posX;
        this.posY=posY;
        this.w=w;
        this.h=h;
    }

    public static void createStars(){
        for (int i = 0; i < Arkanoid.NUM_STARS; i++) {
            Arkanoid.stars.add(new Star(i,random.nextInt(Arkanoid.WINDOW_SIZE_W),random.nextInt(Arkanoid.WINDOW_SIZE_H),random.nextInt(3)+2,random.nextInt(3)+2));
        }
    }

    public void moveStar() {
        if (id%2==0){
            posY+=2;
        }
        else {
            posY+=1;
        }
        if (posY>Arkanoid.WINDOW_SIZE_H){
            int i=id;
            Arkanoid.stars.remove(this);
            Arkanoid.stars.add(new Star(id,random.nextInt(Arkanoid.WINDOW_SIZE_W),0,random.nextInt(3)+2,random.nextInt(3)+2));
        }
    }
}
