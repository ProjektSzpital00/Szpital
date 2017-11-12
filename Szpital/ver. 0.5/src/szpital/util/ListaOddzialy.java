package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import szpital.model.Oddzial;

public class ListaOddzialy 
{
    private static ObservableList<Oddzial> oddzialList = FXCollections.observableArrayList();
    
    public static ObservableList<Oddzial> get() throws SQLException, ClassNotFoundException
    { 
        try 
        {
            Statement stmt = Laczenie.connect();
            
            try 
            {
                String query = "SELECT * FROM Oddzialy";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    Oddzial o = new Oddzial(rs.getInt("id"), rs.getString("nazwa"), rs.getInt("l_mijesc"), rs.getInt("l_wolnych_miejsc"));
                    oddzialList.add(o);
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
        finally
        {
            Laczenie.disconnect();
        }
        
        return oddzialList;
    }
    
    public static void clear()
    {
        oddzialList.clear();
    }
}
