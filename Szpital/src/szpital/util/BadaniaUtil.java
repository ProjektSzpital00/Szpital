package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Badania;
import szpital.model.Pacjent;

public class BadaniaUtil {
	private static ObservableList<Badania> badanieList = FXCollections.observableArrayList();

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
	
	public static void addBadanie(Statement statement, Badania badania) throws SQLException {
		try {
			Statement stmt = Laczenie.getStatement();
			badania.setId_Pacjenta(new SimpleIntegerProperty(2));
			String query = "insert BadaniaPacjentow (id_badania, id_pacjenta, data, wynik) " + 
					"values ('"+badania.getId().getValue()+"','"+ badania.getId_Pacjenta().getValue() +"','"+badania.getDataBadania().getValue()+"','"+badania.getWynikBadania().getValue()+"');";
			
                        //System.out.println(badania.getId().getValue());
                    stmt.executeUpdate(query);
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		} catch (ClassNotFoundException ex) {
			Utils.alertWyswietl(ex);
		}
	}
}
