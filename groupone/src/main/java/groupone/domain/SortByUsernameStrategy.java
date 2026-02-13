package groupone.domain;

import groupone.model.User;

import java.util.Comparator;
import java.util.List;

public class SortByUsernameStrategy implements SortStrategy{
    @Override
    public void sort(List<User> list) {
        Quicksort.quicksort(list, Comparator.comparing(User::getUsername), 0, list.size() - 1);
    }
}
