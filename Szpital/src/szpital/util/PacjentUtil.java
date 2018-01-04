package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Pacjent;
import szpital.model.SalaPacjent;

public class PacjentUtil 
{
    private static ObservableList<Pacjent> pacjentList = FXCollections.observableArrayList();

    public static ObservableList<Pacjent> getPacjentList() throws SQLException, ClassNotFoundException 
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "SELECT Pacjenci.id, Pacjenci.imie, Pacjenci.nazwisko, Pacjenci.pesel, Lekarze.id, Lekarze.imie, Lekarze.nazwisko,"
            		+ " Oddzialy.id, Oddzialy.nazwa, gr_krwi, nr_sali, nr_lozka\n"
                            + "FROM Pacjenci\n" + "JOIN Lekarze ON Pacjenci.id_lekarza = Lekarze.id\n"
                            + "JOIN Oddzialy ON Lekarze.id_oddzialu = Oddzialy.id\n" + "ORDER BY Pacjenci.id";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String lekarzNazwisko = rs.getString("Lekarze.nazwisko");
                Pacjent p;
                if(lekarzNazwisko.equals("nieznany"))
                {
                    p = new Pacjent(rs.getInt("Pacjenci.id"), rs.getString("Pacjenci.imie"),
                                    rs.getString("Pacjenci.nazwisko"), rs.getString("Pacjenci.pesel"), rs.getInt("Lekarze.id"),
                                    (lekarzNazwisko+" "),
                                    rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"), rs.getString("gr_krwi"), rs.getInt("nr_sali"), rs.getInt("nr_lozka"));
                }
                else
                {
                    p = new Pacjent(rs.getInt("Pacjenci.id"), rs.getString("Pacjenci.imie"),
                                    rs.getString("Pacjenci.nazwisko"), rs.getString("Pacjenci.pesel"), rs.getInt("Lekarze.id"),
                                    (lekarzNazwisko + " " + rs.getString("Lekarze.imie")),
                                    rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"), rs.getString("gr_krwi"),rs.getInt("nr_sali"), rs.getInt("nr_lozka"));
                }
                pacjentList.add(p);
            }
            
            for(Pacjent pp : pacjentList)
            {
                for(SalaPacjent sP : SalaPacjentUtil.getSalaPacjentList())
                {
                    if(pp.getNrSali().getValue().equals(sP.getIdSali().getValue()))
                    {
                        pp.setNazwaSali(new SimpleStringProperty(sP.getNazwa().getValue()));
                        break;
                    }
                }
            }

        } 
        catch (SQLException ex) 
        {
            throw new SQLException("Błąd zapytania", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            throw ex;
        }

        return pacjentList;
    }

    public static void clearPacjentList() 
    {
        pacjentList.clear();
    }

    public static void addPacjent(Statement statement, Pacjent pacjent) throws SQLException 
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "insert into Pacjenci (imie, nazwisko, pesel, id_lekarza, id_oddzialu, gr_krwi, nr_sali, nr_lozka) "
                            + "Values('" + pacjent.getImie().getValue() + "', '" + pacjent.getNazwisko().getValue() + "', '"
                            + pacjent.getPesel().getValue() + "', " + pacjent.getIdLekarza().getValue() + ", "
                            + pacjent.getIdOddzialu().getValue() + ", '" + pacjent.getGrKrwii().getValue() + "', "
                            + pacjent.getNrSali().getValue()+","+pacjent.getNrLozka().getValue()+");";
            stmt.executeUpdate(query);

        } 
        catch (SQLException ex) 
        {
            throw new SQLException("Błąd zapytania (add pacjent)", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }

    public static void updatePacjent(Statement statement, Pacjent pacjent) throws SQLException 
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "UPDATE Pacjenci" + " SET imie='" + pacjent.getImie().getValue() + "', nazwisko='"
                            + pacjent.getNazwisko().getValue() + "', pesel='" + pacjent.getPesel().getValue() + "', id_lekarza="
                            + pacjent.getIdLekarza().getValue() + ", id_oddzialu=" + pacjent.getIdOddzialu().getValue()
                            + ", gr_krwi='" + pacjent.getGrKrwii().getValue() + "', nr_sali="+ pacjent.getNrSali().getValue()+",nr_lozka="+pacjent.getNrLozka().getValue()
                            + " WHERE id=" + pacjent.getIdPacjenta().getValue() + ";";
            System.out.println(query);
            stmt.executeUpdate(query);

        } 
        catch (SQLException ex) 
        {
            throw new SQLException("Błąd zapytania (update pacjent)", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }

    public static void deletePacjent(Statement statement, IntegerProperty idPacjenta) 
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query = "delete from Pacjenci where id =" + idPacjenta.getValue();
                stmt.executeUpdate(query);
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (delete pacjent)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }

    public static Integer searchPacjentId(Statement statement, String Pesel) 
    {
        Integer ip = null;

        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query = "SELECT id FROM Pacjenci WHERE Pesel = " + Pesel;

                ResultSet rs = stmt.executeQuery(query);

                if (rs.next()) 
                {
                    ip = rs.getInt("id");
                }
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (search id pacjenta)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }

        return ip;
    }
}
