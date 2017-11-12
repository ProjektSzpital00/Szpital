package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lekarz;

public class ListaLekarzy 
{
    private static ObservableList<Lekarz> lekarzList = FXCollections.observableArrayList();
    
    public static ObservableList<Lekarz> get() throws SQLException, ClassNotFoundException
    { 
        try 
        {
            Statement stmt = Laczenie.connect();
            
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
        finally
        {
            Laczenie.disconnect();
        }
        
        return lekarzList;
    }
    
    public static void clear()
    {
        lekarzList.clear();
    }
}
