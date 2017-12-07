package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rezerwacja 
{
    IntegerProperty idRezerwacji;
    IntegerProperty idSali;
    IntegerProperty idRezerwującego;
    StringProperty termin;
    StringProperty terminData;
    StringProperty terminCzas;
    StringProperty informacja;

    public Rezerwacja(Integer idRezerwacji, Integer idSali, Integer idRezerwującego, String termin, String informacja) 
    {
        this.idRezerwacji = new SimpleIntegerProperty(idRezerwacji);
        this.idSali = new SimpleIntegerProperty(idSali);
        this.idRezerwującego = new SimpleIntegerProperty(idRezerwującego);
        this.termin = new SimpleStringProperty(termin);
        String [] temp = termin.split(" ");
        this.terminData = new SimpleStringProperty(temp[0]);
        this.terminCzas = new SimpleStringProperty(temp[1]);
        this.informacja = new SimpleStringProperty(informacja);
    }

    public IntegerProperty getIdRezerwacji() 
    {
        return idRezerwacji;
    }

    public void setIdRezerwacji(IntegerProperty idRezerwacji) 
    {
        this.idRezerwacji = idRezerwacji;
    }

    public IntegerProperty getIdSali() 
    {
        return idSali;
    }

    public void setIdSali(IntegerProperty idSali) 
    {
        this.idSali = idSali;
    }

    public IntegerProperty getIdRezerwującego() 
    {
        return idRezerwującego;
    }

    public void setIdRezerwującego(IntegerProperty idRezerwującego) 
    {
        this.idRezerwującego = idRezerwującego;
    }

    public StringProperty getTermin() 
    {
        return termin;
    }

    public void setTermin(StringProperty termin) 
    {
        this.termin = termin;
    }

    public StringProperty getTerminData() 
    {
        return terminData;
    }

    public void setTerminData(StringProperty terminData) 
    {
        this.terminData = terminData;
    }

    public StringProperty getTerminCzas() 
    {
        return terminCzas;
    }

    public void setTerminCzas(StringProperty terminCzas) 
    {
        this.terminCzas = terminCzas;
    }

    public StringProperty getInformacja() 
    {
        return informacja;
    }

    public void setInformacja(StringProperty informacja) 
    {
        this.informacja = informacja;
    }
}
