
package db;


import java.sql.Connection;
import java.sql.DriverManager;




public class mysqlconnect {
    Connection conn = null;
    private static String url="jdbc:mysql://localhost:3306/heroes";
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,"root","");
            return conn;
        }catch(Exception e){
            return null;
        }
        
    }
    
 
}
