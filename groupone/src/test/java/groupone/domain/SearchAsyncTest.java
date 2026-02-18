package groupone.domain;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import groupone.model.User;
import groupone.model.UserABC;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SearchAsyncTest {

    @Test
    void matches() {
        List<UserABC> list = new ArrayList<>();
        assertEquals(0, (new SearchAsync(list)).matches("текст"));
        assertEquals(0, (new SearchAsync(list)).matches(""));
        assertEquals(0, (new SearchAsync(list)).matches(null));

        Stream.of("Куприянов Олег Валентинович, 23525, kupri@mail.ru")
                .map(item -> new User.Builder().setLine(item).build())
                .filter(UserABC::isValid)
                .forEach(list::add);
        assertEquals(0, (new SearchAsync(list)).matches(""));
        assertEquals(0, (new SearchAsync(list)).matches("Олежа"));
        assertEquals(1, (new SearchAsync(list)).matches("Олег"));
        assertEquals(1, (new SearchAsync(list)).matches("олег"));

        Stream.of(
                        "Петрова Мария Сергеевна, 67890, petrova@mail.ru",                     // полный формат
                        "Кузнецова Анна Владимировна, 98765, kuznetsova@mail.ru",            // полный формат
                        "Морозов Сергей, 11223, morozov@mail.ru",                             // без отчества
                        "Фёдорова, 33445, fedorova@mail.ru",                                 // только фамилия (отсутствуют имя и отчество)
                        "Алексеев Иван Сергеевич, 66778, alekseev@mail.ru",                  // полный формат
                        "Григорьева Татьяна, 99001, grigoreva@mail.ru",                      // без отчества
                        "Соколов Владимир Андреевич, 22334, sokolov@mail.ru",               // полный формат
                        "Михайлова Ирина, 55667, mikhaylova@mail.ru",                       // без отчества
                        "Тарасов Алексей Викторович, 88990, tarasov@mail.ru",                // полный формат
                        "Андреева Светлана Михайловна, 12121, andreeva@mail.ru",              // полный формат
                        "Павлов, 34343, pavlov@mail.ru",                                    // только фамилия
                        "Семёнов Александр, 78787, semenov@mail.ru",                       // без отчества
                        "Захарова Юлия Владимировна, 90909, zaharova@mail.ru",              // полный формат
                        "Волков Евгений Алексеевич, 13131, volkov@mail.ru",                 // полный формат
                        "Белова Екатерина Сергеевна, 45454, belova@mail.ru",                // полный формат
                        "Иванов, 10101, invalid-email",                                     // неверный email
                        "Петров Алексей, 20202",                                             // отсутствует email
                        "Сидорова Мария Ивановна, abcde, sidorova@mail.ru",                 // нечисловой ID
                        "Кузнецов, , kuznetsov@mail.ru",                                    // пустой ID, только фамилия и email
                        "Васильева Елена Александровна, 44556, vasilieva@mail.ru"            // полный формат
                )
                .map(item -> new User.Builder().setLine(item).build())
                .filter(UserABC::isValid)
                .forEach(list::add);
        assertEquals(0, (new SearchAsync(list)).matches("иванов"));
        assertEquals(1, (new SearchAsync(list)).matches("КузнЕцов"));
        assertEquals(1, (new SearchAsync(list)).matches("Васильева Елена Александровна"));
        assertEquals(6, (new SearchAsync(list)).matches("ЕЕ"));
    }

    @Test
    void matches2() {
        List<UserABC> list = new ArrayList<>();
        DataInterface di = new RandomGenerator(999999);
        di.stream().map(item -> new User.Builder().setLine(item).build())
                .filter(UserABC::isValid)
                .forEach(list::add);
        assertTrue((new SearchAsync(list)).matches("a") > 0);
    }

}
