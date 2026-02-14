package groupone.domain;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class SortAltTest {
    public static boolean isEvenSorted(List<User> list, SortInterface context) {
        // Выделить пользователей с чётным паролем
        List<User> evenUsers = new LinkedList<>();
        for (User user : list) {
            if (user.getPassword() % 2 == 0)
                evenUsers.add(user);
        }

        // Сохраняем копию "чётного" списка
        List<User> evenCopy = new LinkedList<>(evenUsers);
        context.sort(evenCopy);

        // Проверяем совпадение позиций чётных объектов
        int expectedIndex = 0;
        for (User user : list) {
            if (user.getPassword() % 2 == 0) {
                // Проверяем, совпадает ли реальный чётный объект с ожидаемым отсортированным
                if (evenCopy.get(expectedIndex).getPassword() != user.getPassword()) {
                    return false;
                }
                expectedIndex++;
            }
        }

        return true;
    }

    @Test
    void firstExtra() {
        LinkedList<User> users = new LinkedList<>();
        DataInterface di = new RandomGenerator(50);
        di.forEach(item -> users.add(new User.Builder().setLine(item).build()));

        SortInterface context = new SortContext();
        context.setSortStrategy(new SortByPasswordAltStrategy());
        context.sort(users);

        Assertions.assertTrue(isEvenSorted(users, context));
    }
}
