public class Counter {

    private int counter;

    public Counter(int c) {
        this.counter = c;
    }

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public synchronized int getCounter() {
        return counter;
    }

}
