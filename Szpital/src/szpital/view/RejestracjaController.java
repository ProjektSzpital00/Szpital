package szpital.view;

import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import szpital.model.Account;
import szpital.model.Lekarz;
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.util.Laczenie;
import szpital.util.LekarzUtil;
import szpital.util.MyAlert;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;

public class RejestracjaController 
{
    private boolean odclick;
    private Account account;
    private LoginController log;
    private Pacjent currentPacjent;
    private ObservableList <Pacjent> pacjentList;
    private ObservableList <String> lekarzList;
    private ObservableList <String> oddzialyList;
    private ObservableList <String> grKrwiiList;

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
    private TextField idPacjentaField;
    
    @FXML
    private TextField imieField;
    
    @FXML
    private TextField nazwiskoField;
    
    @FXML
    private TextField peselField;
    
    @FXML
    private ChoiceBox <String> lekarz;
    
    @FXML
    private ChoiceBox <String> oddzial;
    
    @FXML
    private ChoiceBox <String> grKrwii;

    @FXML
    private void initialize()
    {
        odclick = false;
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        ColumnLekarz.setCellValueFactory(cellData->cellData.getValue().getLekarz());
        ColumnOddzial.setCellValueFactory(cellData->cellData.getValue().getOddzial());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
    }
    
    @FXML
    private void onClic()
    {
        if(!(tabela.getSelectionModel().getSelectedItem().equals(currentPacjent)))
        {
            setOtherUtil();
        }
        else
        {
            if(odclick)
                clearFields();
            else
                setOtherUtil();
        } 
    }
    
