import java.util.ArrayList;

/**
 * Created by Jake on 4/25/17.
 */
public class User {
    int id;
    String name;
    String password;

    public static ArrayList<User> users = new ArrayList<>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
