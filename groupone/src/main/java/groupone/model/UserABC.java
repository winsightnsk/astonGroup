package groupone.model;

import groupone.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class UserABC {

    private static final Logger logger = LoggerFactory.getLogger(UserABC.class.getName());

    protected String username;
    protected int password;
    protected String email;

    public String getUsername() { return username; }
    public int getPassword() { return password; }
    public String getEmail() { return email; }

    private UserABC() {}
    public UserABC(String username, int password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Проверка заполнения полей на валидность данных
     * Имя пользователя: одно или больше слов через один пробел
     * пароль: число больше или равно нулю;
     * Е-мэйл: поддерживает точки в имени (не повторяющиеся)
     * @return True если всё хорошо
     */
    public boolean isValid() {
        if (username == null || email == null) {
            if (username == null) logger.warn("Пустое имя");
            else logger.warn("пустой емеил");
            return false;
        }
        if (username.matches("^([a-zA-Zа-яА-Я]+(\\s[a-zA-Zа-яА-Я]+)*)$")
                && password >= 0
                && email.matches("^([a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+\\.[a-zA-Z]+)$")) {
            return true;
        } else {
            logger.warn("{} не прошёл валидацию", username);
            return false;
        }
    }

    @Override
    public String toString() {
        return String.join(", ", username, String.valueOf(password), email);
    }

}
