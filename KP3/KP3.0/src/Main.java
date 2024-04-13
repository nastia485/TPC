import java.io.File;
import java.io.IOException;

public class Main {
    static WordCounter wordCounter = new WordCounter();


    public static void main(String[] args) throws IOException {


        Folder folder = new Folder(new File("D:/TextFolder"));

        String searchedWord = "synchronization";

        countWithForkJoin(folder, searchedWord);

        countWithSingleThread(folder, searchedWord);


    }

    static void countWithForkJoin(Folder folder, String searchedWord) {

        int repeatCount = 4;

        long startTime, stopTime, counts = 0, averTime = 0;

        for (int i = 0; i < repeatCount; i++) {

            startTime = System.currentTimeMillis();

            counts = wordCounter.countOccurrencesInParallel(folder, searchedWord);

            stopTime = System.currentTimeMillis();

            averTime += stopTime - startTime;

        }

        System.out.println(counts +

                " words are fined. Fork / join search took " +

                averTime / repeatCount + "ms");
    }

    static void countWithSingleThread(Folder folder, String searchedWord) {

        int repeatCount = 4;

        long startTime, stopTime, counts = 0, averTime = 0;


        for (int i = 0; i < repeatCount; i++) {

            startTime = System.currentTimeMillis();

            counts = wordCounter.countOccurrencesOnSingleThread(folder, searchedWord);

            stopTime = System.currentTimeMillis();

            averTime += stopTime - startTime;

        }

        System.out.println(counts +

                " words are fined. Single thread search took " +

                averTime / repeatCount + "ms");

    }
}
