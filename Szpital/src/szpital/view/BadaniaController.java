/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.model.Badania;
import szpital.model.Pacjent;
import szpital.util.BadaniaUtil;
import szpital.util.LekarzUtil;
import szpital.util.OddzialUtil;
import szpital.util.Utils;

/**
 *
 * @author Bartek
 */
public class BadaniaController 
{
    private ObservableList<Badania> badaniaList;
    private Stage dialoStage;
    
    @FXML
    private TableView<Badania> tabela;
    
    
    @FXML
    private TableColumn<Badania, String> nazwaBadania;
    
    @FXML
    private TableColumn<Badania, String> imiePacjenta;
    
    @FXML
    private TableColumn<Badania, String> dataBadania;
    
    @FXML
    private TableColumn<Badania, String> wynikBadania;
    
    private Integer idPacjenta; // do pobierania danych z bazy(setter)
    private Pacjent wybranyPacjent;
    
    private LekarzController lekarzController;

    private OrdynatorController ordynatorController;
    
    
    public void setBadaniaList()
    {
    
    }
    
    
    
    public void ladujListe(Integer i)
    {
            try {    
                
                badaniaList = BadaniaUtil.getBadaniaList(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        nazwaBadania.setCellValueFactory(cellData->cellData.getValue().getNazwaBadania());
        imiePacjenta.setCellValueFactory(cellData->cellData.getValue().getImieNazwisko());
        dataBadania.setCellValueFactory(cellData->cellData.getValue().getDataBadania());
        wynikBadania.setCellValueFactory(cellData->cellData.getValue().getWynikBadania());
        
        tabela.setItems(badaniaList);
    
    }
    
    @FXML
    public void dodajBadanie()
    {
        Badania badanie = null;
        wczytajAddBadanieScreen(badanie, "Dodaj nowe badanie");
    }
    
    @FXML
    public void editBadanie()
    {
        Badania badanie = tabela.getSelectionModel().getSelectedItem();
        if(badanie != null)
        {
            wczytajAddBadanieScreen(badanie, "Edytuj badanie");
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego badania chcesz wyświetlić.");
        }
    }
    
    public void setRejestracjaController(OrdynatorController ordynatorController)
    {
        this.ordynatorController = ordynatorController;
    }
    
    public void setId(Integer i)
    {
        idPacjenta = i;
    }
    
    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

    public void setRejestracjaController1(LekarzController lekarzController)
    {
        this.lekarzController = lekarzController;
    }
    
    private void wczytajAddBadanieScreen(Badania badanie, String stageTitle)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddBadanie.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
        
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            dialogStage.setMinHeight(400);
            dialogStage.setMinWidth(450);
            dialogStage.setHeight(400);
            dialogStage.setWidth(450);
            dialogStage.setMaxHeight(400);
            dialogStage.setMaxWidth(450);
            
            AddBadanieController addBadanieController = loader.getController();
            addBadanieController.setBadanieController(this);
            addBadanieController.setStage(dialogStage);
            addBadanieController.setBadanie(badanie);
            addBadanieController.setZaznaczonyPacjent(idPacjenta);
            addBadanieController.setWybranyPacjent(wybranyPacjent);
           
            addBadanieController.setListaBadan();
            dialogStage.showAndWait();
        }
        catch (IllegalStateException | IOException exc) 
        {
            Utils.alertWyswietl(exc);
        }
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
