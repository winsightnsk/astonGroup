package groupone.domain;

import groupone.model.User;

import java.util.List;

public interface SortStrategy {
    void sort(List<User> list);
}
