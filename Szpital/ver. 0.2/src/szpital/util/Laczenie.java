package szpital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Laczenie 
{
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String databaseUrl1 = "jdbc:mysql://adresSerwera:port/nazwaBazyDanych";
    private static final String user = "wpisz tutaj nazwe uzytkownika";
    private static final String password = "tutaj wpisz haslo";
    private static Connection dbConnection;
    private static Statement stmt;

    public static Statement connect()
    {
        try 
        {
            Class.forName(driver);
            dbConnection = (Connection) DriverManager.getConnection(databaseUrl1, user, password);
            stmt = dbConnection.createStatement();
        } 
        catch (ClassNotFoundException ex) 
        {
            System.err.println("Blad sterownika MySQl JDBC");
        } 
        catch (SQLException exc) 
        { 
            System.err.println("Blad polacznia");
        }

        return stmt;
    }
     
    public static void closeConnection()
    {
        if(stmt != null)
            try 
            {
                stmt.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Blad przy zamykaniu polaczenia");
            }
    }
}
