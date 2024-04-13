
//Розробіть та реалізуйте алгоритм пошуку текстових документів,
//які відповідають заданим ключовим словам (належать до області «Інформаційні технології»),
//з використанням ForkJoinFramework.

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Folder folder = new Folder(new File("D:/TextFolder/Folder2"));

        DocFinder docFinder = new DocFinder();

        docFinder.countOccurrencesInParallel(folder);

        System.out.println(docFinder.getIt_documents());
    }
}