import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Jake on 4/25/17.
 */
public class SQL {
    public static void addUser(Connection conn, User user) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("insert into users values(null,?,?");
        stmt.setString(1, user.name);
        stmt.setString(2, user.password);
    }
    public static void addGuitar(Connection conn, Guitar guitar) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("insert into guitars values(null,?,?,?,?,?,null");
        stmt.setString(1, guitar.make);
        stmt.setString(2, guitar.model);
        stmt.setString(3, guitar.type);
        stmt.setInt(4, guitar.year);
        stmt.setString(5, guitar.strings);

    }
    public static void claimGuitar(Connection conn, int id, String name) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement("update guitars set owner=? where id=?");
        stmt.setString(1, name);
        stmt.setInt(2, id);

    }
    public static ArrayList viewAllGuitars(Connection conn) throws SQLException {
        ArrayList all = new ArrayList();
        Statement stmt=conn.createStatement();
        ResultSet results = stmt.executeQuery("select * from guitars inner join users on " +
                "users.id=guitars.owner");
        while(results.next()) {
            int id = results.getInt("id");
            String make = results.getString("make");
            String model = results.getString("model");
            String type = results.getString("type");
            int year = results.getInt("year");
            String strings = results.getString("strings");
            String owner = results.getString("name");
            all.add(new Guitar(id, make, model, type, year, strings, owner));
        }
        return all;
        }




}
