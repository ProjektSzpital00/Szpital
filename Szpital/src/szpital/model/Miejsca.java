package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Miejsca {
    private IntegerProperty IdOddzialu;
    private IntegerProperty IdSali;
    private IntegerProperty IdLozka;

    public Miejsca(Integer idOddzialu, Integer idSali, Integer idLozka) {
        IdOddzialu = new SimpleIntegerProperty(idOddzialu);
        IdSali = new SimpleIntegerProperty(idSali);
        IdLozka = new SimpleIntegerProperty(idLozka);
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
}
