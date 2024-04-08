import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonRed = new JButton("Start red");
        JButton buttonBlue = new JButton("Start blue");
        JButton buttonStop = new JButton("Stop");


        buttonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.RED);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.BLUE);
                canvas.add(b);
                BallThread thread = new BallThread(b);
                thread.start();
                System.out.println("Thread name = " +
                        thread.getName());
            }
        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(buttonRed);
        buttonPanel.add(buttonBlue);
        buttonPanel.add(buttonStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void testJoin(){
        Ball bRED = new Ball(canvas, Color.RED);
        Ball bBLUE = new Ball(canvas, Color.BLUE);

        canvas.add(bRED);
        canvas.add(bBLUE);
        BallThread threadRED = new BallThread(bRED);
        BallThread threadBLUE = new BallThread(bBLUE);
        threadBLUE.start();
        try {
            // чекає завершення руху синьої кульки
            threadBLUE.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        threadRED.start();
        try {
            // чекає завершення руху червоної кульки
            threadRED.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}