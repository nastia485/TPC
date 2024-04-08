public class SymbolThread extends Thread {
    private String symbol;
    private static final Object lock = new Object();

    public SymbolThread(String s) {
        this.symbol = s;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            synchronized (lock) {
                System.out.print(symbol);
                try {
                    // сповіщення іншого потоку
                    lock.notify();
                    // очікування сповіщення іншого потоку
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}


