
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Document {

    private final List<String> lines;

    public Document(File file)

            throws IOException {
        lines = new ArrayList<>();

        try (BufferedReader reader =

                     new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();

            while (line != null) {

                lines.add(line);

                line = reader.readLine();

            }

        }
    }

    public List<String> getLines() {
        return lines;
    }

    public String[] getWords() {
        List<String> words = new ArrayList<>();
        String[] wordsInLine;
        for (String i : lines) {
            wordsInLine = i.trim().split("(\\s|\\p{Punct})+");

            List<String> wordsToAdd = Arrays.stream(wordsInLine)
                     .filter(word -> word.length() > 3 && !word.startsWith("th"))
                    .toList();

            words.addAll(wordsToAdd);
        }


        String[] resultWords = new String[words.size()];
        for (int i = 0; i < words.size(); i++) {
            resultWords[i] = words.get(i).toLowerCase();
        }

        return resultWords;

    }

}

