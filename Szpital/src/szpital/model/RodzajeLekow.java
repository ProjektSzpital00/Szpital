package szpital.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RodzajeLekow 
{
    private IntegerProperty id;
    private StringProperty nazwa;

    public RodzajeLekow(Integer id, String nazwa)
    {
		this.id=new SimpleIntegerProperty(id);
		this.nazwa=new SimpleStringProperty(nazwa);
    }
    
    public IntegerProperty getId() {
        return id;
    }

    public void setId(IntegerProperty id) {
        this.id = id;
    }

    public StringProperty getNazwa() {
        return nazwa;
    }

    public void setNazwa(StringProperty nazwa) {
        this.nazwa = nazwa;
    }
    
    
}
