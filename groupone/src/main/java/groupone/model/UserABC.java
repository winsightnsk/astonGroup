package groupone.model;

public abstract class UserABC {

    protected String username;
    protected int password;
    protected String email;

    public String getUsername() { return username; }
    public int getPassword() { return password; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "Имя: " + username + ", Пароль: " + password + ", Почта: " + email;
    }

}
