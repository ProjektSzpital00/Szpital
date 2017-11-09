package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Pacjent;

public class ListaPacjentow 
{
    private static ObservableList<Pacjent> pacjentList = FXCollections.observableArrayList();;
    
    public static ObservableList<Pacjent> get()
    {
        Statement stmt = Laczenie.connect();
        
        try 
        {
            String query = "SELECT * FROM Pacjenci";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                Pacjent p = new Pacjent(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("pesel"), rs.getInt("id_lekarza"), rs.getInt("id_oddzialu"), rs.getString("gr_krwi"));
                pacjentList.add(p);
            }
            Laczenie.closeConnection();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(Uwierzytelnianie.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacjentList;
    }
}
