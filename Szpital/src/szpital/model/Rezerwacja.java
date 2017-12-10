package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rezerwacja 
{
    IntegerProperty idRezerwacji;
    IntegerProperty idSali;
    StringProperty sala;
    IntegerProperty idRezerwującego;
    StringProperty rezerwujacy;
    StringProperty termin;
    StringProperty terminData;
    StringProperty terminCzas;
    StringProperty informacja;

    public Rezerwacja(String czas)
    {
        this.terminCzas = new SimpleStringProperty(czas);
    }
    
    public Rezerwacja(String data, String czas, Integer idSali, String sala)
    {
        this.idSali = new SimpleIntegerProperty(idSali);
        this.sala = new SimpleStringProperty(sala);
        this.terminData = new SimpleStringProperty(data);
        this.terminCzas = new SimpleStringProperty(czas);
        this.termin = new SimpleStringProperty(data+" "+czas);
    }
    
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

    public StringProperty getSala() 
    {
        return sala;
    }

    public void setSala(StringProperty sala) 
    {
        this.sala = sala;
    }

    public IntegerProperty getIdRezerwującego() 
    {
        return idRezerwującego;
    }

    public void setIdRezerwującego(IntegerProperty idRezerwującego) 
    {
        this.idRezerwującego = idRezerwującego;
    }

    public StringProperty getRezerwujacy() 
    {
        return rezerwujacy;
    }

    public void setRezerwujacy(StringProperty rezerwujacy) 
    {
        this.rezerwujacy = rezerwujacy;
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
