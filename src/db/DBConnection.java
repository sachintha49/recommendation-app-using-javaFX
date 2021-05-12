package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection = null;
    private static DBConnection dbConnection;

    public DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db","root","mysql");
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbConnection == null){
            dbConnection = new DBConnection();
        }
        return dbConnection;
    }
}
