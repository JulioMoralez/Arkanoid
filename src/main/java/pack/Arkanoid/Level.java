package pack.Arkanoid;



public class Level {

    public static int levelHP;

    public void create(int level) {
        Arkanoid.bricks.clear();
        Arkanoid.balls.clear();
        Arkanoid.bats.clear();
        Arkanoid.bullets.clear();
        Arkanoid.bonuses.clear();
        int i=0;
        switch (level){
            case 0:
                i=1;
                break;
            case 1:
                Arkanoid.bricks.add(new Brick(++i,0,0,2,1));
                Arkanoid.bricks.add(new Brick(++i,1,1,2,1));
                Arkanoid.bricks.add(new Brick(++i,2,2,2,1));
                Arkanoid.bricks.add(new Brick(++i,3,3,2,1));
                Arkanoid.bricks.add(new Brick(++i,4,4,2,1));
                Arkanoid.bricks.add(new Brick(++i,5,5,2,1));
                Arkanoid.bricks.add(new Brick(++i,6,6,2,1));
                Arkanoid.bricks.add(new Brick(++i,7,7,2,1));
                Arkanoid.bricks.add(new Brick(++i,8,8,2,1));
                Arkanoid.bricks.add(new Brick(++i,9,9,2,1));
                Arkanoid.bricks.add(new Brick(++i,10,10,2,1));
                Arkanoid.bricks.add(new Brick(++i,11,11,2,1));
                Arkanoid.bricks.add(new Brick(++i,12,12,2,1));
                Arkanoid.bricks.add(new Brick(++i,13,13,2,1));
                break;
            case 2:
                Arkanoid.bricks.add(new Brick(++i,2,2,1,1));
                break;
            case 3:
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k <19; k++) {
                        Arkanoid.bricks.add(new Brick(++i, 20+k*40,100+j*20,k%2==0?1:2,1));
                    }
                }
                break;
            case 4:
                break;
            case 5:
                break;
        }
        levelHP=i;
    }
}
