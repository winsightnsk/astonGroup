package groupone.data;

import groupone.model.User;

import java.util.List;

public interface CustomFileWriter {
    void appendWrite(List<User> users, String path);
}
