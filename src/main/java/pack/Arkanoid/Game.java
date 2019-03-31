package pack.Arkanoid;

import java.io.*;

import static pack.Arkanoid.Arkanoid.*;

public class Game {

    private Collision collision;
    private Level level = new Level();

    public static int getCurrentLevel() {
        return currentLevel;
    }

    private static int currentLevel;
    private int maxLevel;
    private static int score;
    private static int maxScore;

    public static int getMaxScore() {
        return maxScore;
    }

    public static void setMaxScore(int maxScore) {
        Game.maxScore = maxScore;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }

    public static int getGameState() {
        return gameState;
    }

    private static int gameState;


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
        maxLevel=10;
        start();
        loadScore();
        playGame();
    }

    private void start() {
        collision = new Collision();
        Star.createStars();


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
                if (gameState==0){
                    for (Star star:stars){
                        star.moveStar();
                    }
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


            while (true){
                label1:
                switch (gameState){
                    case 0:
                        if (stars==null){
                            Star.createStars();
                        }
                        break;
                    case 1:
                        currentLevel=2;
                        score=0;
                        life=3;
                        Level.levelHP=0;
                        while (currentLevel<=maxLevel){
                            level.create(currentLevel);

                            newBallOnBat();
                            while (Level.levelHP>0){
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                if (balls.size()==0){
                                    newBallOnBat();
                                    life--;
                                }
                                if (life==0){
                                    System.out.println("YOU LOSE");
                                    endGame();
                                    break label1;
                                }
                                if (gameState==0){
                                    System.out.println("RESTART GAME");
                                    endGame();
                                    break label1;
                                }
                            }
                            currentLevel++;
                            score+=5000;
                            saveScore();
                        }
                        System.out.println("YOU WIN!");
                        endGame();
                        break;
                }
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();



    }

    private void saveScore() {
        if (score>maxScore){
            maxScore=score;
            new Thread(()->{
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("score.txt"))){
                    bufferedWriter.write(""+score);
                    bufferedWriter.flush();
                } catch (IOException e) {

                }
            }).start();
        }

    }

    private void endGame() {
        gameState=0;
        life=0;
        currentLevel=0;
        balls.clear();
        bats.clear();
        bricks.clear();
        bullets.clear();
        bonuses.clear();
    }

    private void newBallOnBat() {
        Game.setBreakLevel(false);
        bats.clear();
        quatro=false;
        bats.add(new Bat(BatType.DOWN));
        balls.add(new Ball());
        balls.get(0).setBat(bats.get(0));
        bats.get(0).ballAdd(balls.get(0));
    }

    public void newGame(){
        loadScore();
        if (gameState==0){
            gameState=1;
        }
        else {
            gameState=0;
        }

    }

    private void loadScore() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("score.txt"))){
            maxScore= Integer.parseInt(bufferedReader.readLine());
        } catch (Exception e) {
            maxScore=0;
        }

    }
}


