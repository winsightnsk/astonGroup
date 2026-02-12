package groupone.model;

public class User {

    private String username;
    private String password;
    private String email;

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }

    private User() {}
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public abstract static class Builder {
        private String username;
        private String password;
        private String email;
        public Builder setUsername(String username) { this.username = username; return this; }
        public Builder setPassword(String password) { this.password = password; return this; }
        public Builder setEmail(String email) { this.email = email; return this; }
        public User create() { return new User(username, password, email); }
    }

}
