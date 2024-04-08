import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

class Ball {
    private BallCanvas canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;

    public Ball(BallCanvas c) {
        this.canvas = c;
        defineStart();
    }
    private void defineStart(){
        double random = Math.random();

        x = new Random().nextInt(this.canvas.getWidth() / 3) + 60;

        if (random <= 0.25) {
            y = 0;
            return;
        }
        if (random <= 0.5) {
            y = this.canvas.getHeight();
            return;
        }
        x = new Random().nextInt(this.canvas.getWidth() / 3) + this.canvas.getWidth() / 2;
        if (random <= 0.75) {
            y = 0;
        } else {
            y = this.canvas.getHeight();
        }
    }

    public BallCanvas getCanvas() {
        return canvas;
    }

    public static void f() {
        int a = 0;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= this.canvas.getWidth()) {
            x = this.canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= this.canvas.getHeight()) {
            y = this.canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        this.canvas.repaint();
    }

    private Point getBallCenter() {
        int ballRadius = XSIZE / 2;
        return new Point(this.x + ballRadius, this.y + ballRadius);
    }

    //does ball intersect pocket
    public boolean isCaught() {
        ArrayList<Pocket> pockets = canvas.getPockets();
        int pocketDimension = pockets.get(0).getHeight();
        boolean isCaught = false;
        Point center = this.getBallCenter();
        for (Pocket pocket : pockets) {
            if (center.x >= pocket.getX() &&
                    center.x <= pocket.getX() + pocket.getWidth() &&
                    center.y >= pocket.getY() &&
                    center.y <= pocket.getY() + pocket.getHeight()) isCaught = true;
        }
        return isCaught;
    }

}
