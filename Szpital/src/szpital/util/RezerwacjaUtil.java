package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lekarz;
import szpital.model.Rezerwacja;
import szpital.model.Sala;

public class RezerwacjaUtil 
{
    private static ObservableList<Rezerwacja> rezerwcjaList = FXCollections.observableArrayList();
    private static ObservableList<Rezerwacja> rezerwcjaList2 = FXCollections.observableArrayList();
    
    public static ObservableList<Rezerwacja> getRezerwacjaList(String termin, Integer idSali) throws SQLException, ClassNotFoundException
    { 
        if(rezerwcjaList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM RezerwacjeSali WHERE termin LIKE '"+termin+"%' AND id_sali = "+idSali+"";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Rezerwacja r = new Rezerwacja(rs.getInt("id"), rs.getInt("id_sali"), rs.getInt("id_rezerwujacego"), rs.getString("termin"), rs.getString("informacja"));
                        rezerwcjaList.add(r);
                    }

                    for(Rezerwacja r : rezerwcjaList)
                    {
                        for(Lekarz l : LekarzUtil.getLekarzList())
                        {
                            if(r.getIdRezerwującego().getValue().equals(l.getIdLekarza().getValue()))
                            {
                                r.setRezerwujacy(new SimpleStringProperty(l.getNazwisko().getValue()+" "+l.getImie().getValue()));
                            }
                        }
                    }

                    for(Rezerwacja r : rezerwcjaList)
                    {
                        for(Sala s : SalaUtil.getSalaList())
                        {
                            if(r.getIdSali().getValue().equals(s.getIdSali().getValue()))
                            {
                                r.setSala(new SimpleStringProperty(s.getNazwa().getValue()));
                            }
                        }
                    }
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania (Select RezerwacjaUtil)", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return rezerwcjaList;
    }
    
    public static ObservableList<Rezerwacja> getRezerwacjaList(Integer idRezerwujacego) throws SQLException, ClassNotFoundException
    { 
        if(rezerwcjaList2.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM RezerwacjeSali WHERE id_rezerwujacego = "+idRezerwujacego+" AND termin > '"+LocalDate.now().toString()+"%';";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Rezerwacja r = new Rezerwacja(rs.getInt("id"), rs.getInt("id_sali"), rs.getInt("id_rezerwujacego"), rs.getString("termin"), rs.getString("informacja"));
                        rezerwcjaList2.add(r);
                    }
                    
                    for(Rezerwacja r : rezerwcjaList2)
                    {
                        for(Sala s : SalaUtil.getSalaList())
                        {
                            if(r.getIdSali().getValue().equals(s.getIdSali().getValue()))
                            {
                                r.setSala(new SimpleStringProperty(s.getNazwa().getValue()));
                            }
                        }
                    }
                    
                }
                catch(SQLException ex)
                {
                    throw new SQLException("Błąd zapytania (Select RezerwacjaUtil)", ex);
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        
        return rezerwcjaList2;
    }
    
    public static void clearRezerwacjaList()
    {
        rezerwcjaList.clear();
    }
    
    public static void clearRezerwacjaList2()
    {
        rezerwcjaList2.clear();
    }
    
    public static void addRezerwacja(Statement statement, Rezerwacja rezerwacja) throws SQLException
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "insert into RezerwacjeSali (id_sali, id_rezerwujacego, termin, informacja) "
                            + "Values(" + rezerwacja.getIdSali().getValue() + ", " + rezerwacja.getIdRezerwującego().getValue() + ", '"
                            + rezerwacja.getTermin().getValue() + "', '" + rezerwacja.getInformacja().getValue() + "');";
            stmt.executeUpdate(query);

        } 
        catch (SQLException ex) 
        {
            throw new SQLException("Błąd zapytania (Add RezerwacjaUtil)", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static void updateRezerwacja(Statement statement, Rezerwacja rezerwacja) throws SQLException
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            String query = "UPDATE RezerwacjaSali" + " SET id_sali=" + rezerwacja.getIdSali().getValue() + ", id_rezerwujacego="
                            + rezerwacja.getIdRezerwującego().getValue() + ", termin='" + rezerwacja.getTermin().getValue() + "', informacja='"
                            + rezerwacja.getInformacja().getValue() + "';";
            stmt.executeUpdate(query);

        } 
        catch (SQLException ex) 
        {
            throw new SQLException("Błąd zapytania (Update RezerwacjaUtil)", ex);
        } 
        catch (ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static void deleteRezerwacja(Statement statement, Integer idRezerwacji)
    {
        try 
        {
            Statement stmt = Laczenie.getStatement();

            try 
            {
                String query = "delete from RezrwacjeSali where id =" + idRezerwacji;
                stmt.executeUpdate(query);
            } 
            catch (SQLException ex) 
            {
                throw new SQLException("Błąd zapytania (Delete RezerwacjaUtil)", ex);
            }
        } 
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public static Integer searchRezerwacjaId(Statement statement, String termin)
    {
        Integer ip = null;
        
        try 
        {
            Statement stmt = Laczenie.getStatement();
            
            try 
            {
                String query = "SELECT id FROM RezerwacjeSali WHERE termin = '"+termin+"'";
                
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