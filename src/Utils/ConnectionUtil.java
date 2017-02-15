package Utils;


import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionUtil {
	
private static java.sql.Connection con;
    
    public static java.sql.Connection getConnection(){
        try { 
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/motoStore";
            con = DriverManager.getConnection(url,"root","root");
            con.setAutoCommit(false);
            return con;
        } catch ( ClassNotFoundException e){
            e.printStackTrace();
        } catch ( SQLException se){
            se.printStackTrace();
        }
        return null;
    }
    
    public static void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
