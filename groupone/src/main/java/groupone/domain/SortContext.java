package groupone.domain;

import groupone.model.User;

import java.util.List;

public class SortContext {
    private SortStrategy sortStrategy;

    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    public void sort(List<User> list) {
        sortStrategy.sort(list);
    }
}
