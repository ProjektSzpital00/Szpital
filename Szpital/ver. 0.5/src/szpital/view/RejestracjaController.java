package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.model.Account;
import szpital.model.Pacjent;
import szpital.util.LekarzUtil;
import szpital.util.PacjentUtil;
import szpital.util.MyAlert;
import szpital.util.OddzialUtil;

public class RejestracjaController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;

    @FXML
    private TextField searchTextField;
    
    @FXML
    private TableView<Pacjent> tabela;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnIdPacjenta;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnImie;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnNazwisko;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnPesel;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnLekarz;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnOddzial;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnGrKrwii;

    @FXML
    private void initialize()
    {
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        ColumnLekarz.setCellValueFactory(cellData->cellData.getValue().getLekarz());
        ColumnOddzial.setCellValueFactory(cellData->cellData.getValue().getOddzial());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
    }
    
    @FXML
    public void addPacjent()
    {
        Pacjent nowyPacjent = null;
        wczytajAddPacjentScreen(nowyPacjent);
    }
    
    @FXML
    public void editPacjent()
    {
        Pacjent wybranyPacjent = tabela.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            wczytajAddPacjentScreen(wybranyPacjent);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Nie wybrano pacjenta!");
            alert.setContentText("Proszę wybrać pacjenta którego chcesz edytować");
            alert.showAndWait();
        }
    }
    
    @FXML
    public void deletePacjent()
    {
        
    }
    
    @FXML
    public void search()
    {
        
    }
    
    @FXML
    public void logout()
    {
        PacjentUtil.clearPacjentList();
        log.setLoginScreen();
    }
    
    private void wczytajAddPacjentScreen(Pacjent pacjent)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddPacjentScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edycja Pacjent");
        
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            
            AddPacjentController addPacjentController = loader.getController();
            addPacjentController.setRejestracjaController(this);
            addPacjentController.setStage(dialogStage);
            addPacjentController.setPacjent(pacjent);
            addPacjentController.setLekarzList(LekarzUtil.getLekarzList());
            addPacjentController.setOddzialyList(OddzialUtil.getOddzialList());
            addPacjentController.setGrKrwii();
            dialogStage.showAndWait();
        }
        catch (IllegalStateException | IOException | SQLException | ClassNotFoundException exc) 
        {
            MyAlert.alertWyswietl(exc);
        }
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) 
    {
        this.pacjentList = pacjentList;
        tabela.setItems(pacjentList);
    }
    
    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}
