package groupone.model;

import groupone.data.DataInterface;
import groupone.data.FileReader;
import groupone.data.RandomGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {

    // статик только здесь в тестах!
    private static class Tester extends ABC<Integer, String, Double> {
        public Tester(Integer id, String name, Double val) {
            super(id, name, val);
        }
        public static class Builder extends ABC.Builder<Integer, String, Double, Tester> {
            @Override
            public Tester build() {
                return new Tester(field1, field2, field3);
            }
        }
    }

    @Test
    void testBuild() {
        Tester tester = new Tester(1, "Андрейка", 100500.0);
        Tester built = new Tester.Builder()
                .setField1(200)
                .setField2("Стёпанныч")
                .setField3(-1.5)
                .build();
        assertNotNull(tester);
        assertEquals(1, tester.getField1());
        assertEquals("Андрейка", tester.getField2());
        assertNotNull(built);
        assertEquals(-1.5, built.getField3(), 0.1);

        DataInterface di = new RandomGenerator(5, tester.listClasses());
        di.forEach(System.out::println);
    }

}
