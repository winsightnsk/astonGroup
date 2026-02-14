package groupone.domain;

import groupone.model.User;

import java.util.List;

public class SortContext implements SortInterface{
    private SortStrategy sortStrategy;

    @Override
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void sort(List<User> list) {
        sortStrategy.sort(list);
    }
}
