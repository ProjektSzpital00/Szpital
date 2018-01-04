package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Lozko 
{
    private IntegerProperty idLozka;
    private IntegerProperty idSali;
    private IntegerProperty idOddzialu;
    private IntegerProperty czyWolne;
    
    public Lozko(Integer idLozka, Integer idSali, Integer czyWolne) 
    {
        this.idLozka = new SimpleIntegerProperty(idLozka);
        this.idSali = new SimpleIntegerProperty(idSali);
        this.czyWolne = new SimpleIntegerProperty(czyWolne);
    }

    public Lozko(Integer idLozka, Integer idSali, Integer idOddzialu, Integer czyWolne) 
    {
        this.idLozka = new SimpleIntegerProperty(idLozka);
        this.idSali = new SimpleIntegerProperty(idSali);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.czyWolne = new SimpleIntegerProperty(czyWolne);
    }

    public IntegerProperty getIdLozka() 
    {
        return idLozka;
    }

    public void setIdLozka(IntegerProperty idLozka) 
    {
        this.idLozka = idLozka;
    }

    public IntegerProperty getIdSali() 
    {
        return idSali;
    }

    public void setIdSali(IntegerProperty idSali) 
    {
        this.idSali = idSali;
    }

    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public void setIdOddzialu(IntegerProperty idOddzialu) 
    {
        this.idOddzialu = idOddzialu;
    }

    public IntegerProperty getCzyWolne() 
    {
        return czyWolne;
    }

    public void setCzyWolne(IntegerProperty czyWolne) 
    {
        this.czyWolne = czyWolne;
    }
}
