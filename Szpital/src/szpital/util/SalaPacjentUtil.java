package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.SalaPacjent;

public class SalaPacjentUtil 
{
    private static ObservableList<SalaPacjent> salaPacjentList = FXCollections.observableArrayList();
    
    public static ObservableList<SalaPacjent> getSalaPacjentList() throws SQLException, ClassNotFoundException
    { 
        if(salaPacjentList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM SaleSzpitalne";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        SalaPacjent s = new SalaPacjent(rs.getInt("id"), rs.getString("nazwa"), rs.getInt("id_oddzialu"));
                        salaPacjentList.add(s);
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
        
        return salaPacjentList;
    }
    
    public static void clearSalaPacjentList()
    {
        salaPacjentList.clear();
    }
    
    public static void addSalaPacjent(SalaPacjent salaPacjent)
    {
        
    }
    
    public static void updateSalaPacjent(SalaPacjent salaPacjent)
    {
        
    }
    
    public static void deleteSalaPacjent(Integer idSalaPacjent)
    {
        
    }
    
    public static Integer searchSalaPacjentId(String nazwaSali)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM SaleSzpitalne WHERE nazwa = '"+nazwaSali+"'";
                
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