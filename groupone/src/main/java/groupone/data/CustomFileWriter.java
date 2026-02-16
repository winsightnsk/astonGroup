package groupone.data;

import groupone.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CustomFileWriter {
    private static final Logger logger = LoggerFactory.getLogger(CustomFileWriter.class.getName());
    private static final String HEADER = "Имя;Пароль;Почта";
    private static final String SEPARATOR = ", ";

    public static void appendWrite(List<User> users, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (User user : users) {
                writer.write(String.join(SEPARATOR, user.getUsername(), String.valueOf(user.getPassword()), user.getEmail()) + '\n');
            }
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }
}
