import java.sql.*;

public class DatabaseConnection {

    private final String userName = "root";
    private final String password = "xxxxxx";
    private final String db = "jdbc:mysql://localhost:3306/library";

    private Connection connection = null;

    public DatabaseConnection(){
        try {
            //Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Establish connection to the database
            connection = DriverManager.getConnection(db, userName, password);
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println();
    }

    public Connection getConnection(){
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
