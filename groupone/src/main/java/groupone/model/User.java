package groupone.model;

public class User extends UserABC {

    private User() {}
    public User(String username, int password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static class Builder {
        private String username;
        private int password;
        private String email;
        public User.Builder setUsername(String username) { this.username = username; return this; }
        public User.Builder setPassword(int password) { this.password = password; return this; }
        public User.Builder setEmail(String email) { this.email = email; return this; }
        public User create() { return new User(username, password, email); }
    }

}
