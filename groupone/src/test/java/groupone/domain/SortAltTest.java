package groupone.domain;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.model.User;

import java.util.LinkedList;

public class SortAltTest {
    public static void main(String[] args) {
        LinkedList<User> users = new LinkedList<>();
        DataInterface di = new RandomGenerator(10);
        di.forEach(item -> users.add(new User.Builder().setLine(item).build()));
        users.forEach(System.out::println);
        System.out.println();

        SortInterface context = new SortContext();
        context.setSortStrategy(new SortByPasswordAltStrategy());
        context.sort(users);
        users.forEach(System.out::println);
    }
}
