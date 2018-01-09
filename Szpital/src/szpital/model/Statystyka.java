/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Bartek
 */
public class Statystyka 
{
    private SimpleStringProperty nazwaOddzialu;
    private SimpleStringProperty sala;
    private SimpleIntegerProperty nrLozka;
    private SimpleIntegerProperty czyZajete;
    //private boolean czyZajete;
    private SimpleStringProperty czyZajeteString;
    
    
    public Statystyka(String nazwaOddzialu, String sala, Integer nrLozka, Integer czyZajete)
    {
        this.nazwaOddzialu = new SimpleStringProperty(nazwaOddzialu);
        this.sala = new SimpleStringProperty(sala);
        this.nrLozka = new SimpleIntegerProperty(nrLozka);
        this.czyZajete = new SimpleIntegerProperty(czyZajete);
        
        if(czyZajete == 1)
        {
            czyZajeteString = new SimpleStringProperty("Zajete");
        }
        else
        {
            czyZajeteString = new SimpleStringProperty("");
        }
        
        //this.czyZajete = czyZajete;
        
    }

    public SimpleStringProperty getNazwaOddzialu() {
        return nazwaOddzialu;
    }

    public void setNazwaOddzialu(SimpleStringProperty nazwaOddzialu) {
        this.nazwaOddzialu = nazwaOddzialu;
    }

    
    public SimpleStringProperty getSala() {
        return sala;
    }

    
    public void setSala(SimpleStringProperty sala) {
        this.sala = sala;
    }

    
    public SimpleIntegerProperty getNrLozka() {
        return nrLozka;
    }

    
    public void setNrLozka(SimpleIntegerProperty nrLozka) {
        this.nrLozka = nrLozka;
    }


    /*
    
    public boolean isCzyZajete() {
        return czyZajete;
    }

     
    public void setCzyZajete(boolean czyZajete) {
        this.czyZajete = czyZajete;
    }
    */

    
    public SimpleIntegerProperty getCzyZajete() {
        return czyZajete;
    }

    public void setCzyZajete(SimpleIntegerProperty czyZajete) {
        this.czyZajete = czyZajete;
    }

    /**
     * @return the czyZajeteString
     */
    public SimpleStringProperty getCzyZajeteString() {
        return czyZajeteString;
    }

    /**
     * @param czyZajeteString the czyZajeteString to set
     */
    public void setCzyZajeteString(SimpleStringProperty czyZajeteString) {
        this.czyZajeteString = czyZajeteString;
    }
}
