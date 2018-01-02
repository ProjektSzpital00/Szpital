/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    /**
     * @param id the id to set
     */
    public void setId(IntegerProperty id) {
        this.id = id;
    }

    /**
     * @return the nazwa
     */
    public StringProperty getNazwa() {
        return nazwa;
    }

    /**
     * @param nazwa the nazwa to set
     */
    public void setNazwa(StringProperty nazwa) {
        this.nazwa = nazwa;
    }
    
    
}
