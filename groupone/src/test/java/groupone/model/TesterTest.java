package groupone.model;

import groupone.data.DataInterface;
import groupone.data.RandomGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TesterTest {

    @Test
    void testBuilder() {
        UserABC user = new User.Builder()
                .setUsername(" Иван Иванович ")
                .setPassword(123)
                .setEmail("ivan.ivan@mail.ru ")
                .build();
        assertTrue(user.isValid());
        assertEquals("Иван Иванович", user.getUsername());
        assertEquals(123, user.getPassword());
        assertEquals("ivan.ivan@mail.ru", user.getEmail());
        user = new User.Builder()
                .setLine("  Aula fon der Bruman,  22884,valld.emar@gmail.com")
                .build();
        assertTrue(user.isValid());
        assertEquals("Aula fon der Bruman", user.getUsername());
        assertEquals(22884, user.getPassword());
        assertEquals("valld.emar@gmail.com", user.getEmail());
        user = new User.Builder()
                .setLine("22884,Aula fon der Bruman,valld.emar@gmail.com")
                .build();
        assertFalse(user.isValid());
        user = new User.Builder()
                .setLine("Aula fon der Bruman,valld.emar@gmail.com")
                .build();
        assertFalse(user.isValid());
        user = new User.Builder()
                .setLine("Aula fon der Bruman,22884,valld.emar@gmail.com, ")
                .build();
        assertFalse(user.isValid());
        user = new User.Builder()
                .setLine("Aula fon der Bruman,22884,valld.emar@gmailcom")
                .build();
        assertFalse(user.isValid());
        user = new User.Builder()
                .setLine("Aula fon der Bruman,22884,valldemar@gmail.com")
                .build();
        assertTrue(user.isValid());
        user = new User.Builder()
                .setLine("Aula fon der Bruman,,valld.emar@gmail.com")
                .build();
        assertFalse(user.isValid());
    }

    @Test
    void testDI() {
        DataInterface di = new RandomGenerator(50);
        di.forEach(item -> assertTrue(new User.Builder().setLine(item).build().isValid()));
    }

}
