package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lekarz;

public class OrdynatorUtil
{
    private static ObservableList<Lekarz> ordynatorList = FXCollections.observableArrayList();

    public static ObservableList<Lekarz> getOrdynatorList() throws SQLException, ClassNotFoundException
    {
        if(ordynatorList.isEmpty())
        {
            try
            {
                Statement stmt = Laczenie.getStatement();

                try
                {
                    String query = "SELECT Lekarze.id, Lekarze.imie, Lekarze.nazwisko, Lekarze.pesel, Stanowiska.id, Stanowiska.nazwa, Oddzialy.id, Oddzialy.nazwa\n" +
                            "FROM Lekarze\n" +
                            "JOIN Stanowiska ON Lekarze.id_stanowiska = 3\n" +
                            "JOIN Oddzialy ON Lekarze.id_oddzialu = Oddzialy.id";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        String imie = rs.getString("Lekarze.imie");
                        String pesel = rs.getString("Lekarze.pesel");
                        Lekarz l;
                        if(imie.equals(" ") || pesel.equals(" "))
                            l = new Lekarz(rs.getInt("Lekarze.id"), rs.getString("Lekarze.nazwisko"), rs.getInt("Stanowiska.id"), rs.getString("Stanowiska.nazwa"), rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"));
                        else
                            l = new Lekarz(rs.getInt("Lekarze.id"), imie, rs.getString("Lekarze.nazwisko"),pesel, rs.getInt("Stanowiska.id"), rs.getString("Stanowiska.nazwa"), rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"));
                        ordynatorList.add(l);
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania lekarz Util", ex);
                }
            }
            catch (SQLException | ClassNotFoundException ex)
            {
                throw ex;
            }
        }

        return ordynatorList;
    }

    public static void clearOrdynatorList()
    {
        ordynatorList.clear();
    }

    public static Integer searchOrdynatorId(Statement statement, String imie, String nazwisko)
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
