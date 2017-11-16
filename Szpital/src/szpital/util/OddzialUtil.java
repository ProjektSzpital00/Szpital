package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Oddzial;

public class OddzialUtil 
{
    private static ObservableList<Oddzial> oddzialList = FXCollections.observableArrayList();
    
    public static ObservableList<Oddzial> getOddzialList() throws SQLException, ClassNotFoundException
    { 
        if(oddzialList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM Oddzialy";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Oddzial o = new Oddzial(rs.getInt("id"), rs.getString("nazwa"), rs.getInt("l_mijesc"), rs.getInt("l_wolnych_miejsc"));
                        oddzialList.add(o);
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
        
        return oddzialList;
    }
    
    public static void clearOddzialyList()
    {
        oddzialList.clear();
    }
    
    public static void addOddzial(Statement statement, Oddzial oddzial)
    {
        
    }
    
    public static void updateOddzial(Statement statement, Oddzial oddzial)
    {
        
    }
    
    public static void deleteOddzial(Statement statement, IntegerProperty idOddzialu)
    {
        
    }
    
    public static Integer searchOddzialId(Statement statement, String nazwaOddzialu)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM Oddzialy WHERE nazwa = '"+nazwaOddzialu+"'";
                
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
