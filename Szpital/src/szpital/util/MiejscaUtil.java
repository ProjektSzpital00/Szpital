package szpital.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Miejsca;
import szpital.model.Pacjent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiejscaUtil {

    private static ObservableList<Miejsca> MiejscatList = FXCollections.observableArrayList();
    private static ObservableList<Miejsca> ZajeteMiejscatList = FXCollections.observableArrayList();

    public static ObservableList<Miejsca> getMiejscatList () throws SQLException, ClassNotFoundException {

        try{
            Statement stmt = Laczenie.getStatement();

            String query = "select SaleSzpitalne.id_Oddzialu, SaleSzpitalne.id, Lozka.id, SaleSzpitalne.nrSali, Lozka.nrLozka " +
                    "from SaleSzpitalne join Lozka on SaleSzpitalne.Id=Lozka.Id";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Miejsca miejsca = new Miejsca(rs.getInt("SaleSzpitalne.id_Oddzialu"),rs.getInt("SaleSzpitalne.id"),
                        rs.getInt("Lozka.id"),rs.getString("SaleSzpitalne.nrSali"),rs.getString("Lozka.nrLozka"));
            }
        }catch (SQLException ex)
        {
            throw new SQLException("Błąd zapytania", ex);
        }
        catch (ClassNotFoundException ex)
        {
            throw ex;
        }

        return MiejscatList;
    }

    public static ObservableList<Miejsca> getZajeteMiejscatList () throws SQLException, ClassNotFoundException {

        try{
            Statement stmt = Laczenie.getStatement();

            String query = "";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Miejsca miejsca = new Miejsca(rs.getInt("SaleSzpitalne.id_Oddzialu"),rs.getInt("SaleSzpitalne.id"),
                        rs.getInt("Lozka.id"),rs.getString("SaleSzpitalne.nrSali"),rs.getString("Lozka.nrLozka"));
            }
        }catch (SQLException ex)
        {
            throw new SQLException("Błąd zapytania", ex);
        }
        catch (ClassNotFoundException ex)
        {
            throw ex;
        }

        return ZajeteMiejscatList;
    }
}
