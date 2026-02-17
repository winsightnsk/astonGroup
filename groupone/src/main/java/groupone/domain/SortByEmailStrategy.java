package groupone.domain;

import groupone.model.UserABC;

import java.util.Comparator;
import java.util.List;

public class SortByEmailStrategy implements SortStrategy{
    @Override
    public void sort(List<UserABC> list) {
        Quicksort.quicksort(list, Comparator.comparing(UserABC::getEmail), 0, list.size() - 1);
    }
}
