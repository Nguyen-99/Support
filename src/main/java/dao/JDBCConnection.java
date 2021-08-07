package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCConnection {
    public static Connection getJDBCConnection(){
        Connection con=null;
        final String url="jdbc:mysql://localhost:3306/human_resource";
        final String username="root";
        final String password="nguyenz";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//tai mysql driver vào
            con=DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
