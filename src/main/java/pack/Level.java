package pack;

import pack.Arkanoid.Arkanoid;
import pack.Arkanoid.Brick;

public class Level {



    public void create(int level) {
        Arkanoid.bricks.clear();
        int i=0;
        switch (level){
            case 1:
                Arkanoid.bricks.add(new Brick(++i,100,100,1,1));
                Arkanoid.bricks.add(new Brick(++i,140,100,2,1));
                Arkanoid.bricks.add(new Brick(++i,180,100,3,1));
                Arkanoid.bricks.add(new Brick(++i,220,100,2,1));
                Arkanoid.bricks.add(new Brick(++i,260,100,3,1));
                Arkanoid.bricks.add(new Brick(++i,300,100,2,1));
                Arkanoid.bricks.add(new Brick(++i,340,100,3,1));
                Arkanoid.bricks.add(new Brick(++i,380,100,1,1));
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }

    }
}
