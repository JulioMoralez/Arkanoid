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

    private Game game;
    private Menu menu;



    final static int WINDOW_SIZE_W=800;
    final static int WINDOW_SIZE_H=600;

    @Override
    public void paint(Graphics g) {
        screen=g;
        g=buffer.getGraphics();

        g.setColor(Color.GREEN);
        g.fillRect(0,0,WINDOW_SIZE_W,WINDOW_SIZE_H);
        g.setColor(Color.BLACK);
        g.fillRect(20,40,WINDOW_SIZE_W-40,WINDOW_SIZE_H-40);

        g.setColor(Color.GREEN);
        for (Brick brick:bricks){
            g.setColor(brick.getColor());
            g.fillRect((int)brick.getPosX(),(int)brick.getPosY(),brick.getW(),brick.getH());
        }

        g.setColor(Color.YELLOW);
        for (Ball ball: balls){
            g.fillOval((int)ball.getPosX(),(int)ball.getPosY(),ball.getSize(),ball.getSize());
        }

        g.setColor(Color.RED);
        for (Bullet bullet: bullets){
            g.fillOval((int)bullet.getPosX(),(int)bullet.getPosY(),bullet.getSize(),bullet.getSize());
        }

        g.setFont(new Font("TimesRoman",Font.BOLD,30));
        g.drawString("Menu",WINDOW_SIZE_W+50,100);
        int i=0;
        for (String str:menu.getNameMenuItem()){
            g.drawString(str,WINDOW_SIZE_W+50,200+i++*30);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(WINDOW_SIZE_W+10,160,40,120);
        g.setColor(Color.RED);
        g.fillOval(WINDOW_SIZE_W+10,170+menu.getCurrentMenuItem()*30,40,40);

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
