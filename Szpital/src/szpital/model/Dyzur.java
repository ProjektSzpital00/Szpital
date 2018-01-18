package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dyzur 
{
    IntegerProperty idDyzuru;
    IntegerProperty idLekarza;
    StringProperty lekarzDyzurujacy;
    IntegerProperty idPielęgniarki;
    StringProperty pielegniarkaDyzurujaca;
    IntegerProperty idOddzialu;
    StringProperty oddzial;
    StringProperty terminOd;
    StringProperty terminDataOd;
    StringProperty terminCzasOd;
    StringProperty terminDo;
    StringProperty terminDataDo;
    StringProperty terminCzasDo;
    
    public Dyzur(Integer idOsobyDyzurujacej, Boolean czyLekarz, String terminOd, String terminDo) 
    {
        if(czyLekarz)
            this.idLekarza = new SimpleIntegerProperty(idOsobyDyzurujacej);
        else
            this.idPielęgniarki = new SimpleIntegerProperty(idOsobyDyzurujacej);
        this.terminOd = new SimpleStringProperty(terminOd);
        this.terminDo = new SimpleStringProperty(terminDo);
    }
    
    public Dyzur(Integer idDyzuru, String terminOd, String terminDo, Integer idOsobyDyzurujacej, Boolean czyLekarz)
    {
        this.idDyzuru = new SimpleIntegerProperty(idDyzuru);
        
        this.terminOd = new SimpleStringProperty(terminOd);
        String [] temp = terminOd.split(" ");
        this.terminDataOd = new SimpleStringProperty(temp[0]);
        this.terminCzasOd = new SimpleStringProperty(temp[1]);
        
        this.terminDo = new SimpleStringProperty(terminOd);
        temp = terminDo.split(" ");
        this.terminDataDo = new SimpleStringProperty(temp[0]);
        this.terminCzasDo = new SimpleStringProperty(temp[1]);
        
        if(czyLekarz)
            this.idLekarza = new SimpleIntegerProperty(idOsobyDyzurujacej);
        else
            this.idPielęgniarki = new SimpleIntegerProperty(idOsobyDyzurujacej);
    }

    public IntegerProperty getIdDyzuru() 
    {
        return idDyzuru;
    }

    public void setIdDyzuru(IntegerProperty idDyzuru) 
    {
        this.idDyzuru = idDyzuru;
    }

    public IntegerProperty getIdLekarza() 
    {
        return idLekarza;
    }

    public void setIdLekarza(IntegerProperty idLekarza) 
    {
        this.idLekarza = idLekarza;
    }

    public StringProperty getLekarzDyzurujacy() 
    {
        return lekarzDyzurujacy;
    }

    public void setLekarzDyzurujacy(StringProperty lekarzDyzurujacy) 
    {
        this.lekarzDyzurujacy = lekarzDyzurujacy;
    }

    public IntegerProperty getIdPielęgniarki() 
    {
        return idPielęgniarki;
    }

    public void setIdPielęgniarki(IntegerProperty idPielęgniarki) 
    {
        this.idPielęgniarki = idPielęgniarki;
    }

    public StringProperty getPielegniarkaDyzurujaca() 
    {
        return pielegniarkaDyzurujaca;
    }

    public void setPielegniarkaDyzurujaca(StringProperty pielegniarkaDyzurujaca) 
    {
        this.pielegniarkaDyzurujaca = pielegniarkaDyzurujaca;
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

    public StringProperty getTerminOd() 
    {
        return terminOd;
    }

    public void setTerminOd(StringProperty terminOd) 
    {
        this.terminOd = terminOd;
    }

    public StringProperty getTerminDataOd() 
    {
        return terminDataOd;
    }

    public void setTerminDataOd(StringProperty terminDataOd) 
    {
        this.terminDataOd = terminDataOd;
    }

    public StringProperty getTerminCzasOd() 
    {
        return terminCzasOd;
    }

    public void setTerminCzasOd(StringProperty terminCzasOd) 
    {
        this.terminCzasOd = terminCzasOd;
    }

    public StringProperty getTerminDo() 
    {
        return terminDo;
    }

    public void setTerminDo(StringProperty terminDo) 
    {
        this.terminDo = terminDo;
    }

    public StringProperty getTerminDataDo() 
    {
        return terminDataDo;
    }

    public void setTerminDataDo(StringProperty terminDataDo) 
    {
        this.terminDataDo = terminDataDo;
    }

    public StringProperty getTerminCzasDo() 
    {
        return terminCzasDo;
    }

    public void setTerminCzasDo(StringProperty terminCzasDo) 
    {
        this.terminCzasDo = terminCzasDo;
    }
}