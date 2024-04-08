public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        b = ball;
    }

    @Override
    public void run() {
        try {
            while (!b.isCaught()) {
                b.move();
                Thread.sleep(10);
            }
            b.getCanvas().deleteBall(b);
            if (b.getCanvas().getBalls().size() == 0) {
                b.getCanvas().repaint();
            }

        } catch (InterruptedException ex) {
        }
    }
}