package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Dyzur;
import szpital.model.Lekarz;
import szpital.model.Pielegniarka;

public class DyzurUtil 
{
    private static ObservableList<Dyzur> dyzurList = FXCollections.observableArrayList();
    
    public static ObservableList<Dyzur> getDyzurList() throws SQLException, ClassNotFoundException
    { 
        if(dyzurList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM DyzuryLekarzy";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Dyzur d = new Dyzur(rs.getInt("id"), rs.getString("data_od"), rs.getString("data_do"), rs.getInt("id_lekarza"), true);
                        dyzurList.add(d);
                    }
                    
                    for(Dyzur d : dyzurList)
                    {
                        for(Lekarz l : LekarzUtil.getLekarzList())
                        {
                            if(d.getIdLekarza() != null && d.getIdLekarza().getValue().equals(l.getIdLekarza().getValue()))
                            {
                                d.setLekarzDyzurujacy(new SimpleStringProperty(l.getNazwisko().getValue()+" "+l.getImie().getValue()));
                                d.setIdOddzialu(new SimpleIntegerProperty(l.getIdOddzialu().getValue()));
                                d.setOddzial(new SimpleStringProperty(l.getOddzial().getValue()));
                            }
                        }
                    }
                    
                    query = "SELECT * FROM DyzuryPielegniarek";
                    rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Dyzur d = new Dyzur(rs.getInt("id"), rs.getString("data_od"), rs.getString("data_do"), rs.getInt("id_pielegniarki"), false);
                        dyzurList.add(d);
                    }
                    
                    for(Dyzur d : dyzurList)
                    {
                        for(Pielegniarka p : PielegniarkaUtil.getPielegniarkaList())
                        {
                            if(d.getIdPielęgniarki() != null && d.getIdPielęgniarki().getValue().equals(p.getIdPielegniarki().getValue()))
                            {
                                d.setPielegniarkaDyzurujaca(new SimpleStringProperty(p.getNazwisko().getValue()+" "+p.getImie().getValue()));
                                d.setIdOddzialu(new SimpleIntegerProperty(p.getIdOddzialu().getValue()));
                                d.setOddzial(new SimpleStringProperty(p.getOddzial().getValue()));
                            }
                        }
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania (Select DyzurUtil)", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return dyzurList;
    }
    
    public static ObservableList<Dyzur> getDyzurList(String oddzialu) throws SQLException, ClassNotFoundException
    { 
        if(dyzurList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM DyzuryLekarzy";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Dyzur d = new Dyzur(rs.getInt("id"), rs.getString("data_od"), rs.getString("data_do"), rs.getInt("id_lekarza"), true);
                        dyzurList.add(d);
                    }
                    
                    for(Dyzur d : dyzurList)
                    {
                        for(Lekarz l : LekarzUtil.getLekarzList())
                        {
                            if((d.getIdLekarza().getValue().equals(l.getIdLekarza().getValue())) && (l.getOddzial().getValue().equals(oddzialu)))
                            {
                                d.setLekarzDyzurujacy(new SimpleStringProperty(l.getNazwisko().getValue()+" "+l.getImie().getValue()));
                                d.setIdOddzialu(new SimpleIntegerProperty(l.getIdOddzialu().getValue()));
                                d.setOddzial(new SimpleStringProperty(l.getOddzial().getValue()));
                            }
                        }
                    }
                    
                    query = "SELECT * FROM DyzuryPielegniarek";
                    rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Dyzur d = new Dyzur(rs.getInt("id"), rs.getString("data_od"), rs.getString("data_do"), rs.getInt("id_pielegniarki"), false);
                        dyzurList.add(d);
                    }
                    
                    for(Dyzur d : dyzurList)
                    {
                        for(Pielegniarka p : PielegniarkaUtil.getPielegniarkaList())
                        {
                            if((d.getIdPielęgniarki() != null && d.getIdPielęgniarki().getValue().equals(p.getIdPielegniarki().getValue())) && (p.getOddzial().getValue().equals(oddzialu)))
                            {
                                d.setPielegniarkaDyzurujaca(new SimpleStringProperty(p.getNazwisko().getValue()+" "+p.getImie().getValue()));
                                d.setIdOddzialu(new SimpleIntegerProperty(p.getIdOddzialu().getValue()));
                                d.setOddzial(new SimpleStringProperty(p.getOddzial().getValue()));
                            }
                        }
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania (Select DyzurUtil)", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return dyzurList;
    }
    
    public static void clearDyzurList()
    {
        dyzurList.clear();
    }
    
    public static void addLekarz(Dyzur dyzur, Boolean czyLekarz)
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query;
                if(czyLekarz)
                    query = "INSERT INTO DyzuryLekarzy (id, id_lekarza, data_od, data_do) "
                            + "VALUES (NULL, " + dyzur.getIdLekarza().getValue() + ", '" + dyzur.getTerminOd().getValue() + "', '" + dyzur.getTerminDo().getValue() + "');";
                else
                    query = "INSERT INTO DyzuryPielegniarek (id, id_pielegniarki, data_od, data_do) "
                            + "VALUES (NULL, " + dyzur.getIdPielęgniarki().getValue() + ", '" + dyzur.getTerminOd().getValue() + "', '" + dyzur.getTerminDo().getValue() + "');";
                
                stmt.executeUpdate(query);
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (Delete DyzurUtil)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static void updateLekarz(Dyzur dyzur, Boolean czyLekarz)
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query;
                if(czyLekarz)
                    query = "UPDATE DyzuryLekarzy SET data_od = " + dyzur.getTerminOd().getValue() 
                            + ", data_do = " + dyzur.getTerminDo().getValue() + "WHERE id = " + dyzur.getIdDyzuru().getValue();
                else
                    query = "UPDATE DyzuryPielegniarek SET data_od = " + dyzur.getTerminOd().getValue() 
                            + ", data_do = " + dyzur.getTerminDo().getValue() + "WHERE id = " + dyzur.getIdDyzuru().getValue();
                
                stmt.executeUpdate(query);
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (Delete DyzurUtil)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static void deleteDyzurPersonelu(IntegerProperty idDyzuru, Boolean czyLekarz)
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query;
                if(czyLekarz)
                    query = "delete from DyzuryLekarzy where id =" + idDyzuru;
                else
                    query = "delete from DyzuryPielegniarek where id =" + idDyzuru;
                stmt.executeUpdate(query);
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (Delete DyzurUtil)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static Integer searchDyzurId(String terminOd, String terminDo, Boolean czyLekarz)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query;
                if(czyLekarz)
                    query = "SELECT id FROM DyzuryLekarzy WHERE data_od = "+terminOd+" AND data_do = "+terminDo;
                else
                    query = "SELECT id FROM DyzuryPielegniarek WHERE data_od = "+terminOd+" AND data_do = "+terminDo;
                
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
