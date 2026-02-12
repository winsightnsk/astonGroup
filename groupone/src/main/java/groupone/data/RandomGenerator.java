package groupone.data;

import java.util.*;

import com.github.javafaker.Faker;

public class RandomGenerator extends DataABC {

    private List<Class<?>> classList;

    Random rnd = new Random();
    Faker faker = new Faker();

    private RandomGenerator() {}
    public RandomGenerator(int count, List<Class<?>> classes){
        if (count <= 0 || classes == null || classes.size() != 3)
            throw new RuntimeException("Ошибка входных параметров");
        this.count = count;
        classList = classes;
    }

    @Override
    public String next() {
        List<String> list = new ArrayList<>();
        for (int i=0; i<3; i++) {
            if (classList.get(i) == Integer.class) {
                list.add(String.valueOf(rnd.nextInt()));
            } else if (classList.get(i) == Double.class) {
                list.add(String.valueOf(rnd.nextDouble()));
            } else if (classList.get(i) == String.class) {
                list.add(faker.lorem().word());
            } else throw new RuntimeException("Класс "
                    + classList.get(i).getSimpleName() + " не поддерживается");
        }
        this.i += 1;
        return String.join(", ", list);
    }

}
