package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Lozko;
import szpital.model.Oddzial;
import szpital.model.SalaPacjent;

public class LozkoUtil 
{
    private static ObservableList<Lozko> lozkoList = FXCollections.observableArrayList();
    
    public static ObservableList<Lozko> getLozkoList() throws SQLException, ClassNotFoundException
    { 
        if(lozkoList.isEmpty())
        {
            try 
            {
                Statement stmt = Laczenie.getStatement();

                try 
                {
                    String query = "SELECT * FROM Lozka";
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next())
                    {
                        Lozko l = new Lozko(rs.getInt("id"), rs.getInt("id_Sali"), rs.getInt("wolne"));
                        lozkoList.add(l);
                    }
                    
                    for(Lozko l : lozkoList)
                    {
                        for(SalaPacjent sP : SalaPacjentUtil.getSalaPacjentList())
                        {
                            if (l.getIdSali().getValue().equals(sP.getIdSali().getValue())) 
                            {
                                for(Oddzial o : OddzialUtil.getOddzialList())
                                {
                                    if(sP.getIdOddzialu().getValue().equals(o.getIdOddzialu().getValue()))
                                    {
                                        l.setIdOddzialu(new SimpleIntegerProperty(o.getIdOddzialu().getValue()));
                                        break;
                                    }
                                }
                                break;
                            }
                        }
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
        }
        
        return lozkoList;
    }
    
    public static void clearLozkoList()
    {
        lozkoList.clear();
    }
    
    public static void zmiannaStatusuLozka (Integer status, Integer idLozka) throws SQLException, ClassNotFoundException 
    {
        try
        {
            Statement stmt = Laczenie.getStatement();
            String query ="UPDATE Lozka SET wolne = "+ status.toString()+"WHERE id = "+idLozka+";";
            stmt.executeQuery(query);
        }
        catch (SQLException ex)
        {
            throw new SQLException("Błąd zapytania", ex);
        }
        catch (ClassNotFoundException ex)
        {
            throw ex;
        }
    }
}
