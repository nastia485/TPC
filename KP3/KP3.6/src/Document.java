
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

class Document {

    private final List<String> lines;

    private File file;

    public Document(File file)

            throws IOException {
        this.file = file;
        lines = new ArrayList<>();

        try (BufferedReader reader =

                     new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while (line != null) {

                lines.add(line);

                line = reader.readLine();

            }

        }
        // return new Document(lines);

    }

    public List<String> getLines() {
        return lines;
    }

    public ArrayList<String> getWords() {
        ArrayList<String> words = new ArrayList<>();
        String[] wordsInLine;
        List<String> wordsInLineList;

        for (String i : lines) {
            wordsInLine = i.trim().split("(\\s|\\p{Punct})+");
            wordsInLineList = Arrays.stream(wordsInLine)
                    .map(String::toLowerCase)
                    .filter(word -> word.length() > 2)
                    .collect(Collectors.toList());
            words.addAll(wordsInLineList);
        }

        HashSet<String> uniqueWords = new HashSet<>(words);

        words = new ArrayList<>(uniqueWords);

        return words;

    }

    @Override
    public String toString() {
        return "Document: " +
                file.getName();
    }
}
