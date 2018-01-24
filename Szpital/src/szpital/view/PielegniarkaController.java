package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
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
import szpital.model.Account;
import szpital.model.Dyzur;
import szpital.util.DyzurUtil;
import szpital.model.Pacjent;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;
import szpital.util.PielegniarkaUtil;
import szpital.util.Utils;

public class PielegniarkaController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;
    private ObservableList<Dyzur> dyzuryList;
    
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
    private TableView<Dyzur> tabelaDyzurow;

    @FXML
    private TableColumn<Dyzur, String> ColumnDyzurData;

    @FXML
    private TableColumn<Dyzur, String> ColumnDyzurGodzina;
    
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
        ColumnDyzurData.setCellValueFactory(cellData -> cellData.getValue().getTerminDataOd());
        ColumnDyzurGodzina.setCellValueFactory(cellData->cellData.getValue().getTerminCzasOd());
    }
    
     @FXML
    public void logout()
    {
        PacjentUtil.clearPacjentList();
        PielegniarkaUtil.clearPielegniarkaList();
        OddzialUtil.clearOddzialyList();
        log.setLoginScreen();
    }
    
     public void setAccount(Account account)
    {
        this.account = account;
        ktoZalogowany.setText(account.getImie()+" "+account.getNazwisko());
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
                badaniaController.setPielegniarkaController(this);
                badaniaController.setStage(dialogStage);
                badaniaController.setWybranyPacjent(wybranyPacjent);
               
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

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LekiPielegniarka.fxml"));

                AnchorPane anchorPane = loader.load();

                Stage dialogStage = new Stage();
                dialogStage.setTitle("Leki");

                Scene scene = new Scene(anchorPane);
                dialogStage.setScene(scene);

                LekiPielegniarkaController lekiController = loader.getController();
                lekiController.setPielegniarkaController(this);
                lekiController.setStage(dialogStage);
                lekiController.setWybranyPacjent(wybranyPacjent);
                lekiController.ladujListe(wybranyPacjent.getIdPacjenta().getValue());
                
                dialogStage.showAndWait();
            }
            catch (IllegalStateException | IOException exc) 
            {
                exc.printStackTrace();
                Utils.alertWyswietl(exc);
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego leki chcesz wyświetlić.");
        }
    }
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) throws SQLException, ClassNotFoundException 
    {
       this.pacjentList = FXCollections.observableArrayList();
        for(Pacjent p : pacjentList){
        if(p.getIdOddzialu().getValue().equals(account.getId_oddzialu()))
            this.pacjentList.add(p);}

        tabelaPacjentow.setItems(this.pacjentList);
    }
    
    public void setDyzuryTabelka()
    {
        try
        {
            DyzurUtil.clearDyzurList2();
            dyzuryList = DyzurUtil.getDyzurList(account.getId_personel(), false);
            tabelaDyzurow.setItems(dyzuryList);
            tabelaDyzurow.getSortOrder().add(ColumnDyzurData);
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            Utils.alertWyswietl(ex);
        } 
    }

    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}