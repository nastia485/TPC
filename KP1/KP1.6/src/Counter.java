public class Counter {

    private int counter;

    public Counter(int c) {
        this.counter = c;
    }

    public synchronized void increment() {
        counter++;
    }
    public synchronized void decrement() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }
}
