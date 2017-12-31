package szpital.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Miejsca;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MiejscaUtil {

    private static ObservableList<Miejsca> MiejscatList = FXCollections.observableArrayList();
    private static ObservableList<String> SaleList = FXCollections.observableArrayList();
    private static ObservableList<String> LozkaList = FXCollections.observableArrayList();

    public static ObservableList<Miejsca> getMiejscatList () throws SQLException, ClassNotFoundException {

        try{
            Statement stmt = Laczenie.getStatement();

            String query = "select SaleSzpitalne.id_Oddzialu, SaleSzpitalne.id, Lozka.id, Lozka.wolne " +
                    "from SaleSzpitalne join Lozka on SaleSzpitalne.Id=Lozka.Id where Lozka.wolne = true;";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Miejsca miejsca = new Miejsca(rs.getInt("SaleSzpitalne.id_Oddzialu"),rs.getInt("SaleSzpitalne.id"),
                        rs.getInt("Lozka.id"));
                MiejscatList.add(miejsca);
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

    public ObservableList<String> getSaleList (Integer idSali) throws SQLException, ClassNotFoundException {
        SaleList.clear();
        try{
            Statement stmt = Laczenie.getStatement();

            String query = "select SaleSzpitalne.id " +
                    "from SaleSzpitalne join Lozka on SaleSzpitalne.Id=Lozka.Id where Lozka.wolne = true and SaleSzpitalne.id_Oddzialu= "+idSali.toString()+";";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
               SaleList.add(rs.getString("SaleSzpitalne.id"));
            }
        }catch (SQLException ex)
        {
            throw new SQLException("Błąd zapytania", ex);
        }
        catch (ClassNotFoundException ex)
        {
            throw ex;
        }

        return SaleList;
    }

    public ObservableList<String> getLozkaList (String idOddzialu) throws SQLException, ClassNotFoundException {

        LozkaList.clear();
        try{
            Statement stmt = Laczenie.getStatement();

            String query = "select Lozka.Id from SaleSzpitalne join Lozka on SaleSzpitalne.Id=Lozka.Id where Lozka.wolne = true and SaleSzpitalne.id_Oddzialu= "+idOddzialu+";";

            ResultSet rs= stmt.executeQuery(query);

            while (rs.next()) {
                LozkaList.add(rs.getString("Lozka.Id"));
            }



        }catch (SQLException ex)
        {
            throw new SQLException("Błąd zapytania", ex);
        }
        catch (ClassNotFoundException ex)
        {
            throw ex;
        }

        return LozkaList;
    }
}
