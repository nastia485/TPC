//Розробіть та реалізуйте алгоритм пошуку спільних слів
//в текстових документах з використанням ForkJoinFramework.


import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String filePath1 = "D:\\TextFolder\\Folder1\\middleText.txt";
        String filePath2 = "D:\\TextFolder\\Folder1\\simpleText.txt";

        try {
            Document doc1 = new Document(new File(filePath1));
            Document doc2 = new Document(new File(filePath2));
            CommonWordsFinder wordsFinder = new CommonWordsFinder(doc1, doc2);
            System.out.println(wordsFinder.findCommonWords());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}