import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();

    int deletedBalls = 0;

    private ArrayList<Pocket> pockets;
    BilliardsTableBuilder builder = new BilliardsTableBuilder();


    public BallCanvas() {
    }

    public ArrayList<Ball> getBalls() {
        return balls;
    }

    public void deleteBall(Ball b) {
        balls.remove(b);
        deletedBalls++;
        System.out.println("Number of trapped balls: " + deletedBalls);
    }

    public void addPockets() {
        pockets = builder.createPockets(this);
    }

    public ArrayList<Pocket> getPockets() {
        return pockets;
    }
       public void add(Ball b) {
        this.balls.add(b);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (pockets != null) {
            builder.draw(g2);
        }
        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}
