package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Oddzial 
{
    private IntegerProperty idOddzialu;
    private StringProperty nazwaOddzialu;
    private IntegerProperty iloscMiejsc;
    private IntegerProperty iloscWolnychMiejsc;
    
    public Oddzial(Integer idOddzialu, String nazwaOddzialu, Integer iloscMiejsc, Integer iloscWolnychMiejsc)
    {
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
        this.nazwaOddzialu = new SimpleStringProperty(nazwaOddzialu);
        this.iloscMiejsc = new SimpleIntegerProperty(iloscMiejsc);
        this.iloscWolnychMiejsc = new SimpleIntegerProperty(iloscWolnychMiejsc);
    }
    
    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public void setIdOddzialu(IntegerProperty idOddzialu) 
    {
        this.idOddzialu = idOddzialu;
    }

    public StringProperty getNazwaOddzialu() 
    {
        return nazwaOddzialu;
    }

    public void setNazwaOddzialu(StringProperty nazwaOddzialu) 
    {
        this.nazwaOddzialu = nazwaOddzialu;
    }

    public IntegerProperty getIloscMiejsc() 
    {
        return iloscMiejsc;
    }

    public void setIloscMiejsc(IntegerProperty iloscMiejsc) 
    {
        this.iloscMiejsc = iloscMiejsc;
    }

    public IntegerProperty getIloscWolnychMiejsc() 
    {
        return iloscWolnychMiejsc;
    }

    public void setIloscWolnychMiejsc(IntegerProperty iloscWolnychMiejsc) 
    {
        this.iloscWolnychMiejsc = iloscWolnychMiejsc;
    }
}
