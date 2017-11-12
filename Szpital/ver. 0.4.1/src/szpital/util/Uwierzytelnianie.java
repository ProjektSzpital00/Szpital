package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Uwierzytelnianie 
{
    public static boolean walidacja(String pesel, String haslo, Statement stmt)
    {
        try 
        {
            String query = "SELECT pesel, haslo FROM Konta";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                if (rs.getString("pesel").equals(pesel))  
                {
                    if(rs.getString("haslo").equals(haslo))
                        return true;
                }
            }
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Uwierzytelnianie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
