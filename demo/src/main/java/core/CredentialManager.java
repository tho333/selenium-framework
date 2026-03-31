package core;

public class CredentialManager {

    public static String getUsername() {
        String username = System.getenv("APP_USERNAME");
        if (username == null || username.trim().isEmpty()) {
            return ConfigReader.getProperty("username");
        }
        return username;
    }

    public static String getPassword() {
        String password = System.getenv("APP_PASSWORD");
        if (password == null || password.trim().isEmpty()) {
            return ConfigReader.getProperty("password");
        }
        return password;
    }
}
