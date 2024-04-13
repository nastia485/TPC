
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        // return new Document(lines);

    }

    public List<String> getLines() {
        return lines;
    }
}
