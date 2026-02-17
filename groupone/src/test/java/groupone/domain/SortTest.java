package groupone.domain;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.model.User;
import groupone.model.UserABC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.LinkedList;

class SortTest {
    public static <T> boolean isSorted(Iterable<? extends T> iterable, Comparator<? super T> comparator) {
        T previous = null;
        boolean firstElement = true;

        for (T current : iterable) {
            if (firstElement) {
                firstElement = false;
            } else {
                if (comparator.compare(previous, current) > 0) {
                    return false;
                }
            }
            previous = current;
        }
        return true;
    }

    @Test
    void testIsSorted() {
        LinkedList<UserABC> users = new LinkedList<>();
        DataInterface di = new RandomGenerator(50);
        di.forEach(item -> users.add(new User.Builder().setLine(item).build()));

        SortInterface context = new SortContext();
        context.setSortStrategy(new SortByUsernameStrategy());
        context.sort(users);
        Assertions.assertTrue(isSorted(users, Comparator.comparing(UserABC::getUsername)));

        context.setSortStrategy(new SortByPasswordStrategy());
        context.sort(users);
        Assertions.assertTrue(isSorted(users, Comparator.comparing(UserABC::getPassword)));

        context.setSortStrategy(new SortByEmailStrategy());
        context.sort(users);
        Assertions.assertTrue(isSorted(users, Comparator.comparing(UserABC::getEmail)));
    }
}