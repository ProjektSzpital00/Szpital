package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.model.Rezerwacja;
import szpital.util.LekarzUtil;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;
import szpital.util.PielegniarkaUtil;
import szpital.util.Utils;

public class PielegniarkaController 
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
    private TableColumn<Pacjent, Integer> ColumnSala;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnMiejsce;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnSalaR;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnDataR;
    
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
                badaniaController.setRejestracjaController1(this);
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
                lekiController.setRejestracjaControllerPielegniarka(this); 
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
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) throws SQLException, ClassNotFoundException 
    {
       this.pacjentList = FXCollections.observableArrayList();
        for(Pacjent p : pacjentList)
        if(p.getIdOddzialu().getValue().equals(account.getId_oddzialu()))
            this.pacjentList.add(p);
          tabelaPacjentow.setItems(this.pacjentList);

    }
    
    
   
    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}
