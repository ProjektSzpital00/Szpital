package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sala 
{
    private IntegerProperty idSali;
    private StringProperty nazwa;
    private StringProperty opis;
    private IntegerProperty idOddzialu;
    private Boolean czyZarezerwowana;
    private Lekarz lekarzRezerwujacy;

    public Sala(Integer idSali, String nazwa, String opis, Integer idOddzialu, Boolean czyZarezerwowana, Lekarz lekarzRezerwujacy) 
    {
        this.idSali = new SimpleIntegerProperty(idSali);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.opis = new SimpleStringProperty(opis);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.czyZarezerwowana = czyZarezerwowana;
        if(lekarzRezerwujacy != null)
            this.lekarzRezerwujacy = lekarzRezerwujacy;
    }

    public IntegerProperty getIdSali() 
    {
        return idSali;
    }

    public void setIdSali(IntegerProperty idSali) 
    {
        this.idSali = idSali;
    }

    public StringProperty getNazwa() 
    {
        return nazwa;
    }

    public void setNazwa(StringProperty nazwa) 
    {
        this.nazwa = nazwa;
    }

    public StringProperty getOpis() 
    {
        return opis;
    }

    public void setOpis(StringProperty opis) 
    {
        this.opis = opis;
    }

    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public void setIdOddzialu(IntegerProperty idOddzialu) 
    {
        this.idOddzialu = idOddzialu;
    }

    public Boolean getCzyZarezerwowana() 
    {
        return czyZarezerwowana;
    }

    public void setCzyZarezerwowana(Boolean czyZarezerwowana) 
    {
        this.czyZarezerwowana = czyZarezerwowana;
    }

    public Lekarz getLekarzRezerwujacy() 
    {
        return lekarzRezerwujacy;
    }

    public void setLekarzRezerwujacy(Lekarz lekarzRezerwujacy) 
    {
        this.lekarzRezerwujacy = lekarzRezerwujacy;
    }
}
