import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        int[] importantInfo = generateArray(1000);
        Random random = new Random();

        for (int i = 0; i < importantInfo.length; i++) {
            drop.put(importantInfo[i]);
            try {
                Thread.sleep(random.nextInt(5));
            } catch (InterruptedException e) {
            }
        }
        drop.put(null);
    }

    private int[] generateArray(int num) {
        int[] array = new int[num];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(num);
        }
        return array;
    }
}
