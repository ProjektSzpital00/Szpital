package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
;
import szpital.model.Rezerwacja;

public class RezerwacjaUtil 
{
    private static HashMap<ArrayList<HashMap<String, HashMap<String, Rezerwacja>>>, Integer> listaRezerwacjiDlaWszystkichSal;
    private static ArrayList<HashMap<String, HashMap<String, Rezerwacja>>> listaRezerwacjiDlaSali; 
    private static HashMap<String, HashMap<String, Rezerwacja>> listaRezerwacjiDni;
    private static HashMap<String, Rezerwacja> listaRezerwacjiGodziny;
    
    /*public static HashMap<Rezerwacja, String> getListaRezerwacjiGodziny() throws SQLException, ClassNotFoundException
    {
        if(listaRezerwacjiGodziny.isEmpty())
        {
            try
            {
                Statement stmt = Laczenie.getStatement();
                
                ArrayList<Integer> saleIdArray = new ArrayList<Integer>();
                String tempQ = "SELECT id FROM Sale";
                ResultSet rs = stmt.executeQuery(tempQ);
                while (rs.next())
                {
                    saleIdArray.add(rs.getInt("id"));
                }
                
                ArrayList<Rezerwacja> rezerwacjeWgSali = new ArrayList<Rezerwacja>();
                HashMap<Integer, ArrayList<Rezerwacja>> rezerwacjePoSalach = new HashMap<Integer, ArrayList<Rezerwacja>>();
                for(Integer id : saleIdArray)
                {
                    tempQ = "SELECT * FROM RezerwacjeSali"
                        + "WHERE id_sali = "+id;
                    rs = stmt.executeQuery(tempQ);
                    while (rs.next())
                    {
                        Rezerwacja s = new Rezerwacja(rs.getInt("id"), rs.getInt("id_sali"), rs.getInt("id_rezerwujacego"), rs.getString("termin"), rs.getString("infromacja"));
                        rezerwacjeWgSali.add(s);
                    }
                    rezerwacjePoSalach.put(id, rezerwacjeWgSali);
                    rezerwacjeWgSali = new ArrayList<Rezerwacja>();
                }
                
                TreeSet<String> tempForComp = new TreeSet<String>();
                for(Map.Entry<Integer, ArrayList<Rezerwacja>> entry : rezerwacjePoSalach.entrySet()) 
                {
                    Integer k = entry.getKey();
                    ArrayList<Rezerwacja> value = entry.getValue();
                    for(Rezerwacja r : value)
                        tempForComp.add(r.getTermin().getValue());
                }
                    
                ArrayList<Rezerwacja> rezerwacjeWgDni = new ArrayList<Rezerwacja>();
                HashMap<String, ArrayList<Rezerwacja>> rezerwacjePoDniach = new HashMap<String, ArrayList<Rezerwacja>>();
                HashMap<Integer, HashMap<String, ArrayList<Rezerwacja>>> rezerwacjePoSalach2 = new HashMap<Integer, HashMap<String, ArrayList<Rezerwacja>>>();
                
                for(Map.Entry<Integer, ArrayList<Rezerwacja>> entry : rezerwacjePoSalach.entrySet()) 
                {
                    String termin = "";
                    Integer k = entry.getKey();
                    ArrayList<Rezerwacja> value = entry.getValue();
                    Iterator it = tempForComp.iterator();
                    while(it.hasNext())
                    {
                        for(int i = 0; i < value.size(); i++)
                        {
                            termin = value.get(i).getTermin().getValue();
                            if(termin.regionMatches(0, it.next().toString(), 0, 10))
                            {
                                rezerwacjeWgDni.add(value.get(i));
                            }
                        }
                        rezerwacjePoDniach.put(termin, rezerwacjeWgDni);
                        rezerwacjeWgDni = new ArrayList<Rezerwacja>();
                    }
                    rezerwacjePoSalach2.put(k, rezerwacjePoDniach);
                    rezerwacjePoDniach = new HashMap<String, ArrayList<Rezerwacja>>();
                }
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                throw ex;
            }
        }
        
        return listaRezerwacjiGodziny;
    } */
    
    public static void clearSalaList()
    {
        //clear();
    }
    
    public static void addRezerwacja(Statement statement, Rezerwacja rezerwacja)
    {
        
    }
    
    public static void updateRezerwacja(Statement statement, Rezerwacja rezerwacja)
    {
        
    }
    
    public static void deleteRezerwacja(Statement statement, Integer idRezerwacji)
    {
        
    }
}