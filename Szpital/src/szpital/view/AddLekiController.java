/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import szpital.model.Badania;
import szpital.model.Leki;
import szpital.model.Pacjent;
import szpital.model.RodzajeBadan;
import szpital.model.RodzajeLekow;
import szpital.util.BadaniaUtil;
import szpital.util.Laczenie;
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
    
    
    public void ok() throws SQLException, ClassNotFoundException
    {
        boolean walidacja_nazwaLeku = false;
        boolean walidacja_dataOd = false;
        boolean walidacja_dataDo = false;
        boolean walidacja_dawkowanie = false;
        
        StringBuffer komunikat = new StringBuffer();
        
        
        if(nazwaLeku.getValue() != null)
        {
            walidacja_nazwaLeku = true;
        }
        else
        {
            komunikat.append("- Nazwa leku nie zostala wybrana\n");
        }
        
        if(dataOd.getValue() != null)
        {
            walidacja_dataOd = true;
        }
        else
        {
            komunikat.append("- Data rozpoczecia przyjmowania leku nie zostala wybrana\n");
        }
        
        if(dataDo.getValue() != null)
        {
            walidacja_dataDo = true;
        }
        else
        {
            komunikat.append("- Data zakonczenia przyjmowania leku nie zostala wybrana\n");
        }
        
        System.out.println(!dawkowanie.getText().isEmpty());
        
        if(!dawkowanie.getText().isEmpty())
        {
            walidacja_dawkowanie = true;
        }
        else
        {
            komunikat.append("- Pole dawkowanie nie może pozostać puste");
        }
        
        System.out.println(walidacja_dataOd);
        System.err.println(walidacja_dawkowanie);
        System.out.println(walidacja_nazwaLeku);
        System.out.println(walidacja_dataDo);
        
        if(walidacja_nazwaLeku && walidacja_dataOd  && walidacja_dataDo && walidacja_dawkowanie)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            //System.out.println("weszlo");
            Optional<ButtonType> result = alert.showAndWait();
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Dodanie nowego leku");
            
            if(result.get() == ButtonType.OK)
            {
                
                
                LocalDate locald = dataOd.getValue();
                Date dateOd = Date.valueOf(locald);
        
                LocalDate locald2 = dataOd.getValue();
                Date dateDo = Date.valueOf(locald);
                
                
                 String s = nazwaLeku.getValue();
                int tmp =0;
                for(RodzajeLekow rb: rodzajeLekowList)
                {
                    if(rb.getNazwa().getValue().equals(s))
                    {
                        //System.err.println("Wpisuje");
                        tmp = rb.getId().intValue();
                    }
                }
                
                
                Leki lekPacjenta = new Leki(-1, wybranyPacjent.getIdPacjenta().intValue(),"niewazne",tmp, dateOd, dateDo,dawkowanie.getText());
                //System.out.println("Nowe lek");
         
                 
                LekiUtil.addLekPacjenta(lekPacjenta);
                
                lekiController.ladujListe(wybranyPacjent.getIdPacjenta().intValue());
                dialoStage.close();
            }
            
        
        }
        else
        {
            Alert puste = new Alert(Alert.AlertType.ERROR);
            
            puste.setTitle("Błąd operacji");
            puste.setHeaderText("Dodanie nowego leku.");
            puste.setContentText(komunikat.toString());
            puste.showAndWait();
        }
        
        
        
        
    }
    
    
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
