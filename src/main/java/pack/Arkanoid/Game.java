package pack.Arkanoid;

import pack.Level;

import static pack.Arkanoid.Arkanoid.balls;
import static pack.Arkanoid.Arkanoid.bats;

public class Game {

    private Collision collision;
    private Level level = new Level();
    private int currentLevel;
    private int maxLevel;
    private int hp;

    public Game(){
        maxLevel=2;
        start();
        playGame();
    }

    private void start() {

//        bats.add(new Bat(1));
//        bats.add(new Bat(2));
//        bats.add(new Bat(3));
//        bats.add(new Bat(4));

//        balls.add(new Ball());
        //       balls.add(new Ball());
//        balls.add(new Ball());
//        balls.add(new Ball());

//        balls.get(0).setBat(bats.get(0));
//        balls.get(1).setBat(bats.get(1));
//        balls.get(2).setBat(bats.get(2));
//        balls.get(3).setBat(bats.get(3));

//        bats.get(0).ballAdd(balls.get(0));
//        bats.get(1).ballAdd(balls.get(1));
//        bats.get(2).ballAdd(balls.get(2));
//        bats.get(3).ballAdd(balls.get(3));



        collision = new Collision();


        new Thread(() -> {
            while (true){
                for (Ball ball:balls) {
                    ball.moveBall();
                }
                collision.result();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

    private void playGame() {
        new Thread(() -> {
            currentLevel=0;
            level.create(currentLevel);
            while (currentLevel<=maxLevel){
                level.create(currentLevel);
                newBallOnBat();
                while (Level.levelHP>0){
                    if (balls.size()==0){

                        newBallOnBat();
                    }
                 //   System.out.println(balls.size());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                currentLevel++;
            }
            System.out.println("YOU WIN!");
        }).start();



    }

    private void newBallOnBat() {
        if (currentLevel==0) return;
        bats.clear();
        bats.add(new Bat(1));
        balls.add(new Ball());
        balls.get(0).setBat(bats.get(0));
        bats.get(0).ballAdd(balls.get(0));
    }

    public void newGame(){
        currentLevel=0;
        Level.levelHP=0;
    }
}


