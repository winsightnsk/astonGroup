package groupone.data;

import java.util.*;

import com.github.javafaker.Faker;

public class RandomGenerator extends DataABC {

    Random rnd = new Random();
    Faker faker = new Faker();

    private RandomGenerator() {}
    public RandomGenerator(int count){
        if (count <= 0) throw new RuntimeException("Ошибка входных параметров");
        this.count = count;
    }

    @Override
    public String next() {
        List<String> list = new ArrayList<>();
        list.add(faker.name().firstName());
        list.add(faker.lorem().word());
        list.add(faker.internet().emailAddress());
        this.i += 1;
        return String.join(", ", list);
    }

}
