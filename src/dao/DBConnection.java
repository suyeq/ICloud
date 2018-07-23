package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {
    protected static Connection con=null;
    protected static PreparedStatement sta=null;
    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        String driver=Config.getConfig("driver");
        String url=Config.getConfig("url");
        String username=Config.getConfig("username");
        String pass=Config.getConfig("password");
        Class.forName(driver);
        con= DriverManager.getConnection(url,username,pass);
        if (con!=null){
            return con;
        }
        return null;
    }
}
