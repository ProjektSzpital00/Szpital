package szpital.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.model.Account;
import szpital.model.Pacjent;
import szpital.util.*;

public class RejestracjaController 
{
    private Account account;
    private LoginController log;
    private ObservableList<Pacjent> pacjentList;
    private ObservableList<Pacjent> findList;

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
    private TableColumn<Pacjent, String> ColumnSala;
    
    @FXML
    private TableColumn<Pacjent, Integer> ColumnNrLozka;
    
    @FXML
    private Label ktoZalogowanyLabel;
    
    
    @FXML
    private void initialize()
    {
        findList = FXCollections.observableArrayList();
        ColumnIdPacjenta.setCellValueFactory(cellData -> cellData.getValue().getIdPacjenta().asObject());
        ColumnImie.setCellValueFactory(cellData->cellData.getValue().getImie());
        ColumnNazwisko.setCellValueFactory(cellData->cellData.getValue().getNazwisko());
        ColumnPesel.setCellValueFactory(cellData->cellData.getValue().getPesel());
        ColumnLekarz.setCellValueFactory(cellData->cellData.getValue().getLekarz());
        ColumnOddzial.setCellValueFactory(cellData->cellData.getValue().getOddzial());
        ColumnGrKrwii.setCellValueFactory(cellData->cellData.getValue().getGrKrwii());
        ColumnSala.setCellValueFactory(cellData -> cellData.getValue().getNazwaSali());
        ColumnNrLozka.setCellValueFactory(cellData -> cellData.getValue().getNrLozka().asObject());
    }
    
    @FXML
    public void addPacjent()
    {
        Pacjent nowyPacjent = null;
        wczytajAddPacjentScreen(nowyPacjent, "Dodawanie pacjenta");
    }
    
