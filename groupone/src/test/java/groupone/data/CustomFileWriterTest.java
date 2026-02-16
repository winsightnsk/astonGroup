package groupone.data;

import groupone.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

class CustomFileWriterTest {
    private static final String SEPARATOR = ", ";
    private static final String FILENAME = "testOutput.txt";
    private static final int SIZE = 50;

    @TempDir
    private static Path TEMP_DIR;

    public static List<String> getLastLines(String path, int num) throws IOException {
        List<String> lines = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);

                if (lines.size() > num) {
                    lines.removeFirst();
                }
            }
        }
        return lines;
    }

    @Test
    void appendWriteTest() throws IOException {
        LinkedList<User> users = new LinkedList<>();
        DataInterface di = new RandomGenerator(SIZE);
        di.forEach(item -> users.add(new User.Builder().setLine(item).build()));

        List<String> expectedLines = users.stream()
                .map(user -> String.join(SEPARATOR, user.getUsername(), String.valueOf(user.getPassword()), user.getEmail()))
                .toList();

        Path outputFile = TEMP_DIR.resolve(FILENAME);
        CustomFileWriter.appendWrite(users, outputFile.toString());

        Assertions.assertTrue(Files.exists(outputFile));

        List<String> actualLines = getLastLines(outputFile.toString(), SIZE);

        Assertions.assertEquals(expectedLines, actualLines, "Содержимое файла не совпадает с содержимым списка");
        Assertions.assertEquals(SIZE, actualLines.size(), String.format("В файле должно быть ровно %s строк", SIZE));
    }
}