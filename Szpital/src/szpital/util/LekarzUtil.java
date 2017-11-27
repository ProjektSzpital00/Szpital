package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lekarz;
import szpital.model.Pacjent;

public class LekarzUtil 
{
    private static ObservableList<Lekarz> lekarzList = FXCollections.observableArrayList();
    private static ObservableList<Pacjent> pacjentList = FXCollections.observableArrayList();
    
    public static ObservableList<Lekarz> getLekarzList() throws SQLException, ClassNotFoundException
    { 
        if(lekarzList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT Lekarze.id, Lekarze.imie, Lekarze.nazwisko, Lekarze.pesel, Stanowiska.id, Stanowiska.nazwa, Oddzialy.id, Oddzialy.nazwa\n" +
                                    "FROM Lekarze\n" +
                                    "JOIN Stanowiska ON Lekarze.id_stanowiska = Stanowiska.id\n" +
                                    "JOIN Oddzialy ON Lekarze.id_oddzialu = Oddzialy.id";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Lekarz l = new Lekarz(rs.getInt("Lekarze.id"), rs.getString("Lekarze.imie"), rs.getString("Lekarze.nazwisko"), rs.getString("Lekarze.pesel"), rs.getInt("Stanowiska.id"), rs.getString("Stanowiska.nazwa"), rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"));
                        lekarzList.add(l);
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
        
        return lekarzList;
    }
    
    public static void clearLekarzList()
    {
        lekarzList.clear();
    }
    
    public static void addLekarz(Statement statement, Lekarz lekarz)
    {
        
    }
    
    public static void updateLekarz(Statement statement, Lekarz lekarz)
    {
        
    }
    
    public static void deleteLekarz(Statement statement, IntegerProperty idLekarza)
    {
        
    }
    
    public static Integer searchLekarzId(Statement statement, String imie, String nazwisko)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM Lekarze WHERE imie = '"+imie+"' AND nazwisko = '"+nazwisko+"'";
                
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
