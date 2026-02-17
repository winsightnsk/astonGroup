package groupone.data;

import groupone.model.User;
import groupone.model.UserABC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserFileWriter implements CustomFileWriter{
    private static final Logger logger = LoggerFactory.getLogger(UserFileWriter.class.getName());

    @Override
    public void appendWrite(List<UserABC> users, String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            for (UserABC user : users) {
                writer.write(user.toString() + '\n');
            }
        } catch (IOException e) {
            logger.error(String.valueOf(e));
        }
    }
}
