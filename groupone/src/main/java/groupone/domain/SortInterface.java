package groupone.domain;

import groupone.model.User;

import java.util.List;

public interface SortInterface {
    void sort(List<User> list);
    void setSortStrategy(SortStrategy sortStrategy);
}
