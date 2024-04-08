public class Counter {

    private int counter;

    public Counter(int c) {
        this.counter = c;
    }

    public void increment() {
        counter++;
    }

    public void decrement() {
        counter--;
    }

    public  int getCounter() {
        return counter;

    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
