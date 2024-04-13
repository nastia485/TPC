import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) throws IOException {


        Document document = new Document(new File("D:\\TextFolder\\Folder1\\difficultText.txt"));

        String[] words = document.getWords();

        StatisticsCounter statisticsCounter;
        long timeBefore = System.currentTimeMillis();
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {

            statisticsCounter = forkJoinPool.invoke(new StatisticsCounterTask(words, 0, words.length));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long timeAfter = System.currentTimeMillis();

        System.out.println("It took "+ (timeAfter-timeBefore) + " milliseconds");
        System.out.println("The number of words: "+ words.length);

        System.out.println(statisticsCounter);

    }
}