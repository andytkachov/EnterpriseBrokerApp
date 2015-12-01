package broker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    static private final String DB_CONNECTION = "jdbc:mysql://localhost/StockExchange?user=root";
    static Boolean drvLoaded = false;
    
    static private Connection createConnection() throws SQLException {
        if (!drvLoaded)    
        {
            Driver.LoadDriver();
            drvLoaded = true;
        }
        return DriverManager.getConnection(DB_CONNECTION);
    }
    
    static public Connection getConnection() throws SQLException{
        return createConnection();
    }   
    
}
