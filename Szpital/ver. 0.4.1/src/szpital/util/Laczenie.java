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

    public static Statement connect() throws SQLException, ClassNotFoundException
    {
        try 
        {
            Class.forName(driver);
            dbConnection = (Connection) DriverManager.getConnection(databaseUrl1+"?useSSL=false", user, password);
            stmt = dbConnection.createStatement();
        } 
        catch (ClassNotFoundException ex) 
        {
            throw new ClassNotFoundException("Błąd sterownika MySQL JDBC", ex);
        } 
        catch (SQLException exc) 
        { 
            throw new SQLException("Nie udalo się nawiązać połączenia", exc);
        }

        return stmt;
    }
     
    public static void disconnect()
    {
        if(stmt != null)
        {
            try 
            {
                stmt.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Blad przy zamykaniu polaczenia!");
            }
        }
    }
}
