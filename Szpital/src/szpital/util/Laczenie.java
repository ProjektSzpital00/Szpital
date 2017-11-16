package szpital.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Laczenie 
{
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String databaseUrl1 = "jdbc:mysql://80.211.205.68:3306/Szpital2";
    private static final String user = "szpital";
    private static final String password = "podaj haslo";
    private static Connection dbConnection;
    private static Statement stmt;
    
    private static void getConnection() throws ClassNotFoundException, SQLException
    {
        if(dbConnection == null)
        {    
            try 
            {
                Class.forName(driver);
                dbConnection = (Connection) DriverManager.getConnection(databaseUrl1+"?useSSL=false", user, password);
            } 
            catch (ClassNotFoundException ex) 
            {
                throw new ClassNotFoundException("Błąd sterownika MySQL JDBC", ex);
            } 
            catch (SQLException exc) 
            { 
                throw new SQLException("Nie udalo się nawiązać połączenia", exc);
            }
        }
    }
    
    public static void closeConnection()
    {
        if(dbConnection != null)
        {
            try 
            {
                dbConnection.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println("Blad przy zamykaniu polaczenia!");
            }
        }
    }
            
    public static Statement getStatement() throws SQLException, ClassNotFoundException
    {
        if(stmt != null)
        {
            return stmt;
        }
        else
        {
            try 
            {
                if(dbConnection != null)
                {
                    stmt = dbConnection.createStatement();
                }
                else
                {
                    getConnection();
                    stmt = dbConnection.createStatement();
                }   
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
    }
}
