package szpital.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statystyka 
{
    private SimpleStringProperty nazwaOddzialu;
    private SimpleStringProperty sala;
    private SimpleIntegerProperty nrLozka;
    private SimpleIntegerProperty czyZajete;
    private SimpleStringProperty czyZajeteString;
    
    
    public Statystyka(String nazwaOddzialu, String sala, Integer nrLozka, Integer czyZajete)
    {
        this.nazwaOddzialu = new SimpleStringProperty(nazwaOddzialu);
        this.sala = new SimpleStringProperty(sala);
        this.nrLozka = new SimpleIntegerProperty(nrLozka);
        this.czyZajete = new SimpleIntegerProperty(czyZajete);
        
        if(czyZajete == 0)
        {
            czyZajeteString = new SimpleStringProperty("Zajete");
        }
        else
        {
            czyZajeteString = new SimpleStringProperty("");
        }
    }

    public SimpleStringProperty getNazwaOddzialu() 
    {
        return nazwaOddzialu;
    }

    public void setNazwaOddzialu(SimpleStringProperty nazwaOddzialu) 
    {
        this.nazwaOddzialu = nazwaOddzialu;
    }

    
    public SimpleStringProperty getSala() 
    {
        return sala;
    }

    
    public void setSala(SimpleStringProperty sala) 
    {
        this.sala = sala;
    }

    
    public SimpleIntegerProperty getNrLozka() 
    {
        return nrLozka;
    }

    
    public void setNrLozka(SimpleIntegerProperty nrLozka) 
    {
        this.nrLozka = nrLozka;
    }

    public SimpleStringProperty getCzyZajeteString() 
    {
        return czyZajeteString;
    }

    public void setCzyZajeteString(SimpleStringProperty czyZajeteString) 
    {
        this.czyZajeteString = czyZajeteString;
    }
}
