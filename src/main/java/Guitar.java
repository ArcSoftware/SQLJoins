import java.util.ArrayList;

/**
 * Created by Jake on 4/25/17.
 */
public class Guitar {
    int id;
    String make;
    String model;
    String type;
    int year;
    String strings;
    String owner;

    public Guitar(int id, String make, String model, String type, int year, String strings, String owner) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
        this.strings = strings;
        this.owner = owner;
    }

    public static ArrayList<Guitar> guitars = new ArrayList<>();

    public Guitar(String make, String model, String type, int year, String strings, String owner) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.year = year;
        this.strings = strings;
        this.owner = owner;
    }


}
