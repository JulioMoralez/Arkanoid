package pack.Arkanoid;

import pack.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class Arkanoid extends JFrame{

    private Image buffer=null;
    private Graphics screen=null;
    public static List<Ball> balls = new CopyOnWriteArrayList<>();
    public static List<Bat> bats = new CopyOnWriteArrayList<>();
    public static List<Brick> bricks = new CopyOnWriteArrayList<>();
    private Collision collision;



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
            g.fillRect(brick.getPosX(),brick.getPosY(),brick.getW(),brick.getH());
        }


        g.setColor(Color.YELLOW);
        for (Ball ball: balls){
            g.fillOval((int)ball.getPosX(),(int)ball.getPosY(),ball.getSize(),ball.getSize());
        }

        g.setColor(Color.GRAY);
        for (Bat bat: bats){
            switch (bat.getType()){
                case 1:
                case 3:
                    g.fillRect((int)bat.getPosX(),(int)bat.getPosY(),bat.getSize(),bat.getFat());
                    break;
                case 2:
                case 4:
                    g.fillRect((int)bat.getPosX(),(int)bat.getPosY(),bat.getFat(),bat.getSize());
                    break;
            }
        }


        screen.drawImage(buffer,0,0,null);

    }

    public Arkanoid(){
        setLayout(new GridLayout(3,1));
        setSize(new Dimension(WINDOW_SIZE_W, WINDOW_SIZE_H));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start();
        setVisible(true);
        buffer = this.createImage(WINDOW_SIZE_W,WINDOW_SIZE_H);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                for (Bat bat: bats){
                    bat.moveBat(e.getKeyCode());
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

    private void start() {

        bats.add(new Bat(1));
//        bats.add(new Bat(2));
//        bats.add(new Bat(3));
//        bats.add(new Bat(4));

        balls.add(new Ball());
        balls.add(new Ball());
//        balls.add(new Ball());
//        balls.add(new Ball());

        balls.get(0).setBat(bats.get(0));
//        balls.get(1).setBat(bats.get(1));
//        balls.get(2).setBat(bats.get(2));
//        balls.get(3).setBat(bats.get(3));

        bats.get(0).ballAdd(balls.get(0));
//        bats.get(1).ballAdd(balls.get(1));
//        bats.get(2).ballAdd(balls.get(2));
//        bats.get(3).ballAdd(balls.get(3));

        Level level = new Level();


        level.create(1);


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
    }

}
