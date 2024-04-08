import java.awt.*;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        b = ball;
        if(b.getColor().equals(Color.RED)){
            this.setPriority(MAX_PRIORITY);
        }else this.setPriority(MIN_PRIORITY);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                b.move();
                System.out.println("Thread name = "

                        + Thread.currentThread().getName());

                Thread.sleep(10);
            }

        } catch (InterruptedException ex) {
        }
    }
}