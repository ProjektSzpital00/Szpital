package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Statystyka;

public class StatystykaUtil
{
    private static ObservableList<Statystyka> StatystykaList = FXCollections.observableArrayList();
     
     public static ObservableList<Statystyka> getStatystykaList() throws SQLException, ClassNotFoundException
    { 
        if(StatystykaList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();
                try 
                {
                    String query = "SELECT Oddzialy.Nazwa, SaleSzpitalne.Nazwa, Lozka.Id, Lozka.wolne\n" +
                                    "FROM Oddzialy\n" +
                                    "JOIN SaleSzpitalne ON Oddzialy.id = SaleSzpitalne.Id_oddzialu\n" +
                                    "JOIN Lozka ON SaleSzpitalne.Id = Lozka.Id_Sali";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Statystyka s;
                        s = new Statystyka(rs.getString("Oddzialy.Nazwa"),rs.getString("SaleSzpitalne.Nazwa"),rs.getInt("Lozka.Id"), rs.getInt("Lozka.wolne"));
                        StatystykaList.add(s);
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania sstat Util", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return StatystykaList;
    }
}
