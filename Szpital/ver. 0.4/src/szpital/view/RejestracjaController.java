package szpital.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import szpital.model.Account;
import szpital.model.Pacjent;
import szpital.util.ListaPacjentow;

public class RejestracjaController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;

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
    private TableColumn<Pacjent, Integer> ColumnIdLekarza;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnIdOddzialu;
    
    @FXML
    private TableColumn<Pacjent, String> ColumnGrKrwii;

    @FXML
    private void initialize()
    {
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        ColumnIdLekarza.setCellValueFactory(cellData->cellData.getValue().getIdLekarza().asObject());
        ColumnIdOddzialu.setCellValueFactory(cellData->cellData.getValue().getIdOddzialu().asObject());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
    }
    
    @FXML
    public void logout()
    {
        ListaPacjentow.clear();
        log.setLoginScreen();
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
