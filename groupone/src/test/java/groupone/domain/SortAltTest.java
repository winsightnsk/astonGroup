package groupone.domain;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SortAltTest {
    public static List<Integer> odds(List<User> list) {
        // Сохраняем в список индексы нечётных элементов
        List<Integer> odds = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPassword() % 2 != 0)
                odds.add(i);
        }

        return odds;
    }

    public static boolean isEvenSorted(List<User> list, List<Integer> odds) {
        /* Проверяем, что отсортированы только чётные элементы:
         * смотрим на пару с индексами i, j. Если i-й элемент нечётный, то двигаемся до ближайшего чётного.
         * Сравниваем с j-м элементом, начиная с j = i + 1.
         * Если j-й элемент нечётный, то пропускаем его
         */
        for (int i = 0; i < list.size() - 1; i++){
            if (list.get(i).getPassword() % 2 != 0) {
                continue;
            }

            int j = i + 1;

            while (j < list.size() && list.get(j).getPassword() % 2 != 0) {
                j++;
            }

            if (j >= list.size())
                break;

            if (list.get(i).getPassword() > list.get(j).getPassword())
                return false;
        }

        // Проверяем, что нечётные элементы сохранили свои позиции
        for (Integer index : odds) {
            if (list.get(index).getPassword() % 2 == 0)
                return false;
        }

        return true;
    }

    @Test
    void firstExtra() {
        LinkedList<User> users = new LinkedList<>();
        DataInterface di = new RandomGenerator(50);
        di.forEach(item -> users.add(new User.Builder().setLine(item).build()));

        List<Integer> odds = odds(users);

        SortInterface context = new SortContext();
        context.setSortStrategy(new SortByPasswordAltStrategy());
        context.sort(users);

        Assertions.assertTrue(isEvenSorted(users, odds));
    }
}
