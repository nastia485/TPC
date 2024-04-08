import java.util.Random;

public class Consumer implements Runnable {
    private Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (Integer num = drop.take(); num != null; num = drop.take()) {
            System.out.format("NUMBER RECEIVED: %s%n", num);
            try {
                Thread.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
            }
        }
    }
}