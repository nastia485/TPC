import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class Pocket {
    private static final int XSIZE = 40;
    private static final int YSIZE = 40;

    private int x;
    private int y;

    public Pocket(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.darkGray);
        g2.draw(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public int getWidth() {
        return XSIZE;
    }

    public int getHeight() {
        return YSIZE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
