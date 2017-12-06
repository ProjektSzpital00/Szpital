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
}
