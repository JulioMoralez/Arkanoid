package pack.Arkanoid;

public class Bullet extends MyObject{

    public Bullet(double posX, double posY, BatType type){
        this.posX=posX;
        this.posY=posY;
        speed=10;
        size=20;
        switch (type){
            case DOWN:
                dX=0;
                dY=-speed;
                break;
            case RIGHT:
                dX=-speed;
                dY=0;
                break;
            case UP:
                dX=0;
                dY=speed;
                break;
            case LEFT:
                dX=speed;
                dY=0;
                break;
        }
    }



    public void moveBullet() {

        posX += dX;
        posY += dY;
        if ((posX <0) ||
            (posX + size > Arkanoid.WINDOW_SIZE_W) ||
            (posY <20) ||
            (posY + size >Arkanoid.WINDOW_SIZE_H)){
            Arkanoid.bullets.remove(this);
        }
    }
}
