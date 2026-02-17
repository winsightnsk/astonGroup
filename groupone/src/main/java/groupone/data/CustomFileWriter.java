package groupone.data;

import groupone.model.User;
import groupone.model.UserABC;

import java.util.List;

public interface CustomFileWriter {
    void appendWrite(List<UserABC> users, String path);
}
