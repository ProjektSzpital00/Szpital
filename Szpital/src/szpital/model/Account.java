package szpital.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account 
{
    private String pesel;
    private String haslo;
    private Integer id_stanowiska;
    private String stanowisko;
    private Integer id_personel;
    private Integer id_lekarza;
    private String imie;
    private String nazwisko;
        
    public Account(String login, String haslo, Statement stmt)
    {
        pesel = login;
        this.haslo = haslo;
        
        try 
        {
            String query = "SELECT Stanowiska.id AS 'id_stanowiska', nazwa\n" +
                            "FROM Konta\n" +
                            "JOIN Stanowiska ON Konta.id_stanowiska = Stanowiska.id\n" +
                            "WHERE Konta.pesel = "+pesel+" AND Konta.haslo = '"+this.haslo+"'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                id_stanowiska = rs.getInt("id_stanowiska");
                stanowisko = rs.getString("nazwa");
                
                if(stanowisko.equals("lekarz") || stanowisko.equals("ordyantor"))
                {
                    query = "SELECT id, imie, nazwisko\n" +
                            "FROM Lekarze\n" +
                            "WHERE pesel = "+pesel;
                    rs = stmt.executeQuery(query);
                    if (rs.next())
                    {
                        id_lekarza = rs.getInt("id");
                        imie = rs.getString("imie");
                        nazwisko = rs.getString("nazwisko");
                    }
                }
                else
                {
                    query = "SELECT id, imie, nazwisko\n" +
                            "FROM Personel\n" +
                            "WHERE pesel = "+pesel;
                    rs = stmt.executeQuery(query);
                    if (rs.next())
                    {
                        id_personel = rs.getInt("id");
                        imie = rs.getString("imie");
                        nazwisko = rs.getString("nazwisko");
                    }
                }
            }
        } 
        catch (SQLException ex) 
        {
            System.out.println("Blad zapytania sql");
        }
    }

    public String getPesel() 
    {
        return pesel;
    }

    public Integer getId_stanowiska() 
    {
        return id_stanowiska;
    }

    public String getStanowisko() 
    {
        return stanowisko;
    }

    public Integer getId_personel() 
    {
        return id_personel;
    }

    public Integer getId_lekarza() 
    {
        return id_lekarza;
    }
    
    public String getImie() 
    {
        return imie;
    }

    public String getNazwisko() 
    {
        return nazwisko;
    }
}
