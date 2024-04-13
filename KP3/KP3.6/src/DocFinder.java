import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class DocFinder {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();

    String[] it_words = {"system", "software", "data", "algorithm", "hardware", "network", "database", "performance"};

    private List<Document> it_documents;

    public DocFinder() {
        it_documents = new ArrayList<>();
    }

    void countOccurrencesInParallel(Folder folder) {

        forkJoinPool.invoke(

                new FolderSearchTask(folder));

    }

    public List<Document> getIt_documents() {
        return it_documents;
    }

    class DocumentSearchTask extends RecursiveTask<Long> {

        private final Document document;

        public DocumentSearchTask(Document document) {
            this.document = document;
        }

        @Override
        protected Long compute() {

            return occurrencesCount();

        }

        Long occurrencesCount() {

            long count = 0;

            for (String word : document.getWords()) {
                for (String it_word : it_words) {

                    if (word.equalsIgnoreCase(it_word)) {

                        count++;

                    }
                }

            }

            return count;

        }

    }

    class FolderSearchTask extends RecursiveAction {
        private final Folder folder;

        public FolderSearchTask(Folder folder) {
            this.folder = folder;
        }

        @Override

        protected void compute() {

            long counter;

            List<RecursiveTask<Long>> tasks = new LinkedList<>();


            for (Document document : folder.getDocuments()) {

                DocumentSearchTask task = new DocumentSearchTask(document);

                tasks.add(task);

                task.fork();

                counter = task.invoke();

                if (counter > 3) {
                    it_documents.add(document);
                }

            }

        }

    }

}