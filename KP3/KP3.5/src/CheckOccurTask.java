import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CheckOccurTask extends RecursiveTask<Boolean> {

    static final int listSize = 100;

    private final String word;

    private List<String> wordsToFind;

    public CheckOccurTask(String word, List<String> wordsToFind) {
        this.word = word;
        this.wordsToFind = wordsToFind;
    }


    @Override
    protected Boolean compute() {
        if (wordsToFind.size() < listSize) {
            return computeDirectly();
        } else {
            int middle = wordsToFind.size() / 2;

            CheckOccurTask subTask1 = new CheckOccurTask(word, wordsToFind.subList(0, middle));
            CheckOccurTask subTask2 = new CheckOccurTask(word, wordsToFind.subList(middle, wordsToFind.size()));

            invokeAll(subTask1, subTask2);

            return subTask1.join() || subTask2.join();
        }

    }

    private Boolean computeDirectly(){
        return wordsToFind.stream()
                .anyMatch(sample -> sample.equalsIgnoreCase(word));
    }
}
