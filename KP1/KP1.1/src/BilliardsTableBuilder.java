import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class BilliardsTableBuilder {
    private ArrayList<Pocket> pockets;

    public BilliardsTableBuilder() {
    }

    public ArrayList<Pocket> createPockets(Component canvas) {
        pockets = new ArrayList<>();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        System.out.println(width);
        System.out.println(height);
        int pocketSize = 40;
        int shift = pocketSize/2;
        List<Point> points = List.of(
                new Point(0, 0),
                new Point(width / 2 - shift, 0),
                new Point(width-pocketSize, 0),
                new Point(0, height-pocketSize ),
                new Point(width / 2-shift, height -pocketSize),
                new Point(width - pocketSize , height -pocketSize)
        );
        System.out.println(points);

        points.forEach((point -> {
            Pocket pocket = new Pocket(point.x, point.y);
            this.pockets.add(pocket);
        }));
        return pockets;
    }

    public void draw(Graphics2D g2) {
        for (Pocket pocket : pockets) {
            pocket.draw(g2);
        }
    }





}
