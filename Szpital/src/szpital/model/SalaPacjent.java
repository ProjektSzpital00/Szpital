package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SalaPacjent 
{
    private IntegerProperty idSali;
    private StringProperty nazwa;
    private IntegerProperty idOddzialu;

    public SalaPacjent(Integer idSali, String nazwa, Integer idOddzialu) 
    {
        this.idSali = new SimpleIntegerProperty(idSali);
        this.nazwa = new SimpleStringProperty(nazwa);
        this.idOddzialu = new SimpleIntegerProperty(idOddzialu);
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

    public IntegerProperty getIdOddzialu() 
    {
        return idOddzialu;
    }

    public void setIdOddzialu(IntegerProperty idOddzialu) 
    {
        this.idOddzialu = idOddzialu;
    }
}
