import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Folder {

    private List<Folder> subFolders;

    private List<Document> documents;

    public Folder(File dir) throws IOException {

        documents = new LinkedList<>();

        subFolders = new LinkedList<>();

        for (File entry : dir.listFiles()) {

            if (entry.isDirectory()) {

                subFolders.add(new Folder(entry));

            } else {

                documents.add(new Document(entry));

            }

        }

        //return new Folder(subFolders, documents);

    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
