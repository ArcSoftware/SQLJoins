import com.google.gson.Gson;
import org.h2.tools.Server;
import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Jake on 4/25/17.
 */
public class Main {
    public static Gson gson = new Gson();
    //public static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        Spark.staticFileLocation("/resources/public");
        Server.createWebServer().start();
        Connection conn = DriverManager.getConnection("jdbc:h2:./main");
        Statement stmt = conn.createStatement();
        stmt.execute("create table if not exists users(id identity, name varchar, password varchar");
        stmt.execute("create table if not exists guitars(id identity, make varchar, model varchar, " +
                "type varchar, year int, strings varchar, owner int");

        Spark.post(
                "/create-user",
                ((request, response) -> {
                    String name = request.queryParams("name");
                    String password = request.queryParams("password");
                    User a = new User(name, password);
                    User.users.add(a);
                    SQL.addUser(conn, a);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/create-guitar",
                ((request, response) -> {
                    String make = request.queryParams("make");
                    String model = request.queryParams("model");
                    String type = request.queryParams("type");
                    String year = request.queryParams("year");
                    String strings = request.queryParams("strings");
                    Guitar g = new Guitar(make, model, type, Integer.valueOf(year), strings, null);
                    Guitar.guitars.add(g);
                    SQL.addGuitar(conn, g);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/claim-guitar",
                ((request, response) -> {
                    String id = request.queryParams("id");
                    String name = request.queryParams("name");
                    SQL.claimGuitar(conn, Integer.valueOf(id), name);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.get(
                "/all-guitars",
                ((request, response) -> {
                    System.out.println(SQL.viewAllGuitars(conn).size());
                    System.out.println(gson.toJson(SQL.viewAllGuitars(conn)));

                    return gson.toJson(SQL.viewAllGuitars(conn));
                })
        );


    }
}
