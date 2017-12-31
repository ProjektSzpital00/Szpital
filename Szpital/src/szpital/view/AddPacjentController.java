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
import szpital.model.Miejsca;
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.util.*;

public class AddPacjentController 
{
    private Stage dialoStage;
    private Pacjent pacjent;
    private RejestracjaController rejestracjaController;
    private ObservableList <String> lekarzList;
    private ObservableList <String> oddzialyList;
    private ObservableList <String> grKrwiiList;
    private ObservableList <String> salaList;
    private ObservableList <String> nrLozkaList;
    
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
    private ChoiceBox <String> sala;
    
    @FXML
    private ChoiceBox <String> nrLozka;

    
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
                                + "ID:\t\t\t\t"+pacjent.getIdPacjenta().getValue()+"\n"
                                + "Imie:\t\t\t\t"+imieField.getText()+"\n"
                                + "Nazwisko:\t\t"+nazwiskoField.getText()+"\n"
                                + "Pesel:\t\t\t"+peselField.getText()+"\n"
                                + "Gr Krwi:\t\t\t"+grKrwii.getSelectionModel().getSelectedItem()+"\n"
                                + "Lekarz:\t\t\t"+lekarz.getSelectionModel().getSelectedItem()+"\n"
                                + "Oddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem()+"\n"
                                + "nr sali:\t\t\t"+sala.getSelectionModel().getSelectedItem()+"\n"
                                + "nr lóżka:\t\t\t"+nrLozka.getSelectionModel().getSelectedItem()+"\n"
                    );

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        pacjent.setImie(new SimpleStringProperty(imieField.getText()));
                        pacjent.setNazwisko(new SimpleStringProperty(nazwiskoField.getText()));
                        pacjent.setPesel(new SimpleStringProperty(peselField.getText()));

                        String temp = lekarz.getSelectionModel().getSelectedItem();
                        String [] t = temp.split(" ");
                        if(t.length < 2)
                            idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(), " ", t[0]);
                        else
                            idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(), t[1], t[0]);
                        pacjent.setIdLekarza(new SimpleIntegerProperty(idLekarza));
                        pacjent.setLekarz(new SimpleStringProperty(temp));

                        temp = oddzial.getSelectionModel().getSelectedItem();
                        idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);
                        pacjent.setIdOddzialu(new SimpleIntegerProperty(idOddzialu));
                        pacjent.setOddzial(new SimpleStringProperty(oddzial.getSelectionModel().getSelectedItem()));

                        pacjent.setGrKrwii(new SimpleStringProperty(grKrwii.getSelectionModel().getSelectedItem()));

                        pacjent.setNr_sali(new SimpleIntegerProperty(Integer.valueOf(sala.getValue())));
                        pacjent.setNr_lozka(new SimpleIntegerProperty(Integer.valueOf(nrLozka.getValue())));

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
                        if(t.length < 2)
                            idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(), " ", t[0]);
                        else
                            idLekarza = LekarzUtil.searchLekarzId(Laczenie.getStatement(), t[1], t[0]);

                        temp = oddzial.getSelectionModel().getSelectedItem();
                        idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);  
                        
                        pacjent = new Pacjent(null, imieField.getText(), nazwiskoField.getText(), peselField.getText(), 
                                idLekarza, lekarz.getSelectionModel().getSelectedItem(), idOddzialu, 
                                oddzial.getSelectionModel().getSelectedItem(), grKrwii.getSelectionModel().getSelectedItem(),
                                Integer.valueOf(sala.getSelectionModel().getSelectedItem()),Integer.valueOf(nrLozka.getSelectionModel().getSelectedItem()));
                        
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
        else
            lekarz.getSelectionModel().select(this.lekarzList.get(0));
    }

    public void setOddzialyList(ObservableList<Oddzial> oddzialyList) 
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            this.oddzialyList.add(o.getNazwaOddzialu().getValue());
        oddzial.setItems(this.oddzialyList);
        if(pacjent != null)
            oddzial.getSelectionModel().select(pacjent.getOddzial().getValue());
        else
            oddzial.getSelectionModel().select(this.oddzialyList.get(0));
    }
    
    public void setGrKrwii()
    {
        grKrwiiList = FXCollections.observableArrayList();
        grKrwiiList.addAll("nieznana", "A rh+", "A rh-", "B rh+", "B rh-", "AB rh+", "AB rh-", "0 rh+", "0 rh-");
        grKrwii.setItems(grKrwiiList);
        if(pacjent != null)
            grKrwii.getSelectionModel().select(pacjent.getGrKrwii().getValue());
        else
            grKrwii.getSelectionModel().select(grKrwiiList.get(0));
    }
    
    public void setSala() throws SQLException, ClassNotFoundException {
        MiejscaUtil miejscaUtil = new MiejscaUtil();
        sala.getItems().clear();
        salaList = FXCollections.observableArrayList();
        salaList.clear();
        salaList.addAll(miejscaUtil.getSaleList(1));
        sala.setItems(salaList);
    }

    public void setNrMiejsce() throws SQLException, ClassNotFoundException {
        MiejscaUtil miejscaUtil = new MiejscaUtil();
        nrLozka.getItems().clear();
        nrLozkaList = FXCollections.observableArrayList();
        nrLozkaList.clear();
        nrLozkaList.addAll(miejscaUtil.getLozkaList(sala.getValue()));
        nrLozka.setItems(nrLozkaList);
    }
}