package groupone.domain;

import groupone.model.UserABC;

import java.util.List;

public interface SortInterface {
    void sort(List<UserABC> list);
    void setSortStrategy(SortStrategy sortStrategy);
}
