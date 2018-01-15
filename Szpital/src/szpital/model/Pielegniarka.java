package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pielegniarka 
{
    private IntegerProperty idPielegniarki;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty pesel;
    private IntegerProperty idOddzialu;
    private StringProperty oddzial;

    public Pielegniarka(Integer idPielegniarki, String imie, String nazwisko, String pesel, Integer idOddzialu) 
    {
        this.idPielegniarki = new SimpleIntegerProperty(idPielegniarki);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
    }

    public IntegerProperty getIdPielegniarki() 
    {
        return idPielegniarki;
    }

    public void setIdPielegniarki(IntegerProperty idPielegniarki) 
    {
        this.idPielegniarki = idPielegniarki;
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
