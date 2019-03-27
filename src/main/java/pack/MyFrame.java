package pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class MyFrame extends JFrame {

    int x;
    int y;
    int xOval=200;
    int yOval=0;
    private int dx=0;
    private int dy=0;
    String text="";
    Image buffer=null;


    @Override
    public void paint(Graphics g) {
        Graphics screen = null;

        screen=g;
        g=buffer.getGraphics();

        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("1",x,y);

        g.setXORMode(Color.RED);
        g.setColor(Color.WHITE);
        g.drawOval(10,20,100,100);
        g.fillOval(xOval,yOval,20,20);

        g.drawString(text,100,400);

        screen.drawImage(buffer,0,0,null);
    }


    MyFrame(){
        setLayout(new GridLayout(3,1));
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        add(new PaintPanel());
//        add(new MyButton(this));
        setVisible(true);

        buffer = this.createImage(500,500);
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                x=e.getX();
                y=e.getY();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode()==65){
                    dx=-10;
                    dy=0;
                }
                if (e.getKeyCode()==68){
                    dx=10;
                    dy=0;
                }
                if (e.getKeyCode()==87){
                    dx=0;
                    dy=-10;
                }
                if (e.getKeyCode()==83){
                    dx=0;
                    dy=10;
                }
                repaint();
            }
        });

        gameStart();
    }

    void gameStart(){
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                xOval+=dx;
                yOval+=dy;
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
    }

}
