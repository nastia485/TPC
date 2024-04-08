public class Counter {

    private int counter;

    public Counter(int c) {
        this.counter = c;
    }

    public  void increment() {
        setCounter(counter+1);
    }

    public  void decrement() {
        setCounter(counter-1);
    }

    public  int getCounter() {
        return counter;

    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
