package szpital.model;

import java.sql.Connection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pacjent 
{
    private IntegerProperty idPacjenta;
    private StringProperty imie;
    private StringProperty nazwisko;
    private StringProperty pesel;
    private IntegerProperty idLekarza;
    private StringProperty lekarz;
    private IntegerProperty idOddzialu;
    private StringProperty oddzial;
    private StringProperty grKrwii;
    
    public Pacjent(Integer idPacjenta, String imie, String nazwisko, String pesel, Integer idLekarza, String lekarz, Integer idOddzialu, String oddzial, String grKrwii) 
    {
        this.idPacjenta = new SimpleIntegerProperty(idPacjenta);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.idLekarza = new SimpleIntegerProperty(idLekarza);
        this.lekarz = new SimpleStringProperty(lekarz);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.oddzial = new SimpleStringProperty(oddzial);
        this.grKrwii = new SimpleStringProperty(grKrwii);
    }
    
    public IntegerProperty getIdPacjenta() 
    {
        return idPacjenta;
    }

    public void setIdPacjenta(IntegerProperty idPacjenta) 
    {
        this.idPacjenta = idPacjenta;
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

    public IntegerProperty getIdLekarza() 
    {
        return idLekarza;
    }

    public void setIdLekarza(IntegerProperty idLekarza) 
    {
        this.idLekarza = idLekarza;
    }

    public StringProperty getLekarz() 
    {
        return lekarz;
    }

    public void setLekarz(StringProperty lekarz) 
    {
        this.lekarz = lekarz;
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

    public StringProperty getGrKrwii() 
    {
        return grKrwii;
    }

    public void setGrKrwii(StringProperty grKrwii) 
    {
        this.grKrwii = grKrwii;
    }
}