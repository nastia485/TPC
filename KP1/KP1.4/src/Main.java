public class Main extends Thread {
    public static void main(String[] args) {

        SymbolThread symbolThread1 = new SymbolThread("-");
        SymbolThread symbolThread2 = new SymbolThread("|");

        symbolThread1.start();
        symbolThread2.start();

    }

}
