package groupone.model;

public abstract class UserABC {

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
     * Имя пользователя: "^([a-zA-Zа-яА-Я]+([-\\s][a-zA-Zа-яА-Я]+)*)$"
     * одно или больше слов через один пробел или дефис;
     * пароль: число больше ноля;
     * Е-мэйл: поддерживает точки в имени (не повторяющиеся)
     * @return True если всё хорошо
     */
    public boolean isValid() {
        if (username == null || email == null) { return false; }
        return (username.matches("^([a-zA-Zа-яА-Я]+([-\\s][a-zA-Zа-яА-Я]+)*)$")
                && password >= 0
                && email.matches("^([a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+\\.[a-zA-Z]+)$"));
    }

    @Override
    public String toString() {
        return "Имя: " + username + ", Пароль: " + password + ", Почта: " + email;
    }

}
