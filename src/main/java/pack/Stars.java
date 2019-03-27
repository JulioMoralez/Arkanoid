package pack;

//import pack.Arkanoid.Ball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

class Ship{

    private JFrame frame;
    private int posX;
    private int posY;
    private int dX;
    private int dY;

    public Ship(JFrame frame) {
        this.frame=frame;
        posX=300;
        posY=400;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void moveShip(int keyCode){
        switch (keyCode){
            case 65: {
                dX=-1;
                dY=0;
                moveTimer();
                break;
            }
            case 68: {
                dX=1;
                dY=0;
                moveTimer();
                break;
            }
            case 87: {
                dX=0;
                dY=-1;
                moveTimer();
                break;
            }
            case 83: {
                dX=0;
                dY=1;
                moveTimer();
                break;
            }
        }
    }

    private void moveTimer(){
        new Thread(() -> {
            for (int i = 0; i <20 ; i++) {
                posX+=dX;
                posY+=dY;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                frame.repaint();
            }

        }).start();
    }
}


public class Stars extends JFrame {

    Image buffer=null;
    Graphics screen=null;
    Ship ship;
//    Ball bullet;

    final static int NUM_STARS=200;
    final static int WINDOW_SIZE_W=800;
    final static int WINDOW_SIZE_H=600;
    int count_stars=NUM_STARS;

    Point[] points = new Point[NUM_STARS];

    class Point{
        int x;
        int y;
        int w;
        int h;

        Point(int x, int y, int w, int h){
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
        }
    }


    @Override
    public void paint(Graphics g) {
        screen=g;
        g=buffer.getGraphics();
        super.paint(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,WINDOW_SIZE_W,WINDOW_SIZE_H);


//        g.setColor(Color.WHITE);
//        for (int i = 0; i < NUM_STARS; i++) {
//            g.fillOval(points[i].posX,points[i].posY,points[i].w,points[i].h);
//        }

        g.setColor(Color.GREEN);
        g.drawLine(100,500,500,100);
        g.fillOval(100,500,20,20);
        g.fillOval(500,100,20,20);
        g.setColor(Color.YELLOW);
//        g.fillOval((int)bullet.getPosX(),(int)bullet.getPosY(),20,20);

       // g.setXORMode(Color.RED);
        g.setColor(Color.YELLOW);
        g.fillRect(ship.getPosX(), ship.getPosY(),50,50);

        screen.drawImage(buffer,0,0,null);

    }

    Stars(){
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
                ship.moveShip(e.getKeyCode());
//                repaint();
            }
        });
    }

    private void start() {

        ship=new Ship(this);
//        bullet=new Ball();
//        bullet.getDeg(-1);
        Random random = new Random();

        for (int i = 0; i < NUM_STARS; i++) {
            points[i]=new Point(random.nextInt(WINDOW_SIZE_W),random.nextInt(WINDOW_SIZE_H),random.nextInt(3)+2,random.nextInt(3)+2);
        }

        new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                for (int i = 0; i < NUM_STARS; i++){
                    if (i%2==0){
                        points[i].y+=2;
                    }
                    else {
                        points[i].y+=3;
                    }

                    if (points[i].y>WINDOW_SIZE_H){
                        points[i]=new Point(random.nextInt(WINDOW_SIZE_W),0,random.nextInt(3)+2,random.nextInt(3)+2);
                    }
                }
                repaint();
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
//                bullet.moveBullet();

                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }


}
