import java.util.HashMap;

public class UserDatabase {
    private static HashMap<String, User> users = new HashMap<>();

    public static void addUser(String username, User user) {
        users.put(username, user);
    }

    public static User getUser(String username) {
        return users.get(username);
    }
}
