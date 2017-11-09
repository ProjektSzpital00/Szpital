package szpital.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import szpital.model.Account;
import szpital.model.Pacjent;

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
    public void logout()
    {
        log.setLoginScreen();
    }
    
    public void prepareTable()
    {
        ColumnIdPacjenta.setCellValueFactory(new PropertyValueFactory<Pacjent,Integer>("ID"));
        ColumnImie.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("Imie"));
        ColumnNazwisko.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("Nazwisko"));
        ColumnPesel.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("Pesel"));
        ColumnIdLekarza.setCellValueFactory(new PropertyValueFactory<Pacjent, Integer>("ID Lekarza"));
        ColumnIdOddzialu.setCellValueFactory(new PropertyValueFactory<Pacjent, Integer>("ID Oddziału"));
        ColumnGrKrwii.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("Gr_Krwi"));
        
        tabela.setItems(pacjentList);
        //tabela.getColumns().clear();
        //tabela.getColumns().addAll(ColumnIdPacjenta,ColumnImie,ColumnNazwisko,ColumnNazwisko,ColumnPesel,ColumnIdLekarza,ColumnIdOddzialu,ColumnGrKrwii);
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) 
    {
        this.pacjentList = pacjentList;
    }
    
    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}
