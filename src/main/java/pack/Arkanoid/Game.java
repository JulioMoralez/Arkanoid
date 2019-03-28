package pack.Arkanoid;

import static pack.Arkanoid.Arkanoid.*;

public class Game {

    private Collision collision;
    private Level level = new Level();
    private int currentLevel;
    private int maxLevel;



    public static int life;

    public static boolean isBreakLevel() {
        return breakLevel;
    }

    public static void setBreakLevel(boolean breakLevel) {
        Game.breakLevel = breakLevel;
    }

    private static boolean breakLevel;

    public static boolean isQuatro() {
        return quatro;
    }

    public static void setQuatro(boolean quatro) {
        Game.quatro = quatro;
    }

    private static boolean quatro;



    public Game(){
        maxLevel=2;
        life=3;
        start();
        playGame();
    }

    private void start() {
        collision = new Collision();


        new Thread(() -> {
            while (true){
                for (Ball ball:balls) {
                    ball.moveBall();
                }
                for (Bonus bonus:bonuses){
                    bonus.moveBonus();
                }
                for (Bullet bullet:bullets){
                    bullet.moveBullet();
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
        Game.setBreakLevel(false);
        bats.clear();
        quatro=false;
        bats.add(new Bat(BatType.DOWN));
        balls.add(new Ball());
        balls.get(0).setBat(bats.get(0));
        bats.get(0).ballAdd(balls.get(0));
    }

    public void newGame(){
        currentLevel=0;
        life=3;
        Level.levelHP=0;
    }
}


