/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.sql.Date;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import szpital.model.Badania;
import szpital.model.Pacjent;
import szpital.util.BadaniaUtil;
import szpital.util.Laczenie;

/**
 *
 * @author Bartek
 */
public class AddBadanieController 
{

    
    private BadaniaController badaniaController;
    private Stage dialoStage;
    private Badania badanie;
    
    @FXML
    private DatePicker dataBadania;
    @FXML
    private TextArea opisBadania;
    
    @FXML
    private ChoiceBox<String> nazwaBadania;
    
    private ObservableList <String> listaBadan;
    
    private Pacjent wybranyPacjent;
    private Integer zaznaczonyPacjent;
    
    
    public void setBadanieController(BadaniaController badaniaController) 
    {
        this.badaniaController = badaniaController;
    }
    
    
    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

    public Badania getBadanie() 
    {
        return badanie;
    }

    public void setBadanie(Badania badanie) 
    {
        this.badanie = badanie;
    }
    
    
    public void ok() throws SQLException, ClassNotFoundException
    {
        
        System.out.println(nazwaBadania.getValue());
        System.out.println(dataBadania.getValue());
        System.out.println(opisBadania.getText());
        
        
        System.out.println(wybranyPacjent.getImie());
        Badania noweBadanie = new Badania(wybranyPacjent.getIdPacjenta().getValue(), wybranyPacjent.getImie().toString(),
                wybranyPacjent.getNazwisko().toString(), nazwaBadania.getValue(), new Date(10000), opisBadania.getText());
        
        BadaniaUtil.addBadanie(Laczenie.getStatement(),noweBadanie);
        
    }
    
    
    
    
    public void setListaBadan()
    {
    listaBadan = FXCollections.observableArrayList();
        listaBadan.addAll("Badanie 1", "Badanie 2", "Badanie 3", "Badanie 4");
        nazwaBadania.setItems(listaBadan);
        //if(pacjent != null)
          //  grKrwii.getSelectionModel().select(pacjent.getGrKrwii().getValue());
    
    }
    
    
    
    /**
     * @return the zaznaczonyPacjent
     */
    public Integer getZaznaczonyPacjent() {
        return zaznaczonyPacjent;
    }

    /**
     * @param zaznaczonyPacjent the zaznaczonyPacjent to set
     */
    public void setZaznaczonyPacjent(Integer zaznaczonyPacjent) {
        this.zaznaczonyPacjent = zaznaczonyPacjent;
    }

    /**
     * @return the wybranyPacjent
     */
    public Pacjent getWybranyPacjent() {
        return wybranyPacjent;
    }

    /**
     * @param wybranyPacjent the wybranyPacjent to set
     */
    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
}
