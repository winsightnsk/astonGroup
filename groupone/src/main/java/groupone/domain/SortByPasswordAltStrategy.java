package groupone.domain;

import groupone.model.User;

import java.util.Comparator;
import java.util.List;

public class SortByPasswordAltStrategy implements SortStrategy{
    @Override
    public void sort(List<User> list) {
        Quicksort.altSort(list);
    }
}
