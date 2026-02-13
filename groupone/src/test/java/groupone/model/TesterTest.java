package groupone.model;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.domain.DataSort;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {

    @Test
    void testBuild() {
        DataInterface di = new RandomGenerator(5);

        // Заполнение списка пользователей на основе строк, которые выдаёт DataInterface
        List<User> list = new LinkedList<>();
        for (String s : di) {
            String[] tmp = s.split(",");
            User u = new User(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
            list.add(u);
            System.out.println(u);
        }

        System.out.println();

        DataSort context = new DataSort();
        context.setSortStrategy(DataSort.SortByUsername);
        context.sort(list);
        list.forEach(System.out::println);
        System.out.println();
        context.setSortStrategy(DataSort.SortByPassword);
        context.sort(list);
        list.forEach(System.out::println);
        System.out.println();
        context.setSortStrategy(DataSort.SortByEmail);
        context.sort(list);
        list.forEach(System.out::println);

        //di.forEach(System.out::println);
    }

}
