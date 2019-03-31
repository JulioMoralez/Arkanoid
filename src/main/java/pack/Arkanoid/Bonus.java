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

    public Bonus (double posX, double posY){
        this.posX=posX;
        this.posY=posY;
        size=30;
        dX=0.0;
        dY=2.0;
//        bonusType=BonusType.values()[random.nextInt(BonusType.values().length)];
        bonusType=BonusType.PLAYER;

    }

    public static void createBonus(double posX, double posY){
        Bonus bonus = new Bonus(posX, posY);
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
