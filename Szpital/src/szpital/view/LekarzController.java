package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
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
import szpital.model.Badania;
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
        Pacjent wybranyPacjent = tabelaPacjentow.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            try
            {

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BadaniaPacjenta.fxml"));

                AnchorPane anchorPane = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Badania");

                Scene scene = new Scene(anchorPane);
                dialogStage.setScene(scene);

                BadaniaController badaniaController = loader.getController();
                badaniaController.setRejestracjaController(this);
                badaniaController.setStage(dialogStage);
                badaniaController.setWybranyPacjent(wybranyPacjent);
                //System.out.println("Do badani contrroller idize " + wybranyPacjent.getIdPacjenta().getValue());
                badaniaController.ladujListe(wybranyPacjent.getIdPacjenta().getValue());
                
                dialogStage.showAndWait();
            }
            catch (IllegalStateException | IOException exc) 
            {
                Utils.alertWyswietl(exc);
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego badania chcesz wyświetlić.");
        }
        
    }
    
    @FXML
    public void dodajRezerwacjeSali()
    {
        try
            {

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("RezerwacjaSaliScreen.fxml"));

                AnchorPane anchorPane = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Rezerwacja sali");

                Scene scene = new Scene(anchorPane);
                dialogStage.setScene(scene);
                
                RezerwacjaSaliController rezerwacjaSaliController = loader.getController();
                rezerwacjaSaliController.setLekarzController(this);
                rezerwacjaSaliController.setStage(dialogStage);
                //rezerwacjaSaliController.setRezerwacje()
                
                dialogStage.showAndWait();
            }
            catch (IllegalStateException | IOException exc) 
            {
                Utils.alertWyswietl(exc);
            }
    }
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) 
    {
        this.pacjentList = FXCollections.observableArrayList();
        
        for(Pacjent p : pacjentList)
            if(p.getIdLekarza().getValue().equals(account.getId_lekarza()))
                this.pacjentList.add(p);
        
       
        tabelaPacjentow.setItems(this.pacjentList);
    }
}
