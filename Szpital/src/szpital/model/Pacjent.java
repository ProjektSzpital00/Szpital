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
    private StringProperty lekarz;
    private IntegerProperty idOddzialu;
    private StringProperty oddzial;
    private StringProperty grKrwii;
    private IntegerProperty nrSali;
    private StringProperty nazwaSali;
    private IntegerProperty nrLozka;
    private StringProperty mail;

    public Pacjent(Integer idPacjenta, String imie, String nazwisko, String pesel, Integer idLekarza, String lekarz,
                   Integer idOddzialu, String oddzial, String grKrwii, Integer nrSali, Integer nrLozka, String mail)
    {
        if(idPacjenta != null)
            this.idPacjenta = new SimpleIntegerProperty(idPacjenta);
        this.imie = new SimpleStringProperty(imie);
        this.nazwisko = new SimpleStringProperty(nazwisko);
        this.pesel = new SimpleStringProperty(pesel);
        this.idLekarza = new SimpleIntegerProperty(idLekarza);
        this.lekarz = new SimpleStringProperty(lekarz);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.oddzial = new SimpleStringProperty(oddzial);
        this.grKrwii = new SimpleStringProperty(grKrwii);
        this.nrSali = new SimpleIntegerProperty(nrSali);
        this.nrLozka = new SimpleIntegerProperty(nrLozka);
        this.mail = new SimpleStringProperty(mail);
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

    public IntegerProperty getNrSali() 
    {
        return nrSali;
    }

    public void setNrSali(IntegerProperty nrSali) 
    {
        this.nrSali = nrSali;
    }

    public StringProperty getNazwaSali() 
    {
        return nazwaSali;
    }

    public void setNazwaSali(StringProperty nazwaSali) 
    {
        this.nazwaSali = nazwaSali;
    }
    
    public IntegerProperty getNrLozka() 
    {
        return nrLozka;
    }

    public void setNrLozka(IntegerProperty nrLozka) 
    {
        this.nrLozka = nrLozka;
    }

    public StringProperty getMail() { return mail; }

    public void setMail(StringProperty mail) { this.mail =mail; }
}
