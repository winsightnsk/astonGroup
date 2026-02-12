package groupone.model;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {

    @Test
    void testBuild() {
        DataInterface di = new RandomGenerator(5);
        di.forEach(System.out::println);
    }

}
