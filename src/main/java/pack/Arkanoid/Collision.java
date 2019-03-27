package pack.Arkanoid;


public class Collision{

    public void result() {
        double ballX;
        double ballY;
        for (Ball ball:Arkanoid.balls){
            ballX=ball.getPosX()+ball.getSize()/2.0;
            ballY=ball.getPosY()+ball.getSize()/2.0;
            for (Bat bat:Arkanoid.bats){
                if (    (   (bat.getType()==1) &&
                            (ballX>bat.getPosX()) &&
                            (ballX<bat.getPosX()+bat.getSize()) &&
                            (ballY+ball.getSize()/2.0>bat.getPosY())) ||
                        (   (bat.getType()==2) &&
                            (ballY>bat.getPosY()) &&
                            (ballY<bat.getPosY()+bat.getSize()) &&
                            (ballX+ball.getSize()/2.0>bat.getPosX())) ||
                        (   (bat.getType()==3) &&
                            (ballX>bat.getPosX()) &&
                            (ballX<bat.getPosX()+bat.getSize()) &&
                            (ballY-ball.getSize()/2.0<bat.getPosY()+bat.getFat())) ||
                        (   (bat.getType()==4) &&
                            (ballY>bat.getPosY()) &&
                            (ballY<bat.getPosY()+bat.getSize()) &&
                            (ballX-ball.getSize()/2.0<bat.getPosX()+bat.getFat()))  )
                                {
                                    ball.getDeg(bat);
                                }
            }
            for (Brick brick:Arkanoid.bricks){
               if (    (ballX>brick.getPosX()) &&
                       (ballX<brick.getPosX()+brick.getW()) &&
                       (ballY>brick.getPosY()) &&
                       (ballY<brick.getPosY()+brick.getH())){
                   {
                       brick.hit();
                        double dif1,dif2,dif3,dif4,minx,miny;
                        dif1=Math.abs(ballX-brick.getPosX());
                        dif2=Math.abs(ballX-brick.getPosX()-brick.getW());
                        dif3=Math.abs(ballY-brick.getPosY());
                        dif4=Math.abs(ballY-brick.getPosY()-brick.getH());
                        minx=Math.min(dif1,dif2);
                        miny=Math.min(dif3,dif4);

               //        System.out.println(ballX+ " "+ballY);
                        if (minx<miny){
                            ball.inversX();
                        }
                        else{
                            ball.inversY();
                        }


                   }

               }
            }
        }

    }
}
