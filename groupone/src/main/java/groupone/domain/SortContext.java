package groupone.domain;

import groupone.model.UserABC;

import java.util.List;

public class SortContext implements SortInterface{
    private SortStrategy sortStrategy;

    @Override
    public void setSortStrategy(SortStrategy sortStrategy) {
        this.sortStrategy = sortStrategy;
    }

    @Override
    public void sort(List<UserABC> list) {
        sortStrategy.sort(list);
    }
}
