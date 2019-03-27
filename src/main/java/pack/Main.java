package pack;

import pack.Arkanoid.Arkanoid;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class PaintPanel extends JPanel{
    Insets ins;
    Random random;

    Image buffer=null;

    PaintPanel(){
        setBorder(BorderFactory.createLineBorder(Color.RED,5));
        random = new Random();
    }

    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);
        int x1,x2,y1,y2;
        int h=getHeight();
        int w=getWidth();
        ins=getInsets();
        for (int i=0; i<10; i++){
            x1=random.nextInt(w-ins.left);
            y1=random.nextInt(h-ins.bottom);
            x2=random.nextInt(w-ins.left);
            y2=random.nextInt(h-ins.bottom);
            g.drawLine(x1,y1,x2,y2);
        }
    }
}

class MyButton extends JButton{

    MyFrame myFrame;

    public MyButton(MyFrame myFrame) {
        this.myFrame = myFrame;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setText(""+myFrame.x);
    }

}




public class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }


    private Main() {
       // new MyFrame();
       // new Stars();
        new Arkanoid();
       // new PaintPanel();
    }
}
