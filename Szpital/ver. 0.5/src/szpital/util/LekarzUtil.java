package szpital.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lekarz;

public class LekarzUtil 
{
    private static ObservableList<Lekarz> lekarzList = FXCollections.observableArrayList();
    
    public static ObservableList<Lekarz> getLekarzList() throws SQLException, ClassNotFoundException
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
        
        
        return ip;
    }
}