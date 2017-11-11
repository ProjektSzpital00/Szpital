package szpital.model;

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
    private IntegerProperty idOddzialu;
    private StringProperty grKrwii;

    public Pacjent(Integer idPacjenta, String imie, String nazwisko, String pesel, Integer idLekarza, Integer idOddzialu, String grKrwii) 
    {
        this.idPacjenta = new SimpleIntegerProperty(idPacjenta);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.idLekarza = new SimpleIntegerProperty(idLekarza);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.grKrwii = new SimpleStringProperty(grKrwii);
    }
    
    public IntegerProperty getIdPacjenta() 
    {
        return idPacjenta;
    }

    public StringProperty getImie() 
    {
        return imie;
    }

    public StringProperty getNazwisko() 
    {
        return nazwisko;
    }

    public StringProperty getPesel() 
    {
        return pesel;
    }

    public IntegerProperty getIdLekarza() 
    {
        return idLekarza;
    }

    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public StringProperty getGrKrwii() 
    {
        return grKrwii;
    }
}
