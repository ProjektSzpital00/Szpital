package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Badania;
import szpital.model.Pacjent;
import szpital.model.RodzajeBadan;

public class BadaniaUtil {
	private static ObservableList<Badania> badanieList = FXCollections.observableArrayList();
	private static ObservableList<RodzajeBadan> rodzajeBadanieList = FXCollections.observableArrayList();
        
	public static ObservableList<Badania> getBadaniaList(Integer id) throws ClassNotFoundException, SQLException {

		try {
			badanieList.removeAll(badanieList);
			Statement stmt = Laczenie.getStatement();

			String query = "select BadaniaPacjentow.id, Pacjenci.imie, Pacjenci.nazwisko, Badania.nazwa, BadaniaPacjentow.data, BadaniaPacjentow.wynik from "
					+ "BadaniaPacjentow join (Badania, Pacjenci) "
					+ "on BadaniaPacjentow.id_badania=Badania.id AND BadaniaPacjentow.id_pacjenta=Pacjenci.id "
					+ "where Pacjenci.id = " + id.toString() + " order by BadaniaPacjentow.id";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				Badania badania = new Badania(rs.getInt("BadaniaPacjentow.id"), rs.getString("Pacjenci.imie"),
						rs.getString("Pacjenci.nazwisko"), rs.getString("Badania.nazwa"),
						rs.getDate("BadaniaPacjentow.data"), rs.getString("BadaniaPacjentow.wynik"));
				badanieList.add(badania);
			}
		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		}

		return badanieList;
	}

	public static ObservableList<RodzajeBadan> getRodzajeBadanList() throws ClassNotFoundException, SQLException {

		try {
			rodzajeBadanieList.removeAll(rodzajeBadanieList);
			Statement stmt = Laczenie.getStatement();

			String query = "select * from Badania;";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				RodzajeBadan badania = new RodzajeBadan(rs.getInt("Badania.id"), rs.getString("Badania.nazwa"));
				rodzajeBadanieList.add(badania);
			}
		} catch (ClassNotFoundException ex) {
			throw ex;
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		}

		return rodzajeBadanieList;
	}
	
    public static void updateBadaniePacjenta(Badania badania) throws SQLException 
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "update BadaniaPacjentow set id_badania ='"+ badania.getIdNazwaBadania().getValue() +"' , "
            + "id_pacjenta =" +badania.getId_Pacjenta().getValue()
            +", data ='"+ badania.getDataBadania().getValue()+"', wynik = '"+badania.getWynikBadania().getValue()
            +"' where id ="+ badania.getId().getValue()+";";
            
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

	public static void addBadanie(Statement statement, Badania badania) throws SQLException {
		try {
			Statement stmt = Laczenie.getStatement();
			// badania.setId_Pacjenta(new SimpleIntegerProperty(2));
			String query = "insert BadaniaPacjentow (id_badania, id_pacjenta, data, wynik) " + "values ('"
					+ badania.getIdNazwaBadania().getValue() + "','" + badania.getId_Pacjenta().getValue() + "'," + "'"
					+ badania.getDataBadania().getValue() + "','" + badania.getWynikBadania().getValue() + "');";

			// System.out.println(badania.getId().getValue());
			stmt.executeUpdate(query);
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		} catch (ClassNotFoundException ex) {
			Utils.alertWyswietl(ex);
		}
	}
}
