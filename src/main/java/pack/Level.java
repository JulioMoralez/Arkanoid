package pack;

import pack.Arkanoid.Arkanoid;
import pack.Arkanoid.Brick;

import java.util.Random;


public class Level {

    public static int levelHP;
    private Random random = new Random();

    public void create(int level) {
        Arkanoid.bricks.clear();
        Arkanoid.balls.clear();
        Arkanoid.bats.clear();
        int i=0;
        switch (level){
            case 0:
                i=1;
                break;
            case 1:
                Arkanoid.bricks.add(new Brick(++i,100,100,1,1));
                Arkanoid.bricks.add(new Brick(++i,140,100,2,1));
//                Arkanoid.bricks.add(new Brick(++i,180,100,3,1));
//                Arkanoid.bricks.add(new Brick(++i,220,100,2,1));
//                Arkanoid.bricks.add(new Brick(++i,260,100,3,1));
//                Arkanoid.bricks.add(new Brick(++i,300,100,2,1));
//                Arkanoid.bricks.add(new Brick(++i,340,100,3,1));
//                Arkanoid.bricks.add(new Brick(++i,380,100,1,1));
                break;
            case 2:
                Arkanoid.bricks.add(new Brick(++i,100,200,1,1));
                Arkanoid.bricks.add(new Brick(++i,140,300,2,1));
                Arkanoid.bricks.add(new Brick(++i,180,200,3,1));
                Arkanoid.bricks.add(new Brick(++i,220,300,2,1));
                Arkanoid.bricks.add(new Brick(++i,260,200,3,1));
                Arkanoid.bricks.add(new Brick(++i,300,300,2,1));
                Arkanoid.bricks.add(new Brick(++i,340,200,3,1));
                Arkanoid.bricks.add(new Brick(++i,380,300,1,1));
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
