package szpital.view;

import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import szpital.model.Lekarz;
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.util.Laczenie;
import szpital.util.LekarzUtil;
import szpital.util.Utils;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;
//sialalalall
public class AddPacjentController 
{
    private Stage dialoStage;
    private Pacjent pacjent;
    private RejestracjaController rejestracjaController;
    private ObservableList <String> lekarzList;
    private ObservableList <String> oddzialyList;
    private ObservableList <String> grKrwiiList;
    
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
    private void cancel()
    {
        dialoStage.close();
    }
    
    @FXML
    private void ok()
    {
        Integer idLekarza;
        Integer idOddzialu;
         
        try 
        {
            if(pacjent != null)
            {
                if(Utils.walidacjaPola(imieField, "Imie") && Utils.walidacjaPola(nazwiskoField, "Nazwisko") && Utils.walidacjaPolaNumerycznego(peselField, "Pesel"))
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potwierdzenie operacji");
                    alert.setHeaderText("Edycja danych pacjenta");
                    alert.setContentText("Zmienić poprzednie dane pacjenta na następujące:\n\n"
                                + "ID:\t\t\t\t"+idPacjentaField.getText()+"\n"
                                + "Imie:\t\t\t\t"+imieField.getText()+"\n"
                                + "Nazwisko:\t\t"+nazwiskoField.getText()+"\n"
                                + "Pesel:\t\t\t"+peselField.getText()+"\n"
                                + "Gr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                                + "Lekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                                + "Oddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        pacjent.setImie(new SimpleStringProperty(imieField.getText()));
                        pacjent.setNazwisko(new SimpleStringProperty(nazwiskoField.getText()));
                        pacjent.setPesel(new SimpleStringProperty(peselField.getText()));

                        String temp = lekarz.getSelectionModel().getSelectedItem();
                        String [] t = temp.split(" ");
                        idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(),t[1] , t[0]);
                        pacjent.setIdLekarza(new SimpleIntegerProperty(idLekarza));
                        pacjent.setLekarz(new SimpleStringProperty(temp));

                        temp = oddzial.getSelectionModel().getSelectedItem();
                        idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);
                        pacjent.setIdOddzialu(new SimpleIntegerProperty(idOddzialu));
                        pacjent.setOddzial(new SimpleStringProperty(oddzial.getSelectionModel().getSelectedItem()));

                        pacjent.setGrKrwii(new SimpleStringProperty(grKrwii.getSelectionModel().getSelectedItem()));

                        PacjentUtil.updatePacjent(Laczenie.getStatement(), pacjent);
                        PacjentUtil.clearPacjentList();
                        rejestracjaController.setPacjentList(PacjentUtil.getPacjentList());
                        dialoStage.close();
                    }
                    else
                    {
                        alert.close();
                    }
                }
            }
            else
            {
                if(Utils.walidacjaPola(imieField, "Imie") && Utils.walidacjaPola(nazwiskoField, "Nazwisko") && Utils.walidacjaPolaNumerycznego(peselField, "Pesel") && Utils.walidacjaPola(grKrwii, "Gr Krwi") && Utils.walidacjaPola(lekarz, "Lekarz") && Utils.walidacjaPola(oddzial, "Oddzial"))
                {    
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potwierdzenie operacji");
                    alert.setHeaderText("Dodanie nowego pacjenta");
                    alert.setContentText("Dodać pacjenta o następujących danych:\n\n"
                            + "Imie:\t\t\t\t"+imieField.getText()+"\n"
                            + "Nazwisko:\t\t"+nazwiskoField.getText()+"\n"
                            + "Pesel:\t\t\t"+peselField.getText()+"\n"
                            + "Gr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                            + "Lekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                            + "Oddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem());

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        String temp = lekarz.getSelectionModel().getSelectedItem();
                        String [] t = temp.split(" ");
                        idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(),t[1] , t[0]);

                        temp = oddzial.getSelectionModel().getSelectedItem();
                        idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);  
                        
                        pacjent = new Pacjent(null, imieField.getText(), nazwiskoField.getText(), peselField.getText(), idLekarza, lekarz.getSelectionModel().getSelectedItem(), idOddzialu, oddzial.getSelectionModel().getSelectedItem(), grKrwii.getSelectionModel().getSelectedItem());
                        
                        PacjentUtil.addPacjent(Laczenie.getStatement(), pacjent);
                        PacjentUtil.clearPacjentList();
                        rejestracjaController.setPacjentList(PacjentUtil.getPacjentList());
                        dialoStage.close();
                    }
                    else
                    {
                        alert.close();
                    }
                }
            } 
        }  
        catch (SQLException | ClassNotFoundException ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

    public void setPacjent(Pacjent pacjent) 
    {       
        if(pacjent != null)
        {    
            this.pacjent = pacjent;
            imieField.setText(pacjent.getImie().getValue());
            nazwiskoField.setText(pacjent.getNazwisko().getValue());
            peselField.setText(pacjent.getPesel().getValue());
        }
        else
        {
           
           imieField.setText("");
           nazwiskoField.setText("");
           peselField.setText("");
        }
    }

    public void setRejestracjaController(RejestracjaController rejestracjaController) 
    {
        this.rejestracjaController = rejestracjaController;
    }

    public void setLekarzList(ObservableList<Lekarz> lekarzList) 
    {       
        this.lekarzList = FXCollections.observableArrayList();
        for(Lekarz l : lekarzList)
            this.lekarzList.add(l.getNazwisko().getValue()+" "+l.getImie().getValue());
        lekarz.setItems(this.lekarzList);
        if(pacjent != null)
            lekarz.getSelectionModel().select(pacjent.getLekarz().getValue());
    }

    public void setOddzialyList(ObservableList<Oddzial> oddzialyList) 
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            this.oddzialyList.add(o.getNazwaOddzialu().getValue());
        oddzial.setItems(this.oddzialyList);
        if(pacjent != null)
            oddzial.getSelectionModel().select(pacjent.getOddzial().getValue());
    }
    
    public void setGrKrwii()
    {
        grKrwiiList = FXCollections.observableArrayList();
        grKrwiiList.addAll("A rh+", "A rh-", "B rh+", "B rh-", "AB rh+", "AB rh-", "0 rh+", "0 rh-");
        grKrwii.setItems(grKrwiiList);
        if(pacjent != null)
            grKrwii.getSelectionModel().select(pacjent.getGrKrwii().getValue());
    }
}