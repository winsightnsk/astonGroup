package groupone.model;

public class User extends UserABC {

    public User(String username, int password, String email) {
        super(username, password, email);
    }

    public static class Builder {
        private String username;
        private int password;
        private String email;
        public User.Builder setUsername(String username) { this.username = username.trim(); return this; }
        public User.Builder setPassword(int password) { this.password = password; return this; }
        public User.Builder setEmail(String email) { this.email = email.trim(); return this; }
        public User.Builder setLine(String line) {
            String[] list = line.split(",");
            if (list.length != 3) {
                username = "";
                password = -1;
                email = "";
            } else {
                username = list[0].trim();
                try {
                    password = Integer.parseInt(list[1].trim());
                } catch (NumberFormatException e) {
                    password = -1;
                }
                email = list[2].trim();
            }
            return this;
        }
        public User build() { return new  User(username, password, email); }
    }

}
