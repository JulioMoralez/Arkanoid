package pack.Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Arkanoid extends JFrame{

    final static int WINDOW_SIZE_W=810;
    final static int WINDOW_SIZE_H=810;
    final static int NUM_STARS=100;
    private final static Font bonusFont = new Font("TimesRoman",Font.BOLD,24);
    private final static Font menuFont = new Font("TimesRoman",Font.BOLD,24);
    private final static Color shadowColor = new Color(0,0,200);

    private Image buffer=null;
    private Graphics screen=null;
    public static List<Ball> balls = new CopyOnWriteArrayList<>();
    public static List<Bat> bats = new CopyOnWriteArrayList<>();
    public static List<Brick> bricks = new CopyOnWriteArrayList<>();
    public static List<Bullet> bullets = new CopyOnWriteArrayList<>();
    public static List<Bonus> bonuses =new CopyOnWriteArrayList<>();

    public static Star[] stars = new Star[NUM_STARS];



    private Game game;
    private Menu menu;


    @Override
    public void paint(Graphics g) {
        screen=g;
        g=buffer.getGraphics();


        g.setColor(Color.BLUE);
        g.fillRect(20,40,WINDOW_SIZE_W-40,WINDOW_SIZE_H-40);
        g.setColor(shadowColor);
        g.fillRect(20,40,WINDOW_SIZE_W-40,20);
        g.fillRect(20,40,20,WINDOW_SIZE_H-40);

        g.setColor(Color.GREEN);
        for (Brick brick:bricks){
            g.setColor(shadowColor);
            g.fillRect((int)brick.getPosX()+16,(int)brick.getPosY()+16,brick.getW(),brick.getH());
            if (brick.getType()==0){
                g.setColor(Color.YELLOW);
                g.fillRect((int)brick.getPosX()-1,(int)brick.getPosY()-1,brick.getW()+2,brick.getH()+2);
            }
            if (brick.isHitAnim()){
                g.setColor(Color.ORANGE);
                g.fillRect((int)brick.getPosX()-1,(int)brick.getPosY()-1,brick.getW()+2,brick.getH()+2);
            }
            g.setColor(brick.getColor());
            g.fillRect((int)brick.getPosX()+1,(int)brick.getPosY()+1,brick.getW()-2,brick.getH()-2);
        }

        if (Game.getGameState()==0){
            g.setColor(Color.WHITE);
            for (Star star:stars){
                g.fillOval(star.getPosX(),star.getPosY(),star.getW(),star.getH());
            }
        }


        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,20,WINDOW_SIZE_W,20);
        g.fillRect(0,20,20,WINDOW_SIZE_H);
        g.fillRect(WINDOW_SIZE_W-20,20,20,WINDOW_SIZE_H);
        if (Game.isBreakLevel()){
            g.setColor(Color.BLUE);
            g.fillRect(0,WINDOW_SIZE_H-40,WINDOW_SIZE_W,40);
        }




        for (Ball ball: balls){
            g.setColor(Color.YELLOW);
            g.fillOval((int)ball.getPosX(),(int)ball.getPosY(),ball.getSize(),ball.getSize());
            g.setColor(Color.DARK_GRAY);
            g.fillOval((int)ball.getPosX()+1,(int)ball.getPosY()+1,ball.getSize()-2,ball.getSize()-2);
            g.setColor(Color.YELLOW);
            g.fillOval((int)ball.getPosX()+ball.getSize()/5,(int)ball.getPosY()+ball.getSize()/5,ball.getSize()/2,ball.getSize()/2);
            g.setColor(Color.DARK_GRAY);
            g.fillOval((int)ball.getPosX()+ball.getSize()/3,(int)ball.getPosY()+ball.getSize()/3,ball.getSize()/2,ball.getSize()/2);
        }

        g.setColor(Color.RED);
        for (Bullet bullet: bullets){
            g.fillOval((int)bullet.getPosX(),(int)bullet.getPosY(),bullet.getSize(),bullet.getSize());
        }

        for (Bonus bonus: bonuses){
            g.setColor(bonus.getBonusType().getColor());
            g.fillRoundRect((int)bonus.getPosX(),(int)bonus.getPosY(),bonus.getSize()*2,bonus.getSize(),bonus.getSize(),bonus.getSize());
            g.setColor(Color.WHITE);
            g.setFont(bonusFont);
            g.setColor(Color.BLACK);
            g.drawString(bonus.getBonusType().getName(),(int)bonus.getPosX()+bonus.getSize()-8,(int)bonus.getPosY()+bonus.getSize()-4);
            g.setColor(Color.WHITE);
            g.drawString(bonus.getBonusType().getName(),(int)bonus.getPosX()+bonus.getSize()-10,(int)bonus.getPosY()+bonus.getSize()-6);
        }

        for (Bat bat: bats){
            if (bat.isMagnit()) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.GRAY);
            }
            switch (bat.getType()){
                case DOWN:
                case UP:
                    g.fillRect((int)bat.getPosX(),(int)bat.getPosY(),bat.getSize(),bat.getFat());
                    g.setColor(Color.RED);
                    g.fillRect((int)bat.getPosX()+bat.getSize()/2-(int)bat.getShootReloadSize()/2,(int)bat.getPosY()+bat.getFat()/2,(int)bat.getShootReloadSize(),bat.getFat()/3);
                    if (bat.isShootReady()){
                        if (bat.getType()==BatType.DOWN){
                            g.fillOval((int)bat.getPosX(),(int)bat.getPosY(),20,20);
                            g.fillOval((int)bat.getPosX()+bat.getSize()-20,(int)bat.getPosY(),20,20);
                        }
                        else {
                            g.fillOval((int)bat.getPosX(),(int)bat.getPosY()-10,20,20);
                            g.fillOval((int)bat.getPosX()+bat.getSize()-20,(int)bat.getPosY()-10,20,20);
                        }

                    }
                    break;
                case LEFT:
                case RIGHT:
                    g.fillRect((int)bat.getPosX(),(int)bat.getPosY(),bat.getFat(),bat.getSize());
                    g.setColor(Color.RED);
                    g.fillRect((int)bat.getPosX()+bat.getFat()/2,(int)bat.getPosY()+bat.getSize()/2-(int)bat.getShootReloadSize()/2,bat.getFat()/3,(int)bat.getShootReloadSize());
                    if (bat.isShootReady()){
                        if (bat.getType()==BatType.LEFT){
                            g.fillOval((int)bat.getPosX()-10,(int)bat.getPosY(),20,20);
                            g.fillOval((int)bat.getPosX()-10,(int)bat.getPosY()+bat.getSize()-20,20,20);
                        }
                        else {
                            g.fillOval((int)bat.getPosX(),(int)bat.getPosY(),20,20);
                            g.fillOval((int)bat.getPosX(),(int)bat.getPosY()+bat.getSize()-20,20,20);
                        }

                    }
                    break;
            }
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(WINDOW_SIZE_W-4,0,200,WINDOW_SIZE_H);
        if (!Menu.isHelp()){
            g.setColor(Color.RED);
            g.setFont(menuFont);
            g.fillRect(WINDOW_SIZE_W+5,167+menu.getCurrentMenuItem()*30,30,8);
            int i=0;
            for (String str:menu.getNameMenuItem()){
                if (i==menu.getCurrentMenuItem()) {
                    g.setColor(Color.GRAY);
                    g.drawString(str, WINDOW_SIZE_W + 41, 181 + i * 30);
                }
                g.setColor(Color.RED);
                g.drawString(str,WINDOW_SIZE_W+40,180+i++*30);
            }


            if (Game.getGameState()!=0){
                g.drawString("Level:",WINDOW_SIZE_W+20,300);
                g.drawString(""+Game.getCurrentLevel(),WINDOW_SIZE_W+20,330);
                g.drawString("Score:",WINDOW_SIZE_W+20,380);
                g.drawString("" + Game.getScore(),WINDOW_SIZE_W+20,410);
                g.drawString("Max: ",WINDOW_SIZE_W+20,460);
                g.drawString("" + Game.getMaxScore(),WINDOW_SIZE_W+20,490);
                g.drawString("Life: " + Game.life,WINDOW_SIZE_W+20,540);


                int sizeBall=24;
                int posXBall=WINDOW_SIZE_W+160;
                int posYBall=520;
                for (int j = 0; j < Game.life; j++) {
                    posXBall += 30;
                    if (j % 6 == 0) {
                        posXBall -= 180;
                        posYBall += 30;
                    }
                    g.setColor(Color.YELLOW);
                    g.fillOval(posXBall, posYBall, sizeBall, sizeBall);
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(posXBall + 1, posYBall + 1, sizeBall - 2, sizeBall - 2);
                    g.setColor(Color.YELLOW);
                    g.fillOval(posXBall + sizeBall / 5, posYBall + sizeBall / 5, sizeBall / 2, sizeBall / 2);
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(posXBall + sizeBall / 3, posYBall + sizeBall / 3, sizeBall / 2, sizeBall / 2);
                }
            }
        }
        else{
            int size=30;
            int posX=WINDOW_SIZE_W+8;
            int posY=150;
            g.setFont(bonusFont);
            g.setColor(Color.RED);
            g.drawString("Bonuses:",posX,posY);
            for (BonusType bonus:BonusType.values()){
                posY+=36;
                g.setColor(bonus.getColor());
                g.fillRoundRect(posX,posY,size*2,size,size,size);
                g.setColor(Color.BLACK);
                g.drawString(bonus.getName(),posX+size-8,posY+size-4);
                g.drawString(bonus.toString(),posX+size+30,posY+size-4);
                g.setColor(Color.WHITE);
                g.drawString(bonus.getName(),posX+size-10,posY+size-6);
            }
            g.setColor(Color.RED);
            posY+=90;
            g.drawString("'w'a's'd' - move",posX,posY);
            posY+=30;
            g.drawString("'Space' - push",posX,posY);
            posY+=30;
            g.drawString("'Shift' - fire",posX,posY);
            posY+=30;
            g.drawString("'Esc' - exit help",posX,posY);

        }

        if (Level.isShowLevel()){
            g.setColor(Color.RED);
            g.drawString("Level: " + Game.getCurrentLevel(),WINDOW_SIZE_H/2-40,WINDOW_SIZE_H/10*8);
        }

        switch (Game.getGameResult()){
            case 1:
                g.setColor(Color.GREEN);
                break;
            case 2:
                g.setColor(Color.RED);
                break;
        }
        g.drawString(Game.getLabelGameResult()[Game.getGameResult()],WINDOW_SIZE_H/2-45,WINDOW_SIZE_H/2);



        screen.drawImage(buffer,0,0,null);

    }

    public Arkanoid(){
        setLayout(new GridLayout(1,1));
        setSize(new Dimension(WINDOW_SIZE_W+200, WINDOW_SIZE_H));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        game = new Game();
        menu = new Menu();

        new Thread(()->{
            while (true){
                repaint();
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        setVisible(true);

        buffer = this.createImage(WINDOW_SIZE_W+200,WINDOW_SIZE_H);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                for (Bat bat: bats){
                    bat.moveBat(e.getKeyCode());
                }
                menu.keyMenu(e.getKeyCode());
                if (e.getKeyCode()==10){
                    switch (menu.getCurrentMenuItem()){
                        case 0:
                            game.newGame();
                            break;
                        case 1:
                            Menu.setHelp(true);
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }
                }
                if (e.getKeyCode()==27) {
                    Menu.setHelp(false);
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                for (Bat bat:bats){
                    bat.stopKeyCode(e.getKeyCode());
                }

            }
        });
    }



}
