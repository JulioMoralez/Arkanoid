package pack.Arkanoid;


import java.util.Random;


public class Level {

    public static int levelHP;
    private Random random = new Random();

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
                for (int j = 0; j < 10; j++) {
                    for (int k = 0; k <15 ; k++) {
                        Arkanoid.bricks.add(new Brick(100+k*40,100+j*20,k%2==0?1:2,1));
                        i++;
                    }
                }
                break;
            case 2:
                Arkanoid.bricks.add(new Brick(100,200,1,1));    i++;
                Arkanoid.bricks.add(new Brick(140,300,2,1));    i++;
                Arkanoid.bricks.add(new Brick(180,200,3,1));    i++;
                Arkanoid.bricks.add(new Brick(220,300,2,1));    i++;
                Arkanoid.bricks.add(new Brick(260,200,3,1));    i++;
                Arkanoid.bricks.add(new Brick(300,300,2,1));    i++;
                Arkanoid.bricks.add(new Brick(340,200,3,1));    i++;
                Arkanoid.bricks.add(new Brick(380,300,1,1));    i++;
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        levelHP=i;
    }
}
