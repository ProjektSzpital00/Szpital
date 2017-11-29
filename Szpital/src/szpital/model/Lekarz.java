package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lekarz 
{
    private IntegerProperty idLekarza;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty pesel;
    private IntegerProperty idStanowiska;
    private StringProperty stanowisko;
    private IntegerProperty idOddzialu;
    private StringProperty oddzial;
    
    public Lekarz(Integer idLekarza, String nazwa, Integer idStanowiska, String stanowisko, Integer idOddzialu, String oddzial)
    {
        this.idLekarza = new SimpleIntegerProperty(idLekarza);
        this.imie = new SimpleStringProperty("");
        this.nazwisko = new SimpleStringProperty(nazwa);
        this.idStanowiska = new SimpleIntegerProperty(idStanowiska);
        this.stanowisko = new SimpleStringProperty(stanowisko);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.oddzial = new SimpleStringProperty(oddzial);
    }
    
    public Lekarz(Integer idLekarza, String imie, String nazwisko, String pesel, Integer idStanowiska, String stanowisko, Integer idOddzialu, String oddzial)
    {
        this.idLekarza = new SimpleIntegerProperty(idLekarza);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.idStanowiska = new SimpleIntegerProperty(idStanowiska);
        this.stanowisko = new SimpleStringProperty(stanowisko);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.oddzial = new SimpleStringProperty(oddzial);
    }
    
    public IntegerProperty getIdLekarza() 
    {
        return idLekarza;
    }

    public void setIdLekarza(IntegerProperty idLekarza) 
    {
        this.idLekarza = idLekarza;
    }

    public StringProperty getImie() 
    {
        return imie;
    }

    public void setImie(StringProperty imie) 
    {
        this.imie = imie;
    }

    public StringProperty getNazwisko() 
    {
        return nazwisko;
    }

    public void setNazwisko(StringProperty nazwisko) 
    {
        this.nazwisko = nazwisko;
    }

    public StringProperty getPesel() 
    {
        return pesel;
    }

    public void setPesel(StringProperty pesel) 
    {
        this.pesel = pesel;
    }

    public IntegerProperty getIdStanowiska() 
    {
        return idStanowiska;
    }

    public void setIdStanowiska(IntegerProperty idStanowiska) 
    {
        this.idStanowiska = idStanowiska;
    }

    public StringProperty getStanowisko() 
    {
        return stanowisko;
    }

    public void setStanowisko(StringProperty stanowisko) 
    {
        this.stanowisko = stanowisko;
    }

    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public void setIdOddzialu(IntegerProperty idOddzialu) 
    {
        this.idOddzialu = idOddzialu;
    }

    public StringProperty getOddzial() 
    {
        return oddzial;
    }

    public void setOddzial(StringProperty oddzial) 
    {
        this.oddzial = oddzial;
    }
}
