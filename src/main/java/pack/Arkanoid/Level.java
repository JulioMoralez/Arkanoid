package pack.Arkanoid;

import static pack.Arkanoid.Arkanoid.bricks;

public class Level {

    public static int levelHP;

    public void create(int level) {
        Arkanoid.bricks.clear();
        Arkanoid.balls.clear();
        Arkanoid.bats.clear();
        Arkanoid.bullets.clear();
        Arkanoid.bonuses.clear();
        switch (level){
            case 1:
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 3, 7, 2));
                }
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 4, 4, 1));
                }
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 5, 6, 1));
                }
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 6, 3, 1));
                }
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 7, 5, 1));
                }
                for (int k = 0; k <11; k++) {
                    bricks.add(new Brick(k, 8, 2, 1));
                }
                break;
            case 2:
                for (int k = 0; k <10; k++) {
                    bricks.add(new Brick(0, k+2, 7, 1));
                }
                for (int k = 0; k <9; k++) {
                    bricks.add(new Brick(1, k+3, 6, 1));
                }
                for (int k = 0; k <8; k++) {
                    bricks.add(new Brick(2, k+4, 3, 1));
                }
                for (int k = 0; k <7; k++) {
                    bricks.add(new Brick(3, k+5, 2, 1));
                }
                for (int k = 0; k <6; k++) {
                    bricks.add(new Brick(4, k+6, 5, 1));
                }
                for (int k = 0; k <5; k++) {
                    bricks.add(new Brick(5, k+7, 4, 1));
                }
                for (int k = 0; k <4; k++) {
                    bricks.add(new Brick(6, k+8, 7, 1));
                }
                for (int k = 0; k <3; k++) {
                    bricks.add(new Brick(7, k+9, 6, 1));
                }
                for (int k = 0; k <2; k++) {
                    bricks.add(new Brick(8, k+10, 3, 1));
                }
                for (int k = 0; k <1; k++) {
                    bricks.add(new Brick(9, k+11, 2, 1));
                }
                for (int k = 0; k <10; k++) {
                    bricks.add(new Brick(k, 12, 8, 0));
                }
                bricks.add(new Brick(10, 12, 5, 1));
                break;
            case 3:
                bricks.add(new Brick(9, 11, 5, 1));
                break;
            case 4:
                bricks.add(new Brick(9, 11, 5, 1));
                break;
            case 5:
                break;
        }
        for (Brick brick:bricks){
            if (brick.getType()>0){
                levelHP++;
            }
        }
    }
}
