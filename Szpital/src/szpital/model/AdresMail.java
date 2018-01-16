package szpital.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AdresMail {
    private StringProperty adress;

    public AdresMail(String adress){
        this.adress = new SimpleStringProperty(adress);
    }

    public String getAdress() {
        return adress.get();
    }

    public StringProperty adressProperty() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress.set(adress);
    }
}
