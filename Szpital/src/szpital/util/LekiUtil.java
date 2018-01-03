package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Badania;
import szpital.model.Leki;
import szpital.model.RodzajeBadan;
import szpital.model.RodzajeLekow;

public class LekiUtil {
    private static ObservableList<Leki> lekiList = FXCollections.observableArrayList();
    private static ObservableList<RodzajeLekow> rodzajeLekowList = FXCollections.observableArrayList();

    public static ObservableList<Leki> getLekiList(Integer id) throws SQLException, ClassNotFoundException{
            lekiList.removeAll(lekiList);
            try {
                    Statement stmt = Laczenie.getStatement();
                    String query = "Select LekiPacjentow.id, LekiPacjentow.id_pacjenta, Leki.nazwa, LekiPacjentow.od_termin, LekiPacjentow.do_temin, LekiPacjentow.dawkowanie from LekiPacjentow " + 
                                    "join (Leki, Pacjenci) " + 
                                    "on LekiPacjentow.id_leku=Leki.id AND LekiPacjentow.id_pacjenta=Pacjenci.id " + 
                                    "where Pacjenci.id="+id.toString()+";";
                    ResultSet rs;
                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                            Leki leki = new Leki(rs.getInt("LekiPacjentow.id"),rs.getInt("LekiPacjentow.id_pacjenta"),rs.getString("Leki.nazwa"),
                                            rs.getDate("LekiPacjentow.od_termin"),rs.getDate("LekiPacjentow.do_temin"), 
                                            rs.getString("LekiPacjentow.dawkowanie"));
                            lekiList.add(leki);
                    }
            } catch (SQLException e) {
                    throw e;
            } catch (ClassNotFoundException e) {
                    throw e;
            }

            return lekiList;
    }

    public static ObservableList<RodzajeLekow> getRodzajeLekowList() throws ClassNotFoundException, SQLException {

            try {
                    rodzajeLekowList.removeAll(rodzajeLekowList);
                    Statement stmt = Laczenie.getStatement();

                    String query = "select * from Leki;";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                            RodzajeLekow lek = new RodzajeLekow(rs.getInt("Leki.id"), rs.getString("Leki.nazwa"));
                            rodzajeLekowList.add(lek);
                    }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                    //throw ex;
            } catch (SQLException ex) {
                ex.printStackTrace();
                    //throw new SQLException("Błąd zapytania", ex);
            }

            return rodzajeLekowList;
    }
    
    public static void addLekPacjenta(Statement statement, Leki lek) throws SQLException {
		try {
			Statement stmt = Laczenie.getStatement();
			
			String query = "insert LekiPacjentow (id_leku, id_pacjenta, od_termin, do_temin, dawkowanie) " + "values ('"
					+ lek.getIdNazwaLeku().intValue() + "','" + lek.getIdPacjenta().getValue() + "'," + "'"
					+ lek.getOdTermin().getValue() + "','" + lek.getDoTermin().getValue() + "','" + lek.getDawkowanie().getValue()  + "');";

			
			stmt.executeUpdate(query);
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		} catch (ClassNotFoundException ex) {
			Utils.alertWyswietl(ex);
		}
	}
    
}