    @FXML
    private void addPacjent()
    {
        Pacjent nowyPacjent;
        Integer idLekarza;
        Integer idOddzialu;
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie operacji");
        alert.setHeaderText("Dodanie nowego pacjenta");
        alert.setContentText("Dodać pacjenta o następujących danych:\n\n"
                        + "\tID:\t\t\t\t"+idPacjentaField.getText()+"\n"
                        + "\tImie:\t\t\t\t"+imieField.getText()+"\n"
                        + "\tNazwisko:\t\t"+nazwiskoField.getText()+"\n"
                        + "\tPesel:\t\t\t"+peselField.getText()+"\n"
                        + "\tGr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                        + "\tLekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                        + "\tOddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            try
            {    
                String temp = lekarz.getSelectionModel().getSelectedItem();
                String [] t = temp.split(" ");
                idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(),t[0] , t[1]);

                temp = oddzial.getSelectionModel().getSelectedItem();
                idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);

                nowyPacjent = new Pacjent(new Integer(idPacjentaField.getText()), imieField.getText(), nazwiskoField.getText(), peselField.getText(), idLekarza, lekarz.getSelectionModel().getSelectedItem(), idOddzialu, oddzial.getSelectionModel().getSelectedItem(), grKrwii.getSelectionModel().getSelectedItem());

                PacjentUtil.addPacjent(Laczenie.getStatement(), nowyPacjent);
                
                setPacjentList(PacjentUtil.getPacjentList());
            }
            catch (SQLException | ClassNotFoundException ex) 
            {
                MyAlert.alertWyswietl(ex);
            }
        } 
        else 
        {
            alert.close();
        }   
    }
    
    @FXML
    private void editPacjent()
    {
        currentPacjent = tabela.getSelectionModel().getSelectedItem();
        if(currentPacjent != null)
        {
            Integer idLekarza;
            Integer idOddzialu;
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Edycja danych pacjenta");
            alert.setContentText("Zmienić poprzednie dane pacjenta na następujące:\n\n"
                            + "\tID:\t\t\t\t"+idPacjentaField.getText()+"\n"
                            + "\tImie:\t\t\t\t"+imieField.getText()+"\n"
                            + "\tNazwisko:\t\t"+nazwiskoField.getText()+"\n"
                            + "\tPesel:\t\t\t"+peselField.getText()+"\n"
                            + "\tGr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                            + "\tLekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                            + "\tOddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem());

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                try
                {    
                    currentPacjent.setIdPacjenta(new SimpleIntegerProperty(new Integer(idPacjentaField.getText())));
                    currentPacjent.setImie(new SimpleStringProperty(imieField.getText()));
                    currentPacjent.setNazwisko(new SimpleStringProperty(nazwiskoField.getText()));
                    currentPacjent.setPesel(new SimpleStringProperty(peselField.getText()));

                    String temp = lekarz.getSelectionModel().getSelectedItem();
                    String [] t = temp.split(" ");
                    idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(),t[0] , t[1]);
                    currentPacjent.setIdLekarza(new SimpleIntegerProperty(idLekarza));
                    currentPacjent.setLekarz(new SimpleStringProperty(temp));

                    temp = oddzial.getSelectionModel().getSelectedItem();
                    idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);
                    currentPacjent.setIdOddzialu(new SimpleIntegerProperty(idOddzialu));
                    currentPacjent.setOddzial(new SimpleStringProperty(oddzial.getSelectionModel().getSelectedItem()));

                    currentPacjent.setGrKrwii(new SimpleStringProperty(grKrwii.getSelectionModel().getSelectedItem()));

                    PacjentUtil.updatePacjent(Laczenie.getStatement(), currentPacjent);
                    
                    setPacjentList(PacjentUtil.getPacjentList());
                }
                catch (SQLException | ClassNotFoundException ex) 
                {
                    MyAlert.alertWyswietl(ex);
                }
            } 
            else 
            {
                alert.close();
            }
        }
        else
        {
            MyAlert.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego chcesz edytować");
        }
    }
    
    @FXML
    private void deletePacjent()
    {
        currentPacjent = tabela.getSelectionModel().getSelectedItem();
        if(currentPacjent != null)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Usunięcie pacjenta");
            alert.setContentText("Usunąć pacjenta o następujących danych:\n\n"
                            + "\tID:\t\t\t\t"+idPacjentaField.getText()+"\n"
                            + "\tImie:\t\t\t\t"+imieField.getText()+"\n"
                            + "\tNazwisko:\t\t"+nazwiskoField.getText()+"\n"
                            + "\tPesel:\t\t\t"+peselField.getText()+"\n"
                            + "\tGr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                            + "\tLekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                            + "\tOddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem());
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                try
                {  
                    PacjentUtil.deletePacjent(Laczenie.getStatement(), currentPacjent.getIdPacjenta());
                    
                    setPacjentList(PacjentUtil.getPacjentList());
                }
                catch (SQLException | ClassNotFoundException ex) 
                {
                    MyAlert.alertWyswietl(ex);
                }
            }
            else 
            {
                alert.close();
            }
        }
        else
        {
            MyAlert.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego chcesz edytować");
        }
    }
    
    @FXML
    private void search()
    {
        
    }
    
    @FXML
    private void logout()
    {
        PacjentUtil.clearPacjentList();
        log.setLoginScreen();
    }
    
    private void clearFields()
    {
        idPacjentaField.clear();
        imieField.clear();
        nazwiskoField.clear();
        peselField.clear();
        lekarz.getSelectionModel().clearSelection();
        oddzial.getSelectionModel().clearSelection();
        grKrwii.getSelectionModel().clearSelection();
        tabela.getSelectionModel().clearSelection();
        odclick = false;
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
    
    public void setOtherUtil()
    {
        currentPacjent = tabela.getSelectionModel().getSelectedItem();
        if(currentPacjent != null)
        {    
            idPacjentaField.setText(currentPacjent.getIdPacjenta().getValue().toString());
            imieField.setText(currentPacjent.getImie().getValue());
            nazwiskoField.setText(currentPacjent.getNazwisko().getValue());
            peselField.setText(currentPacjent.getPesel().getValue());
            
            lekarz.setItems(lekarzList);
            lekarz.getSelectionModel().select(currentPacjent.getLekarz().getValue());
            
            oddzial.setItems(oddzialyList);
            oddzial.getSelectionModel().select(currentPacjent.getOddzial().getValue());
            
            grKrwii.setItems(grKrwiiList);
            grKrwii.getSelectionModel().select(currentPacjent.getGrKrwii().getValue());
            
            odclick = true;
        }
    }
    
    public void setLekarzList(ObservableList<Lekarz> lekarzList) 
    {       
        this.lekarzList = FXCollections.observableArrayList();
        for(Lekarz l : lekarzList)
            this.lekarzList.add(l.getNazwisko().getValue()+" "+l.getImie().getValue());
    }

    public void setOddzialyList(ObservableList<Oddzial> oddzialyList) 
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            this.oddzialyList.add(o.getNazwaOddzialu().getValue());
    }
    
    public void setGrKrwii()
    {
        grKrwiiList = FXCollections.observableArrayList();
        grKrwiiList.addAll("A rh+", "A rh-", "B rh+", "B rh-", "AB rh+", "AB rh-", "0 rh+", "0 rh-");
    }
}
