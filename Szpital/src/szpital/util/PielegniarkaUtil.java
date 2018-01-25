package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Oddzial;
import szpital.model.Pielegniarka;

public class PielegniarkaUtil 
{
    private static ObservableList<Pielegniarka> pielegniarkaList = FXCollections.observableArrayList();
    
    public static ObservableList<Pielegniarka> getPielegniarkaList() throws SQLException, ClassNotFoundException
    { 
        if(pielegniarkaList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT id, imie, nazwisko, pesel, id_oddzialu "
                            + "FROM Personel WHERE id_stanowiska = 4;";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Pielegniarka p = new Pielegniarka(rs.getInt("id"), rs.getString("imie"), rs.getString("nazwisko"), rs.getString("pesel"), rs.getInt("id_oddzialu"));
                        pielegniarkaList.add(p);
                    }
                    
                    for(Pielegniarka p : pielegniarkaList)
                    {
                        for(Oddzial o : OddzialUtil.getOddzialList())
                        {
                            if(p.getIdOddzialu().getValue().equals(o.getIdOddzialu().getValue()))
                            {
                                p.setOddzial(new SimpleStringProperty(o.getNazwaOddzialu().getValue()));
                            }
                        }
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania [Pielegniarka Util]", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return pielegniarkaList;
    }
    
    public static void clearPielegniarkaList()
    {
        pielegniarkaList.clear();
    }
    
    public static Integer searchPielegniarkaId(String imie, String nazwisko)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM Personel WHERE imie = '"+imie+"' AND nazwisko = '"+nazwisko+"' AND id_stanowiska = 4";
                
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
