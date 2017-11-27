package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PUBLIC_MEMBER;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Badania;
import szpital.model.Leki;

public class LekiUtil {
	private static ObservableList<Leki> lekiList = FXCollections.observableArrayList();
	
	public static ObservableList<Leki> getLekiList(Integer id) throws SQLException, ClassNotFoundException{
		
		try {
			Statement stmt = Laczenie.getStatement();
			String query = "Select LekiPacjentow.id, Leki.nazwa, LekiPacjentow.od_termin, LekiPacjentow.do_temin, LekiPacjentow.dawkowanie from LekiPacjentow " + 
					"join (Leki, Pacjenci) " + 
					"on LekiPacjentow.id_leku=Leki.id AND LekiPacjentow.id_pacjenta=Pacjenci.id " + 
					"where Pacjenci.id="+id.toString()+";";
			ResultSet rs;
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Leki leki = new Leki(rs.getInt("LekiPacjentow.id"),rs.getString("Badania.nazwa"),
						rs.getDate("BadaniaPacjentow.data"),rs.getDate("BadaniaPacjentow.data"), 
						rs.getString("Badania.nazwa"));
				lekiList.add(leki);
			}
		} catch (SQLException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		}
		
		return lekiList;
	}
	
	public void addLek(Integer idLeku, Integer idPacjenata, Leki leki) throws SQLException {
		try {
			Statement stmt = Laczenie.getStatement();
			
			String query = "Insert LekiPacjentow(id_leku, id_pacjenta, od_termin, do_temin, dawkowanie) " + 
					"values ("+idLeku.intValue()+","+idPacjenata.intValue()+
					",'"+leki.getOdTermin().toString()+"','"+leki.getDoTermin().toString()
					+"','"+leki.getDawkowanie().toString()+"');";
		} catch (SQLException ex) {
			throw new SQLException("Błąd zapytania", ex);
		} catch (ClassNotFoundException ex) {
			Utils.alertWyswietl(ex);
		}
	}
}
