package szpital.view;

import java.io.IOException;
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
import szpital.model.Leki;
import szpital.model.Pacjent;
import szpital.util.BadaniaUtil;
import szpital.util.LekiUtil;
import szpital.util.Utils;

public class LekiController 
{
    private ObservableList<Leki> lekiList;
    private Stage dialoStage;
    
    @FXML
    private TableView<Leki> tabela;
    
    
    @FXML
    private TableColumn<Leki, String> nazwaLeku;
    
    @FXML
    private TableColumn<Leki, String> imiePacjenta;
    
    @FXML
    private TableColumn<Leki, String> dataOd;
    
    @FXML
    private TableColumn<Leki, String> dataDo;
    
    @FXML
    private TableColumn<Leki, String> dawkowanie;
    
    private Integer idPacjenta; // do pobierania danych z bazy(setter)
    private Pacjent wybranyPacjent;
    
    private LekarzController lekarzController;
    private OrdynatorController ordynatorController;
    
    
    
    
    
    
     public void ladujListe(Integer i)
    {
       
            try {    
                lekiList = LekiUtil.getLekiList(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        nazwaLeku.setCellValueFactory(cellData->cellData.getValue().getNazwa());
        //imiePacjenta.setCellValueFactory(cellData->cellData.getValue().getImieNazwisko());
        dataOd.setCellValueFactory(cellData->cellData.getValue().getOdTermin());
        dataDo.setCellValueFactory(cellData->cellData.getValue().getDoTermin());
        dawkowanie.setCellValueFactory(cellData->cellData.getValue().getDawkowanie());
        
        
        tabela.setItems(lekiList);
    
    }
    
    
    
    
    @FXML
    public void dodajLek()
    {
        Leki lek = null;
        wczytajAddLekScreen(lek, "Dodaj nowe badanie");
    }
    
    @FXML
    public void edytujLek()
    {
        
    }
    
    
    private void wczytajAddLekScreen(Leki lek, String stageTitle)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddLeki.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.setMinHeight(400);
            dialogStage.setMinWidth(400);
            dialogStage.setHeight(400);
            dialogStage.setWidth(400);
            dialogStage.setMaxHeight(400);
            dialogStage.setMaxWidth(400);
        
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            
            AddLekiController addLekiController = loader.getController();
            addLekiController.setLekiController(this);
            addLekiController.setStage(dialogStage);
            //addLekiController.setLek(lek);
            addLekiController.setZaznaczonyPacjent(idPacjenta);
            addLekiController.setWybranyPacjent(wybranyPacjent);
           // System.out.println(wybranyPacjent.getImie());
            
            
            //addPacjentController.setLekarzList(LekarzUtil.getLekarzList());
            //addPacjentController.setOddzialyList(OddzialUtil.getOddzialList());
            addLekiController.setListaLekow();
            dialogStage.showAndWait();
        }
        catch (IllegalStateException | IOException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }
    
    
    
    public void setRejestracjaController(LekarzController lekarzController) 
    {
        this.lekarzController = lekarzController;
    }

    public void setRejestracjaControllerOrdynator(OrdynatorController ordynatorController)
    {
        this.ordynatorController = ordynatorController;
    }

    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }
    
    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
}
