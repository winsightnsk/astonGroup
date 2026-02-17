package groupone.domain;

import groupone.model.UserABC;

import java.util.List;

public class SortByPasswordAltStrategy implements SortStrategy{
    @Override
    public void sort(List<UserABC> list) {
        Quicksort.altSort(list);
    }
}
