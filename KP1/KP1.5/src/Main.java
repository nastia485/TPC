
public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(0);

        Thread incrThread = new Thread(() ->
        {
            for (int i = 0; i < 92486; i++) {
                counter.decrement();
                System.out.println("Decrementing..."+counter.getCounter());
            }
        }
        );
        Thread decrThread = new Thread(() ->
        {
            for (int i = 0; i < 89913; i++) {
                counter.increment();
                System.out.println("Incrementing..."+counter.getCounter());

            }
        }
        );

        incrThread.start();
        decrThread.start();

//        try {
//            incrThread.join();
//            decrThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(counter.getCounter());
    }


}