package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Pacjent;

public class PacjentUtil
{
    private static ObservableList<Pacjent> pacjentList = FXCollections.observableArrayList();
    
    public static ObservableList<Pacjent> getPacjentList() throws SQLException, ClassNotFoundException
    { 
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT Pacjenci.id, Pacjenci.imie, Pacjenci.nazwisko, Pacjenci.pesel, Lekarze.id, Lekarze.imie, Lekarze.nazwisko, Oddzialy.id, Oddzialy.nazwa, gr_krwi\n" +
                                "FROM Pacjenci\n" +
                                "JOIN Lekarze ON Pacjenci.id_lekarza = Lekarze.id\n" +
                                "JOIN Oddzialy ON Lekarze.id_oddzialu = Oddzialy.id\n" +
                                "ORDER BY Pacjenci.id";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    Pacjent p = new Pacjent(rs.getInt("Pacjenci.id"), rs.getString("Pacjenci.imie"), rs.getString("Pacjenci.nazwisko"), rs.getString("Pacjenci.pesel"), rs.getInt("Lekarze.id"), (rs.getString("Lekarze.nazwisko")+" "+rs.getString("Lekarze.imie")), rs.getInt("Oddzialy.id"), rs.getString("Oddzialy.nazwa"), rs.getString("gr_krwi"));
                    pacjentList.add(p);
                }
            }
            catch(SQLException ex)
            {
                throw new SQLException("B³¹d zapytania", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            throw ex;
        }
        
        return pacjentList;
    }
    
    public static void clearPacjentList()
    {
        pacjentList.clear();
    }
    
    public static void addPacjent(Statement statement, Pacjent pacjent) throws Exception
    {
        try {
        	Statement stmt = Laczenie.getStatement();
        	
        	try {
        	
        		String query="insert into Pacjenci (imie, nazwisko, pesel, id_lekarza, id_oddzialu, gr_krwi) Values('"+
        	pacjent.getImie()+"', '"+pacjent.getNazwisko()+"', '"+pacjent.getPesel()+"',"+pacjent.getIdLekarza()+" , "+pacjent.getIdOddzialu()+", '"+pacjent.getGrKrwii()+"');";
        	
        	ResultSet rs =stmt.executeQuery(query);
        	}catch(SQLException ex){
        		throw new SQLException("B³¹d zapytania", ex);
        	}
        }catch (SQLException | ClassNotFoundException ex) {
			throw ex;
		}
    }
    
    public static void updatePacjent(Statement statement, Pacjent pacjent)
    {

    }
    
    public static void deletePacjent(Statement statement, IntegerProperty idPacjenta) throws Exception
    {
        try {
        	Statement stmt = Laczenie.getStatement();
        	
        	try {
        		
        		String querty="delete from Pacjenci where id = " + idPacjenta;
        		
        		ResultSet rs =stmt.executeQuery(querty);
        	}catch (SQLException ex) {
				throw new SQLException("B³¹d zapytania", ex);
			}
        	
        }catch(SQLException | ClassNotFoundException ex){
        	throw ex;
        }
    }
    
    public static Integer searchPacjentId(Statement statement, String imie, String nazwisko)
    {
        Integer ip = null;
        
        
        return ip;
    }
}
