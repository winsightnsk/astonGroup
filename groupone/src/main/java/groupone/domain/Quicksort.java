package groupone.domain;

import groupone.model.User;
import groupone.model.UserABC;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Quicksort {
    public static void quicksort(List<User> list, Comparator<User> comp, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(list, comp, start, end);

            quicksort(list, comp, start, pivotIndex - 1);
            quicksort(list, comp, pivotIndex + 1, end);
        }
    }

    private static int partition(List<User> list, Comparator<User> comp, int start, int end) {
        User pivot = list.get(end);

        int i = start - 1;
        for (int j = start; j < end; j++) {
            // Компаратор возвращает 0, если элементы равны; 1, если первый больше второго и -1, если наоборот
            if (comp.compare(list.get(j), pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, end);
        return (i + 1);
    }

    private static void swap(List<User> list, int x, int y) {
        User user = list.get(x);
        list.set(x, list.get(y));
        list.set(y, user);
    }

    public static void altSort(List<User> list) {
        List<User> targetUsers = new LinkedList<>();
        for (User user : list) {
            if (user.getPassword() % 2 == 0)
                targetUsers.add((user));
        }

        quicksort(targetUsers, Comparator.comparingInt(UserABC::getPassword), 0, targetUsers.size() - 1);

        int evenIndex = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword() % 2 == 0)
                list.set(i, targetUsers.get(evenIndex++));
        }
    }
}
