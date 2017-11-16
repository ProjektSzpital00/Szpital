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
    private String imie;
    private String nazwisko;
        
    public Account(String login, String haslo, Statement stmt)
    {
        this.pesel = login;
        this.haslo = haslo;
        
        try 
        {
            String query = "SELECT Stanowiska.id AS 'id_stanowiska', nazwa, Personel.id AS 'id_personel', imie, nazwisko\n" +
                            "FROM Konta\n" +
                            "JOIN Stanowiska ON Konta.id_stanowiska = Stanowiska.id\n" +
                            "JOIN Personel ON Stanowiska.id = Personel.id_stanowiska\n" +
                            "WHERE Konta.pesel = "+login+" AND Konta.haslo = '"+haslo+"'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                this.id_stanowiska = rs.getInt("id_stanowiska");
                this.stanowisko = rs.getString("nazwa");
                this.id_personel = rs.getInt("id_personel");
                this.imie = rs.getString("imie");
                this.nazwisko = rs.getString("nazwisko");
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

    public String getImie() 
    {
        return imie;
    }

    public String getNazwisko() 
    {
        return nazwisko;
    }
}