    @FXML
    public void editPacjent()
    {
        Pacjent wybranyPacjent = tabela.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            wczytajAddPacjentScreen(wybranyPacjent, "Edytowanie pacjenta");
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego chcesz edytować");
        }
    }
    
    @FXML
    public void deletePacjent()
    {
        Pacjent wybranyPacjent = tabela.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Potwierdzenie operacji");
            alert.setHeaderText("Usunięcie pacjenta");
            alert.setContentText("Usunąć pacjenta o następujących danych:\n\n"
                            + "ID:\t\t\t\t"+wybranyPacjent.getIdPacjenta().getValue()+"\n"
                            + "Imie:\t\t\t\t"+wybranyPacjent.getImie().getValue()+"\n"
                            + "Nazwisko:\t\t"+wybranyPacjent.getNazwisko().getValue()+"\n"
                            + "Pesel:\t\t\t"+wybranyPacjent.getPesel().getValue()+"\n"
                            + "Gr Krwi:\t\t\t"+wybranyPacjent.getGrKrwii().getValue()+"\n"
                            + "Lekarz:\t\t\t"+wybranyPacjent.getLekarz().getValue()+"\n"
                            + "Oddzial:\t\t\t"+wybranyPacjent.getOddzial().getValue());
            
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                try
                {  
                    PacjentUtil.deletePacjent(wybranyPacjent.getIdPacjenta());
                    PacjentUtil.clearPacjentList();
                    setPacjentList(PacjentUtil.getPacjentList());
                }
                catch (SQLException | ClassNotFoundException ex) 
                {
                    Utils.alertWyswietl(ex);
                }
            }
            else 
            {
                alert.close();
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta którego chcesz edytować");
        }
    }
    
    @FXML
    public void search()
    {
        if(Utils.walidacjaPola(searchTextField, "Search"))
        {
            findList = FXCollections.observableArrayList();
            try
            {
                Long l = new Long(searchTextField.getText());
                for(Pacjent p : pacjentList)
                {
                    if(p.getPesel().getValue().equals(searchTextField.getText()) || p.getIdPacjenta().getValue().toString().equals(searchTextField.getText()))
                    {
                        findList.add(p);
                    }
                }
                
            }
            catch(NumberFormatException nfe)
            {
                for(Pacjent p : pacjentList)
                {
                    String t1 = p.getImie().getValue();
                    String t2 = p.getNazwisko().getValue();
                    String t3 = t1 + " " + t2;
                    String t4 = t2 + " " + t1;
                    
                    if(t1.equals(searchTextField.getText()) || t2.equals(searchTextField.getText()) || t3.equals(searchTextField.getText()) || t4.equals(searchTextField.getText()))
                    {
                        findList.add(p);
                    }
                }
            }
            
            if(!findList.isEmpty())
            {
                tabela.setItems(findList);
            }
            else
            {
                Utils.informacjaWyswietl("Nie znaleziono pacjenta o podanych danych:\n\n"
                        + searchTextField.getText());
            }
        }
    }
    
    @FXML
    public void statystyka() throws SQLException, ClassNotFoundException
    {
        try
        {

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Statystyki.fxml"));

            AnchorPane anchorPane = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statystyka");

            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);

            dialogStage.showAndWait();
        }
        catch (IllegalStateException | IOException exc) 
        {
            Utils.alertWyswietl(exc);
        }
        
        StatystykaUtil.getStatystykaList();
    }
    
    @FXML
    public void drukuj()
    {
        Pacjent wybranyPacjent = tabela.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            try 
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Potwierdzenie operacji");
                alert.setHeaderText("Drukowanie");
                alert.setContentText("Wydrukować kartę informacyjną dla danego pacjenta:\n\n"
                            + "ID:\t\t\t\t"+wybranyPacjent.getIdPacjenta().getValue()+"\n"
                            + "Imie:\t\t\t\t"+wybranyPacjent.getImie().getValue()+"\n"
                            + "Nazwisko:\t\t"+wybranyPacjent.getNazwisko().getValue()+"\n"
                            + "Pesel:\t\t\t"+wybranyPacjent.getPesel().getValue()+"\n");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    Wypis.wydrukuj(wybranyPacjent, BadaniaUtil.getBadaniaList(wybranyPacjent.getIdPacjenta().getValue()), LekiUtil.getLekiList(wybranyPacjent.getIdPacjenta().getValue()));
                    Utils.informacjaWyswietl("Pomyślnie wydrukowano");
                }
                else
                {
                    alert.close();
                }
            } 
            catch(FileNotFoundException | SQLException | ClassNotFoundException exc)
            {
                Utils.alertWyswietl(exc);
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta dla którego chcesz wydrukować");
        }
    }
    
     @FXML
    public void sendMail()
    {
        Pacjent wybranyPacjent = tabela.getSelectionModel().getSelectedItem();
        if(wybranyPacjent != null)
        {
            wczytajAddMail("wyślij mail", wybranyPacjent.getIdPacjenta().getValue());
        }
        else 
        {
            Utils.alertWyswietl("Nie wybrano pacjenta!", "Proszę wybrać pacjenta dla którego chcesz wysłać maila");
        }
    }
    
    @FXML
    public void logout()
    {
        PacjentUtil.clearPacjentList();
        LekarzUtil.clearLekarzList();
        OddzialUtil.clearOddzialyList();
        log.setLoginScreen();
    }
    
    private void wczytajAddPacjentScreen(Pacjent pacjent, String stageTitle)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddPacjentScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            Stage dialogStage = new Stage();
            dialogStage.setTitle(stageTitle);
            dialogStage.setHeight(650);
            dialogStage.setWidth(350);
            dialogStage.setMinHeight(650);
            dialogStage.setMinWidth(350);
            dialogStage.setMaxHeight(650);
            dialogStage.setMaxWidth(350);
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            
            AddPacjentController addPacjentController = loader.getController();
            addPacjentController.setRejestracjaController(this);
            addPacjentController.setStage(dialogStage);
            addPacjentController.setPacjent(pacjent);
            addPacjentController.setOddzialyList(OddzialUtil.getOddzialList());
            addPacjentController.setGrKrwii();
            addPacjentController.init(LozkoUtil.getLozkoList(), SalaPacjentUtil.getSalaPacjentList(), LekarzUtil.getLekarzList());
            addPacjentController.setSalaPacjentList();
            addPacjentController.setLozkaList();
            addPacjentController.setLekarzOddzialList();
            addPacjentController.init2();
            
            dialogStage.showAndWait();
        }
        catch (IllegalStateException | IOException | SQLException | ClassNotFoundException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }

    private void wczytajAddMail(String stageTitle, Integer id)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddMail.fxml"));
            AnchorPane anchorPane = loader.load();
            MailUtil mailUtil = new MailUtil();

            Stage dialogStage = new Stage();
            Scene scene = new Scene(anchorPane);
            dialogStage.setScene(scene);
            dialogStage.setTitle(mailUtil.getMail(id));

            AddMailController addMailController = loader.getController();
            addMailController.setRejestracjaController(this, id);
            addMailController.setStage(dialogStage);

            dialogStage.showAndWait();
        }
        catch (SQLException | IllegalStateException | IOException | ClassNotFoundException exc)
        {
            Utils.alertWyswietl(exc);
        }
    }

    public void setAccount(Account account)
    {
        this.account = account;
        ktoZalogowanyLabel.setText(account.getImie() + " " + account.getNazwisko());
    }
    
    public void setPacjentList(ObservableList<Pacjent> pacjentList) 
    {
        this.pacjentList = pacjentList;
        tabela.setItems(pacjentList);
        searchTextField.textProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                if(newValue.isEmpty())
                {
                    tabela.setItems(pacjentList);
                }
                
                if((oldValue.isEmpty() && !(newValue.isEmpty())) || !(oldValue.equals(newValue)))
                {
                    findList.clear();
                    try
                    {
                        Long l = new Long(searchTextField.getText());
                        for(Pacjent p : pacjentList)
                        {
                            if(p.getPesel().getValue().startsWith(searchTextField.getText()) || p.getIdPacjenta().getValue().toString().startsWith(searchTextField.getText()))
                            {
                                findList.add(p);
                            }
                        }

                    }
                    catch(NumberFormatException nfe)
                    {
                        for(Pacjent p : pacjentList)
                        {
                            String t1 = p.getImie().getValue();
                            String t2 = p.getNazwisko().getValue();
                            String t3 = t1 + " " + t2;
                            String t4 = t2 + " " + t1;

                            if(t1.startsWith(searchTextField.getText()) || t2.startsWith(searchTextField.getText()) || t3.startsWith(searchTextField.getText()) || t4.startsWith(searchTextField.getText()))
                            {
                                findList.add(p);
                            }
                        }
                    }
                    if(!findList.isEmpty())
                    {
                        tabela.setItems(findList);
                    }
                    
                }
            }
        });
    }

    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}
