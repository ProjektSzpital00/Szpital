package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Sala;

public class SalaUtil 
{
    private static ObservableList<Sala> salaList = FXCollections.observableArrayList();
    
    public static ObservableList<Sala> getSalaList() throws SQLException, ClassNotFoundException
    { 
        if(salaList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM Sale";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Sala s = new Sala(rs.getInt("id"), rs.getString("nazwa"), rs.getString("opis"), rs.getInt("id_oddzialu"));
                        salaList.add(s);
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return salaList;
    }
    
     public static void clearSalaList()
    {
        salaList.clear();
    }
    
    public static void addSala(Sala sala)
    {
        
    }
    
    public static void updateSala(Sala sala)
    {
        
    }
    
    public static void deleteSala(Integer idSali)
    {
        
    }
    
    public static Integer searchSalaId(String nazwaSali)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM Sale WHERE nazwa = '"+nazwaSali+"'";
                
                ResultSet rs = stmt.executeQuery(query);
                
                if(rs.next())
                {
                    ip = rs.getInt("id");
                }
            }
            catch(SQLException ex)
            {
                throw new SQLException("Błąd zapytania", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
	}
        
        return ip;
    }
}
