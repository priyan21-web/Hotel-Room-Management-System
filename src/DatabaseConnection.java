import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {

    static String url;
    static String username;
    static String password;

    static {
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream("config.properties");
            properties.load(file);
            file.close();

            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

        } catch (IOException e) {
            System.out.println("Could not load config.properties");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully!");
            return con;
        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        getConnection();
    }
}