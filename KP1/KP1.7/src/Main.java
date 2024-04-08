
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(0);
        Thread incrThread = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
                System.out.println("Incrementing..."+counter.getCounter());
            }
        }
        );
        Thread decrThread = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++) {
                counter.decrement();
                System.out.println("Decrementing..."+counter.getCounter());
            }
        }
        );

        incrThread.start();
        decrThread.start();

        System.out.println(counter.getCounter());
    }


}