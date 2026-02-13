package groupone.domain;

import groupone.model.User;

import java.util.Comparator;
import java.util.List;

public class SortByPasswordStrategy implements SortStrategy{
    @Override
    public void sort(List<User> list) {
        Quicksort.quicksort(list, Comparator.comparing(User::getPassword), 0, list.size() - 1);
    }
}
