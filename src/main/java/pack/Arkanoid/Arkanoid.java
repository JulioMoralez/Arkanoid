package pack.Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Arkanoid extends JFrame{

    private Image buffer=null;
    private Graphics screen=null;
    public static List<Ball> balls = new CopyOnWriteArrayList<>();
    public static List<Bat> bats = new CopyOnWriteArrayList<>();
    public static List<Brick> bricks = new CopyOnWriteArrayList<>();
    public static List<Bullet> bullets = new CopyOnWriteArrayList<>();
    public static List<Bonus> bonuses = new CopyOnWriteArrayList<>();
    public static List<Star> stars = new CopyOnWriteArrayList<>();



    private Game game;
    private Menu menu;


    final static int WINDOW_SIZE_W=600;
    final static int WINDOW_SIZE_H=600;
    final static int NUM_STARS=100;

    @Override
    public void paint(Graphics g) {
        screen=g;
        g=buffer.getGraphics();


        g.setColor(Color.BLUE);
        g.fillRect(20,40,WINDOW_SIZE_W-40,WINDOW_SIZE_H-40);
        g.setColor(new Color(0,0,200));
        g.fillRect(20,40,WINDOW_SIZE_W-40,20);
        g.fillRect(20,40,20,WINDOW_SIZE_H-40);

        g.setColor(Color.GREEN);
        for (Brick brick:bricks){
            g.setColor(new Color(0,0,200));
            g.fillRect((int)brick.getPosX()+16,(int)brick.getPosY()+16,brick.getW(),brick.getH());
            if (brick.getType()==0){
                g.setColor(Color.BLACK);
                g.fillRect((int)brick.getPosX()-1,(int)brick.getPosY()-1,brick.getW()+2,brick.getH()+2);
            }
            if (brick.isHitAnim()){
                g.setColor(Color.ORANGE);
                g.fillRect((int)brick.getPosX()-1,(int)brick.getPosY()-1,brick.getW()+2,brick.getH()+2);
            }
            g.setColor(brick.getColor());
            g.fillRect((int)brick.getPosX()+1,(int)brick.getPosY()+1,brick.getW()-2,brick.getH()-2);
        }

        if (game.getGameState()==0){
            g.setColor(Color.WHITE);
            for (Star star:stars){
                g.fillOval(star.getPosX(),star.getPosY(),star.getW(),star.getH());
            }
        }


        g.setColor(Color.RED);
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
            g.setFont(new Font("TimesRoman",Font.BOLD,20));
            g.drawString(bonus.getBonusType().getName(),(int)bonus.getPosX()+bonus.getSize()-6,(int)bonus.getPosY()+bonus.getSize()-3);
        }

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(WINDOW_SIZE_W-4,0,200,WINDOW_SIZE_H);
        g.setColor(Color.RED);
        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.drawString("Menu",WINDOW_SIZE_W+50,100);
        int i=0;
        for (String str:menu.getNameMenuItem()){
            g.drawString(str,WINDOW_SIZE_W+50,200+i++*30);
        }
        g.setColor(Color.RED);
        g.fillOval(WINDOW_SIZE_W+10,170+menu.getCurrentMenuItem()*30,40,40);
        g.drawString("Life: " + Game.life,WINDOW_SIZE_W+50,300);

        for (Bat bat: bats){
            switch (bat.getType()){
                case DOWN:
                case UP:
                    g.setColor(Color.GRAY);
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
                    g.setColor(Color.GRAY);
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


        screen.drawImage(buffer,0,0,null);

    }

    public Arkanoid(){
        setLayout(new GridLayout(1,1));
        setSize(new Dimension(WINDOW_SIZE_W+200, WINDOW_SIZE_H));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                            break;
                        case 2:
                            System.exit(0);
                            break;
                    }

                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                for (Bat bat: bats){
                    bat.stopKeyCode(e.getKeyCode());
                }
            }
        });
    }



}
