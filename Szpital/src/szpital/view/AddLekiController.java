/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import szpital.model.Badania;
import szpital.model.Leki;
import szpital.model.Pacjent;
import szpital.model.RodzajeBadan;
import szpital.model.RodzajeLekow;
import szpital.util.BadaniaUtil;
import szpital.util.LekiUtil;

/**
 *
 * @author Bartek
 */
public class AddLekiController 
{
    private LekiController lekiController;
    private Stage dialoStage;
    private Leki lek;
    
    private ObservableList <RodzajeLekow> rodzajeLekowList;
    private ObservableList <String> listaLekow;
    
    private Pacjent wybranyPacjent;
    private Integer zaznaczonyPacjent;
    
    @FXML
    private ChoiceBox<String> nazwaLeku;
    
    @FXML
    private DatePicker dataOd;
    
    @FXML
    private DatePicker dataDo;
    
    @FXML
    private TextField dawkowanie;
    
    public void setLekiController(LekiController lekiController) 
    {
        this.lekiController = lekiController;
    }
    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

   
    public Leki getLek() 
    {
        return lek;
    }

    /*
    public void setLek(Leki lek) 
    {
       if(lek != null)
        {
            this.lek = lek;
            
            //System.out.println(badanie.getId());
            
            Date d = badanie.SQLgetDataBadania();
            LocalDate ld = d.toLocalDate();
            
            dataBadania.setValue(ld);
            opisBadania.setText(badanie.getWynikBadania().getValue());
            nazwaBadania.setValue(badanie.getNazwaBadania().getValue());
        }
        else
        {
            
        }
    }
    
    */
    
    public Integer getZaznaczonyPacjent() {
        return zaznaczonyPacjent;
    }
    
    
    public void setZaznaczonyPacjent(Integer zaznaczonyPacjent) {
        this.zaznaczonyPacjent = zaznaczonyPacjent;
    }
    
    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
    
    public void setListaLekow()
    {
        try {
            rodzajeLekowList = LekiUtil.getRodzajeLekowList();
            listaLekow = FXCollections.observableArrayList();
            for(RodzajeLekow b:  rodzajeLekowList)
            {
                listaLekow.addAll(b.getNazwa().getValue());
                System.out.println(b.getNazwa().getValue());
            }

            nazwaLeku.setItems(listaLekow);

        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(AddBadanieController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
            //Logger.getLogger(AddBadanieController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
}
