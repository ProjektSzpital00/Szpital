package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Miejsca {
    private IntegerProperty IdOddzialu;
    private IntegerProperty IdSali;
    private IntegerProperty IdLozka;
    private StringProperty NrSali;
    private StringProperty NrLozka;

    public Miejsca(Integer idOddzialu, Integer idSali, Integer idLozka, String nrSali, String nrLozka) {
        IdOddzialu = new SimpleIntegerProperty(idOddzialu);
        IdSali = new SimpleIntegerProperty(idSali);
        IdLozka = new SimpleIntegerProperty(idLozka);
        NrSali = new SimpleStringProperty(nrSali);
        NrLozka = new SimpleStringProperty(nrLozka);
    }

    public int getIdOddzialu() {
        return IdOddzialu.get();
    }

    public IntegerProperty idOddzialuProperty() {
        return IdOddzialu;
    }

    public void setIdOddzialu(int idOddzialu) {
        this.IdOddzialu.set(idOddzialu);
    }

    public int getIdSali() {
        return IdSali.get();
    }

    public IntegerProperty idSaliProperty() {
        return IdSali;
    }

    public void setIdSali(int idSali) {
        this.IdSali.set(idSali);
    }

    public int getIdLozka() {
        return IdLozka.get();
    }

    public IntegerProperty idLozkaProperty() {
        return IdLozka;
    }

    public void setIdLozka(int idLozka) {
        this.IdLozka.set(idLozka);
    }

    public String getNrSali() {
        return NrSali.get();
    }

    public StringProperty nrSaliProperty() {
        return NrSali;
    }

    public void setNrSali(String nrSali) {
        this.NrSali.set(nrSali);
    }

    public String getNrLozka() {
        return NrLozka.get();
    }

    public StringProperty nrLozkaProperty() {
        return NrLozka;
    }

    public void setNrLozka(String nrLozka) {
        this.NrLozka.set(nrLozka);
    }
}
