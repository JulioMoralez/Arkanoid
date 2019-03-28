package pack.Arkanoid;


public class Collision{

    public void result() {
        double X;
        double Y;
        for (Ball ball:Arkanoid.balls){
            X=ball.getPosX()+ball.getSize()/2.0;
            Y=ball.getPosY()+ball.getSize()/2.0;
            for (Bat bat:Arkanoid.bats){
                if (    (   (bat.getType()==BatType.DOWN) &&
                            (X>bat.getPosX()) &&
                            (X<bat.getPosX()+bat.getSize()) &&
                            (Y+ball.getSize()/2.0>bat.getPosY())) ||
                        (   (bat.getType()==BatType.RIGHT) &&
                            (Y>bat.getPosY()) &&
                            (Y<bat.getPosY()+bat.getSize()) &&
                            (X+ball.getSize()/2.0>bat.getPosX())) ||
                        (   (bat.getType()==BatType.UP) &&
                            (X>bat.getPosX()) &&
                            (X<bat.getPosX()+bat.getSize()) &&
                            (Y-ball.getSize()/2.0<bat.getPosY()+bat.getFat())) ||
                        (   (bat.getType()==BatType.LEFT) &&
                            (Y>bat.getPosY()) &&
                            (Y<bat.getPosY()+bat.getSize()) &&
                            (X-ball.getSize()/2.0<bat.getPosX()+bat.getFat()))  )
                                {
                                    if (bat.isMagnit()){
                                        ball.setState(0);
                                        ball.setBat(bat);
                                        bat.ballAdd(ball);
                                        if ((bat.getType()==BatType.DOWN) || (bat.getType()==BatType.UP)){
                                            ball.setOnBatX(ball.getPosX()-bat.getPosX());
                                        }
                                        if ((bat.getType()==BatType.LEFT) || (bat.getType()==BatType.RIGHT)){
                                            ball.setOnBatY(ball.getPosY()-bat.getPosY());
                                        }
                                    }
                                    else {
                                        ball.getDeg(bat);
                                    }

                                }
            }
            for (Brick brick:Arkanoid.bricks){
               if (    (X>brick.getPosX()) &&
                       (X<brick.getPosX()+brick.getW()) &&
                       (Y>brick.getPosY()) &&
                       (Y<brick.getPosY()+brick.getH())){
                   {
                       brick.hit();
                        double dif1,dif2,dif3,dif4,minx,miny;
                        dif1=Math.abs(X-brick.getPosX());
                        dif2=Math.abs(X-brick.getPosX()-brick.getW());
                        dif3=Math.abs(Y-brick.getPosY());
                        dif4=Math.abs(Y-brick.getPosY()-brick.getH());
                        minx=Math.min(dif1,dif2);
                        miny=Math.min(dif3,dif4);


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
        for (Bullet bullet:Arkanoid.bullets) {
            X = bullet.getPosX() + bullet.getSize()/2.0;
            Y = bullet.getPosY() + bullet.getSize()/2.0;

            for (Brick brick:Arkanoid.bricks) {
                if (    (X > brick.getPosX()) &&
                        (X < brick.getPosX() + brick.getW()) &&
                        (Y > brick.getPosY()) &&
                        (Y < brick.getPosY() + brick.getH())) {
                    {
                        brick.hit();
                        Arkanoid.bullets.remove(bullet);
                    }
                }
            }
        }
        for (Bonus bonus:Arkanoid.bonuses) {
            X = bonus.getPosX() + bonus.getSize()/2.0;
            Y = bonus.getPosY() + bonus.getSize()/2.0;

            for (Bat bat:Arkanoid.bats) {
                if (    (bat.getType()==BatType.DOWN) &&
                        (X > bat.getPosX()) &&
                        (X < bat.getPosX() + bat.getSize()) &&
                        (Y > bat.getPosY())) {
                    {
                        bat.setBonus(bonus);
                        Arkanoid.bonuses.remove(bonus);
                    }
                }
            }
        }

    }
}
