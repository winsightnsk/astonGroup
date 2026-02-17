package groupone.domain;

import groupone.model.UserABC;

import java.util.List;

public interface SortStrategy {
    void sort(List<UserABC> list);
}
