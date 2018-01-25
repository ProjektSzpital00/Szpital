package szpital.view;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import szpital.model.RodzajeBadan;
import szpital.util.BadaniaUtil;


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
    
    private ObservableList<RodzajeBadan> rodzajeBadanList;
    
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
        if(badanie != null)
        {
            this.badanie = badanie;
            
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
    
    
    public void ok() throws SQLException, ClassNotFoundException
    {
        boolean walidacja_nazwaBadania = false;
        boolean walidacja_data = false;
        boolean walidacja_opis = false;
        
        StringBuffer komunikat = new StringBuffer();
        
        
        if(nazwaBadania.getValue() != null)
        {
            walidacja_nazwaBadania = true;
        }
        else
        {
            komunikat.append("- Nazwa badania nie zostala wybrana\n");
        }
        
        if(dataBadania.getValue() != null)
        {
            walidacja_data = true;
        }
        else
        {
            komunikat.append("- Data badania nie zostala wybrana\n");
        }
        
        
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
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Dodanie nowego pacjenta");
            Optional<ButtonType> result = alert.showAndWait();
                     
            if(result.get() == ButtonType.OK)
            {
                
                LocalDate locald = dataBadania.getValue();
                Date date = Date.valueOf(locald);
                
                int tmp = 0;
                
                String s = nazwaBadania.getValue();
                
                
                for(RodzajeBadan rb: rodzajeBadanList)
                {
                    if(rb.getNazwa().getValue().equals(s))
                    {
                        tmp = rb.getId().intValue();
                    }
                }

                if(this.badanie == null)
                {
                    Badania noweBadanie = new Badania(-1,tmp, wybranyPacjent.getIdPacjenta().intValue(), date, opisBadania.getText());
                    System.out.println("Nowe");
                    BadaniaUtil.addBadanie(noweBadanie);
                }
                else
                {
                    Badania noweBadanie = new Badania(badanie.getId().intValue(),tmp, wybranyPacjent.getIdPacjenta().intValue(), date, opisBadania.getText());
                    System.out.println("Edycja");
                    BadaniaUtil.updateBadaniePacjenta(noweBadanie);
                }
                
                badaniaController.ladujListe(wybranyPacjent.getIdPacjenta().intValue());
                
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
        try {
            rodzajeBadanList = BadaniaUtil.getRodzajeBadanList();
            listaBadan = FXCollections.observableArrayList();
            for(RodzajeBadan b:  rodzajeBadanList)
            {
                listaBadan.addAll(b.getNazwa().getValue());
            }

            nazwaBadania.setItems(listaBadan);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddBadanieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AddBadanieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getZaznaczonyPacjent() {
        return zaznaczonyPacjent;
    }

    public void setZaznaczonyPacjent(Integer zaznaczonyPacjent) {
        this.zaznaczonyPacjent = zaznaczonyPacjent;
    }

    public Pacjent getWybranyPacjent() {
        return wybranyPacjent;
    }

    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
}