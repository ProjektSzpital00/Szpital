package szpital.view;

import java.sql.SQLException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import szpital.model.Lekarz;
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.util.ListaPacjentow;
import szpital.util.MyAlert;

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
        Integer idLekarza = null;
        Integer idOddzialu = null;
        if(pacjent != null)
        {
            pacjent.setIdPacjenta(new SimpleIntegerProperty(new Integer(idPacjentaField.getText())));
            pacjent.setImie(new SimpleStringProperty(imieField.getText()));
            pacjent.setNazwisko(new SimpleStringProperty(nazwiskoField.getText()));
            pacjent.setPesel(new SimpleStringProperty(peselField.getText()));
            
           
            // idLekarza = pacjent.setIdLekarza(idLekarza); <- wczytać z bazy
            
            pacjent.setLekarz(new SimpleStringProperty(lekarz.getSelectionModel().getSelectedItem()));
            
            // idOddzialu = pacjent.setIdOddzialu(idOddzialu); <- wczytać z bazy
            
            pacjent.setOddzial(new SimpleStringProperty(oddzial.getSelectionModel().getSelectedItem()));
            pacjent.setGrKrwii(new SimpleStringProperty(grKrwii.getSelectionModel().getSelectedItem()));
        }
        else
        {
            pacjent = new Pacjent(new Integer(idPacjentaField.getText()), imieField.getText(), nazwiskoField.getText(), peselField.getText(), idLekarza, lekarz.getSelectionModel().getSelectedItem(), idOddzialu, oddzial.getSelectionModel().getSelectedItem(), grKrwii.getSelectionModel().getSelectedItem());
        }
        
        // zrób insert do bazy
        
        try 
        {
            rejestracjaController.setPacjentList(ListaPacjentow.get());
        }
        catch (SQLException | ClassNotFoundException exc) 
        {
            MyAlert.alertWyswietl(exc);
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
            idPacjentaField.setText(pacjent.getIdPacjenta().getValue().toString());
            imieField.setText(pacjent.getImie().getValue());
            nazwiskoField.setText(pacjent.getNazwisko().getValue());
            peselField.setText(pacjent.getPesel().getValue());
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
        lekarz.getSelectionModel().select(pacjent.getLekarz().getValue());
    }

    public void setOddzialyList(ObservableList<Oddzial> oddzialyList) 
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            this.oddzialyList.add(o.getNazwaOddzialu().getValue());
        oddzial.setItems(this.oddzialyList);
        oddzial.getSelectionModel().select(pacjent.getOddzial().getValue());
    }
    
    public void setGrKrwii()
    {
        grKrwiiList = FXCollections.observableArrayList();
        grKrwiiList.addAll("A rh+", "A rh-", "B rh+", "B rh-", "AB rh+", "AB rh-", "0 rh+", "0 rh-");
        grKrwii.setItems(grKrwiiList);
        grKrwii.getSelectionModel().select(pacjent.getGrKrwii().getValue());
    }
}