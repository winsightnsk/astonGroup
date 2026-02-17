package groupone.domain;

import groupone.model.UserABC;

import java.util.Comparator;
import java.util.List;

public class SortByPasswordStrategy implements SortStrategy{
    @Override
    public void sort(List<UserABC> list) {
        Quicksort.quicksort(list, Comparator.comparing(UserABC::getPassword), 0, list.size() - 1);
    }
}
