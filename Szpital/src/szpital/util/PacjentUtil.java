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
                throw new SQLException("Błąd zapytania", ex);
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
    
    public static void addPacjent(Statement statement, Pacjent pacjent)
    {
    	
    	try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
            	String query = "insert into Pacjenci "+ 
                		"Values('"+pacjent.getImie().getValue()+"', '"+pacjent.getNazwisko().getValue()+"', '"+ 
            		pacjent.getPesel().getValue()+"', "+pacjent.getIdLekarza().getValue()+", "+pacjent.getIdOddzialu().getValue()+", '" +pacjent.getGrKrwii().getValue()+"');";
                stmt.executeUpdate(query);
            }
            catch(SQLException ex)
            {
                throw new SQLException("Błąd zapytania (add pacjent)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
	}
    }
    
    public static void updatePacjent(Statement statement, Pacjent pacjent)
    {
    	try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
            	String query = "UPDATE Pacjenci" + 
            			" SET imie='"+pacjent.getImie().getValue()+"', nazwisko='"+pacjent.getNazwisko().getValue()+"', pesel='"+pacjent.getPesel().getValue()+
            			"', id_lekarza="+pacjent.getIdLekarza().getValue()+", id_oddzialu="+pacjent.getIdOddzialu().getValue()+", gr_krwi='"+pacjent.getGrKrwii().getValue()+"'" + 
            			" WHERE id="+pacjent.getIdPacjenta().getValue()+";";
                stmt.executeUpdate(query);
            }
            catch(SQLException ex)
            {
                throw new SQLException("Błąd zapytania (update pacjent)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
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
            catch(SQLException ex)
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
                String query = "SELECT id FROM Pacjenci WHERE Pesel = "+Pesel;
                
                ResultSet rs = stmt.executeQuery(query);
                
                if(rs.next())
                {
                    ip = rs.getInt("id");
                }
            }
            catch(SQLException ex)
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
