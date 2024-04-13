import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class CommonWordsFinder {

    private final List<String> words1;
    private final List<String> words2;

    public CommonWordsFinder(Document doc1, Document doc2) {
        this.words1 = doc1.getWords();
        this.words2 = doc2.getWords();
    }

    public List<String> findCommonWords() {
        List<String> commonWords = new ArrayList<>();

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        for (String wordToFind : words1) {
            CheckOccurTask occurTask = new CheckOccurTask(wordToFind, words2);
            if (forkJoinPool.invoke(occurTask)) {
                commonWords.add(wordToFind);
            }
        }

        return commonWords;
    }
}
