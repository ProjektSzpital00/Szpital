/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
        boolean walidacja_nazwaBadania = false;
        boolean walidacja_data = false;
        boolean walidacja_opis = false;
        
        StringBuffer komunikat = new StringBuffer();
        
        
        System.out.println(nazwaBadania.getValue() != null);
        if(nazwaBadania.getValue() != null)
        {
            walidacja_nazwaBadania = true;
        }
        else
        {
            komunikat.append("- Nazwa badania nie zostala wybrana\n");
        }
        
        System.out.println(dataBadania.getValue() != null);
        if(dataBadania.getValue() != null)
        {
            walidacja_data = true;
        }
        else
        {
            komunikat.append("- Data badania nie zostala wybrana\n");
        }
        
        
        System.out.println(!opisBadania.getText().isEmpty());
        if(!opisBadania.getText().isEmpty())
        {
            walidacja_opis = true;
        }
        else
        {
            komunikat.append("- Pole opis nie może pozostać puste");
        }
        
        
        
        if(walidacja_nazwaBadania && walidacja_data  && walidacja_opis)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
            Optional<ButtonType> result = alert.showAndWait();
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Dodanie nowego pacjenta");
            
            if(result.get() == ButtonType.OK)
            {
                //tutaj bedzie inny konstruktor
                Badania noweBadanie = new Badania(3, wybranyPacjent.getImie().toString(),
                        wybranyPacjent.getNazwisko().toString(), nazwaBadania.getValue(), new Date(10000), opisBadania.getText());

                BadaniaUtil.addBadanie(Laczenie.getStatement(),noweBadanie);
                
               
                
                dialoStage.close();
            }
        
        }
        
        else
        {
            Alert puste = new Alert(Alert.AlertType.ERROR);
            
            puste.setTitle("Błąd operacji");
            puste.setHeaderText("Dodanie nowego badania.");
            puste.setContentText(komunikat.toString());
            puste.showAndWait();
        }

    }
        
    public void cancel()
    {
        dialoStage.close();
        
    }
        
    
    
    public void setListaBadan()
    {
        
        
        
        listaBadan = FXCollections.observableArrayList();
        listaBadan.addAll("Badanie 1", "Badanie 2", "Badanie 3", "Badanie 4");
        nazwaBadania.setItems(listaBadan);
        
    
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