package pack.Arkanoid;

import static pack.Arkanoid.Arkanoid.bonuses;

public class Bonus extends MyObject{

    private BonusType bonusType;

    public BonusType getBonusType() {
        return bonusType;
    }

    public void setBonusType(BonusType bonusType) {
        this.bonusType = bonusType;
    }

    private Bonus (double posX, double posY){
        this.posX=posX;
        this.posY=posY;
        dX=0.0;
        dY=2.0;
        bonusType=BonusType.values()[random.nextInt(BonusType.values().length)];
//        bonusType=BonusType.BREAK;

    }

    public static void createBonus(Brick brick){
        Bonus bonus = new Bonus(brick.getPosX(), brick.getPosY());
        bonuses.add(bonus);
    }

    public void moveBonus() {
        posX += dX;
        posY += dY;
        if ((posX <0) ||
                (posX + size > Arkanoid.WINDOW_SIZE_W) ||
                (posY <20) ||
                (posY + size >Arkanoid.WINDOW_SIZE_H)){
            bonuses.remove(this);
        }



    }
}
