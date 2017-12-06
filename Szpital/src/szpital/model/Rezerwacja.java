package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Rezerwacja 
{
    IntegerProperty idRezerwacji;
    IntegerProperty idSali;
    StringProperty nazwa;
    Lekarz lekarzRezerwujacy;
    StringProperty opis;

    public Rezerwacja(IntegerProperty idRezerwacji, IntegerProperty idSali, StringProperty nazwa, Lekarz lekarzRezerwujacy, StringProperty opis) 
    {
        this.idRezerwacji = idRezerwacji;
        this.idSali = idSali;
        this.nazwa = nazwa;
        this.lekarzRezerwujacy = lekarzRezerwujacy;
        this.opis = opis;
    }
}
