package groupone.data;

import groupone.model.User;
import groupone.model.UserABC;
import org.junit.jupiter.api.Test;

class DataStreamTest {

    @Test
    void testStream() {
        DataInterface di = new RandomGenerator(10);
        di.stream().map(item -> new User.Builder().setLine(item).build())
                    .filter(UserABC::isValid)
                    .forEach(System.out::println);
    }

}