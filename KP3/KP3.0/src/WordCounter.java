import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class WordCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    String[] wordsIn(String line) {

        return line.trim().split("(\\s|\\p{Punct})+");

    }

    Long occurrencesCount(Document document, String searchedWord) {

        long count = 0;

        for (String line : document.getLines()) {

            for (String word : wordsIn(line)) {

                if (searchedWord.equalsIgnoreCase(word)) {

                    count++;

                }

            }

        }

        return count;

    }
    Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {

        long count = 0;

        for (Folder subFolder : folder.getSubFolders()) {

            count = count + countOccurrencesOnSingleThread(subFolder, searchedWord);

        }

        for (Document document : folder.getDocuments()) {

            count = count + occurrencesCount(document, searchedWord);

        }

        return count;

    }

    Long countOccurrencesInParallel(Folder folder, String searchedWord){

        return forkJoinPool.invoke(

                new FolderSearchTask(folder, searchedWord));

    }

    class DocumentSearchTask extends RecursiveTask<Long> {

        private final Document document;

        private final String searchedWord;

        public DocumentSearchTask(Document document, String searchedWord) {
            this.document = document;
            this.searchedWord = searchedWord;
        }

        @Override
        protected Long compute() {

            //System.out.println("occurences: "+occurrencesCount(document, searchedWord));
            return occurrencesCount(document, searchedWord);

        }

    }

    class FolderSearchTask extends RecursiveTask<Long> {
        private final Folder folder;

        private final String searchedWord;

        public FolderSearchTask(Folder folder, String searchedWord) {
            this.folder = folder;
            this.searchedWord = searchedWord;
        }

        @Override

        protected Long compute() {

            long count = 0L;

            List<RecursiveTask<Long>> tasks = new LinkedList<>();

            for (Document document : folder.getDocuments()) {

                System.out.println(document.getLines());

                DocumentSearchTask task = new DocumentSearchTask(document, searchedWord);

                tasks.add(task);

                task.fork();

            }

            for (RecursiveTask<Long> task : tasks) {

                count = count + task.invoke();

            }

            return count;

        }

    }

}