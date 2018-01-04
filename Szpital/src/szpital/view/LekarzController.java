package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import szpital.model.Account;
import szpital.model.Pacjent;
import szpital.model.Rezerwacja;
import szpital.util.LekarzUtil;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;
import szpital.util.RezerwacjaUtil;
import szpital.util.SalaUtil;
import szpital.util.Utils;

public class LekarzController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;
    private ObservableList <Rezerwacja> rezerwacjaList;
    
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
    private TableColumn<Pacjent, Integer> ColumnSala;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnMiejsce;
    
    
    @FXML
    private TableView<Rezerwacja> tabelaRezerwacji;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnSalaR;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnDataR;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnInformacjaR;
    
    
    
    @FXML
    private Label ktoZalogowany;
    
    
    @FXML
    private void initialize()
    {
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        ColumnOddzial.setCellValueFactory(cellData->cellData.getValue().getOddzial());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
        ColumnSala.setCellValueFactory(cellData->cellData.getValue().getNrSali().asObject());
        ColumnMiejsce.setCellValueFactory(cellData->cellData.getValue().getNrLozka().asObject());
        
        
        ColumnSalaR.setCellValueFactory(cellData -> cellData.getValue().getSala());
        ColumnDataR.setCellValueFactory(cellData->cellData.getValue().getTermin());
        ColumnInformacjaR.setCellValueFactory(cellData->cellData.getValue().getInformacja());
        
        ColumnDataR.setSortType(TableColumn.SortType.ASCENDING);
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
        ktoZalogowany.setText(account.getImie()+" "+account.getNazwisko());
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
    public void leki()
    {
        Pacjent wybranyPacjent = tabelaPacjentow.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            try
            {

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Leki.fxml"));

                AnchorPane anchorPane = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Leki");

                Scene scene = new Scene(anchorPane);
                dialogStage.setScene(scene);

                LekiController lekiController = loader.getController();
                lekiController.setRejestracjaController(this);
                lekiController.setStage(dialogStage);
                lekiController.setWybranyPacjent(wybranyPacjent);
                //System.out.println("Do badani contrroller idize " + wybranyPacjent.getIdPacjenta().getValue());
                lekiController.ladujListe(wybranyPacjent.getIdPacjenta().getValue());
                
                dialogStage.showAndWait();
            }
            catch (IllegalStateException | IOException exc) 
            {
                exc.printStackTrace();
                Utils.alertWyswietl(exc);
                //System.out.println(exc);
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
            rezerwacjaSaliController.setSaleList(SalaUtil.getSalaList());
            rezerwacjaSaliController.setDate();
            rezerwacjaSaliController.setRezerwacjeList();
            rezerwacjaSaliController.init();

            dialogStage.showAndWait();
        }
        catch (SQLException | ClassNotFoundException | IllegalStateException | IOException exc) 
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
    
    public void setRezerwacjeSal()
    {
        try
        {
            rezerwacjaList = RezerwacjaUtil.getRezerwacjaList(account.getId_lekarza());
            tabelaRezerwacji.setItems(rezerwacjaList);
            tabelaRezerwacji.getSortOrder().add(ColumnDataR);
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            Utils.alertWyswietl(ex);
        } 
    }

    public Account getAccount() 
    {
        return account;
    }
}
