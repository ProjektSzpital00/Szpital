package szpital.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.model.Account;
import szpital.model.Pacjent;
import szpital.util.LekarzUtil;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;
import szpital.util.Utils;

public class LekarzController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;
    
    
    @FXML
    TableView<Pacjent> tabelaPacjentow;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnIdPacjenta;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnImie;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnNazwisko;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnPesel;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnOddzial;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnGrKrwii;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnRecepty;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnLeki;
    
    
    @FXML
    private void initialize()
    {
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        
        ColumnOddzial.setCellValueFactory(cellData->cellData.getValue().getOddzial());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
    }
    
    
    
    
    
    @FXML
    public void logout()
    {
        PacjentUtil.clearPacjentList();
        LekarzUtil.clearLekarzList();
        OddzialUtil.clearOddzialyList();
        log.setLoginScreen();
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
    
    
    @FXML
    public void badania()
    {
         FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BadaniaPacjenta.fxml"));
         AnchorPane anchorPane = new AnchorPane();
        try {
            anchorPane = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LekarzController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("asdasdas");
        
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
    }
    
    
    
    
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) 
    {
        //ObservableList<Pacjent> listaFiltrowana;
        /*
        IntegerProperty i = new SimpleIntegerProperty(1);
        
        ArrayList<Pacjent> ss = new ArrayList<>(pacjentList);
        
        for(Pacjent p: ss)
        {
            if(!p.getIdLekarza().equals(i))
            {
                ss.remove(p);
            }
            
        }
        */
        //ObservableList<Pacjent> listaFiltrowana = FXCollections.observableArrayList(ss);
        this.pacjentList = FXCollections.observableArrayList();
        
        for(Pacjent p : pacjentList)
            if(p.getIdLekarza().getValue().equals(account.getId_lekarza()))
                this.pacjentList.add(p);
        
        //this.pacjentList = pacjentList;
        tabelaPacjentow.setItems(this.pacjentList);
    }
    
    /*
    private void wczytajAddPacjentScreen()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DodajRecept.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dodaj recepte");
        
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        }
            
            AddPacjentController addPacjentController = loader.getController();
            addPacjentController.setRejestracjaController(this);
            addPacjentController.setStage(dialogStage);
            addPacjentController.setPacjent(pacjent);
            addPacjentController.setLekarzList(LekarzUtil.getLekarzList());
            addPacjentController.setOddzialyList(OddzialUtil.getOddzialList());
            addPacjentController.setGrKrwii();
            dialogStage.showAndWait();
        }

        catch (IllegalStateException | IOException exc) 
        {
            Utils.alertWyswietl(exc);
        }
            
    }
    */
}
