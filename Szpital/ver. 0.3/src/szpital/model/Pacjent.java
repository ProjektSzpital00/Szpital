package szpital.model;

public class Pacjent 
{
    private Integer idPacjenta;
    private String imie;
    private String nazwisko;
    private String pesel;
    private Integer idLekarza;
    private Integer idOddzialu;
    private String grKrwii;

    public Pacjent(Integer idPacjenta, String imie, String nazwisko, String pesel, Integer idLekarza, Integer idOddzialu, String grKrwii) 
    {
        this.idPacjenta = idPacjenta;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.idLekarza = idLekarza;
        this.idOddzialu = idOddzialu;
        this.grKrwii = grKrwii;
    }
    
    public Integer getIdPacjenta() 
    {
        return idPacjenta;
    }

    public String getImie() 
    {
        return imie;
    }

    public String getNazwisko() 
    {
        return nazwisko;
    }

    public String getPesel() 
    {
        return pesel;
    }

    public Integer getIdLekarza() 
    {
        return idLekarza;
    }

    public Integer getIdOddzialu() 
    {
        return idOddzialu;
    }

    public String getGrKrwii() 
    {
        return grKrwii;
    }
}
