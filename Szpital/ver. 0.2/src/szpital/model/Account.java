package szpital.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account 
{
    private Integer id_konta;
    private String haslo;
    private String pesel;
    private Integer id_stanowiska;
    private String stanowisko;
    private Integer id_personel;
    private String imie;
    private String nazwisko;
        
    public Account(Integer id_konta, String haslo, Statement stmt)
    {
        this.id_konta = id_konta;
        this.haslo = haslo;
        
        try 
        {
            String query = "SELECT Konta.pesel, Stanowiska.id AS 'id_stanowiska', nazwa, Personel.id AS 'id_personel', imie, nazwisko\n" +
                            "FROM Konta\n" +
                            "JOIN Stanowiska ON Konta.id_stanowiska = Stanowiska.id\n" +
                            "JOIN Personel ON Stanowiska.id = Personel.id_stanowiska\n" +
                            "WHERE Konta.id = "+id_konta+" AND Konta.haslo = '"+haslo+"'";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                this.pesel = rs.getString("pesel");
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
    
    public Integer getId_konta() {
        return id_konta;
    }

    public String getPesel() {
        return pesel;
    }

    public Integer getId_stanowiska() {
        return id_stanowiska;
    }

    public String getStanowisko() {
        return stanowisko;
    }

    public Integer getId_personel() {
        return id_personel;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }
}
