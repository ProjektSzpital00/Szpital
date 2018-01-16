package szpital.view;

import java.sql.SQLException;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import szpital.model.Lekarz;
import szpital.model.Lozko;
import szpital.model.Oddzial;
import szpital.model.Pacjent;
import szpital.model.SalaPacjent;
import szpital.util.*;

public class AddPacjentController 
{
    private Stage dialoStage;
    private Pacjent pacjent;
    private RejestracjaController rejestracjaController;
    private ObservableList <SalaPacjent> salaAllList;
    private ObservableList <Lozko> lozkoAllList;
    private ObservableList <String> lekarzList;
    private ObservableList <String> oddzialyList;
    private ObservableList <String> grKrwiiList;
    private ObservableList <String> salaPacjentList;
    private ObservableList <Integer> lozkaList;
    
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
    private ChoiceBox <Integer> nrLozka;

    @FXML
    private TextField mailField;

    
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
        Integer idSali;
         
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
                                + "Sala:\t\t\t\t"+sala.getSelectionModel().getSelectedItem()+"\n"
                                + "Nr łóżka:\t\t\t"+nrLozka.getSelectionModel().getSelectedItem()+"\n"
                                + "Mail:\t\t\t"+mailField.getText()+"\n"
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

                        temp = sala.getSelectionModel().getSelectedItem();
                        idSali = SalaPacjentUtil.searchSalaPacjentId(Laczenie.getStatement(), temp);
                        pacjent.setNrSali(new SimpleIntegerProperty(idSali));
                        
                        pacjent.setNrLozka(new SimpleIntegerProperty(nrLozka.getSelectionModel().getSelectedItem()));

                        pacjent.setMail(new SimpleStringProperty(mailField.getText()));

                        PacjentUtil.updatePacjent(Laczenie.getStatement(), pacjent);
                        if(idSali.equals(0))
                            LozkoUtil.zmiannaStatusuLozka(0, idSali);
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
                            + "Oddzial:\t\t\t"+oddzial.getSelectionModel().getSelectedItem()+"\n"
                            + "Sala:\t\t\t\t"+sala.getSelectionModel().getSelectedItem()+"\n"
                            + "Nr łóżka:\t\t\t"+nrLozka.getSelectionModel().getSelectedItem()+"\n"
                            + "Mail:\t\t\t"+mailField.getText()+"\n")
                    ;

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
                        
                        temp = sala.getSelectionModel().getSelectedItem();
                        idSali = SalaPacjentUtil.searchSalaPacjentId(Laczenie.getStatement(), temp);
                        
                        pacjent = new Pacjent(null, imieField.getText(), nazwiskoField.getText(), peselField.getText(), 
                                idLekarza, lekarz.getSelectionModel().getSelectedItem(), idOddzialu, 
                                oddzial.getSelectionModel().getSelectedItem(), grKrwii.getSelectionModel().getSelectedItem(),
                                idSali, nrLozka.getSelectionModel().getSelectedItem(), mailField.getText());
                        
                        PacjentUtil.addPacjent(Laczenie.getStatement(), pacjent);
                        if(!idSali.equals(0))
                            LozkoUtil.zmiannaStatusuLozka(0, idSali);
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
    
    public void init(ObservableList<Lozko> lozkoList, ObservableList<SalaPacjent> salaList)
    {
        this.lozkoAllList = lozkoList;
        this.salaAllList = salaList;
    }
    
    public void init2()
    {
        oddzial.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                salaPacjentList.clear();
                try
                {
                    setSalaPacjentList();
                }
                catch (SQLException | ClassNotFoundException exc) 
                {
                    Utils.alertWyswietl(exc);
                }
                lozkaList.clear();
            }
        });
        
        sala.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                lozkaList.clear();
                try
                {
                    setLozkaList();
                }
                catch (SQLException | ClassNotFoundException exc) 
                {
                    Utils.alertWyswietl(exc);
                }
            }
        });
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
            mailField.setText(pacjent.getMail().getValue());
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
    
    public void setSalaPacjentList() throws SQLException, ClassNotFoundException
    {
        this.salaPacjentList = FXCollections.observableArrayList();
        String temp = oddzial.getSelectionModel().getSelectedItem();
        Integer idOddzialu = OddzialUtil.searchOddzialId(Laczenie.getStatement(), temp);
        for(SalaPacjent sP : salaAllList)
        {
            if(sP.getIdOddzialu().getValue().equals(0))
            {
                this.salaPacjentList.add(sP.getNazwa().getValue());
            }
            else if(sP.getIdOddzialu().getValue().equals(idOddzialu))
            {
                this.salaPacjentList.add(sP.getNazwa().getValue());
            }
        }
        sala.setItems(this.salaPacjentList);
        if(pacjent != null)
        {
            for(SalaPacjent sP : salaAllList)
            {
                if(sP.getIdSali().getValue().equals(pacjent.getNrSali().getValue()))
                {
                    sala.getSelectionModel().select(sP.getNazwa().getValue());
                    break;
                }
            }
        }
        else
            sala.getSelectionModel().select(this.salaPacjentList.get(0));
    }
    
    public void setLozkaList() throws SQLException, ClassNotFoundException
    {
        this.lozkaList = FXCollections.observableArrayList();
        String temp = sala.getSelectionModel().getSelectedItem();
        Integer idSali = SalaPacjentUtil.searchSalaPacjentId(Laczenie.getStatement(), temp);
        
        for(Lozko l : lozkoAllList)
        {
            if(l.getIdSali().getValue().equals(idSali))
            {
                if(pacjent != null)
                {
                    if(l.getCzyWolne().getValue().equals(1) || l.getIdLozka().getValue().equals(pacjent.getNrLozka().getValue()))
                    {
                        this.lozkaList.add(l.getIdLozka().getValue());
                    }
                }
                else
                {
                    if(l.getCzyWolne().getValue().equals(1))
                    {
                        this.lozkaList.add(l.getIdLozka().getValue());
                    }
                }
                
            }
        }
        if(!lozkaList.isEmpty())
        {
            nrLozka.setItems(lozkaList);
            if(pacjent != null)
            {
                nrLozka.getSelectionModel().select(pacjent.getNrLozka().getValue());
            }
            else
                nrLozka.getSelectionModel().select(this.lozkaList.get(0));
        }
        else
        {
            lozkaList.add(0);
            nrLozka.setItems(lozkaList);
        }
    }
    
    /*
    
    public void setSala() throws SQLException, ClassNotFoundException 
    {
        MiejscaUtil miejscaUtil = new MiejscaUtil();
        sala.getItems().clear();
        salaList = FXCollections.observableArrayList();
        salaList.clear();
        salaList.addAll(miejscaUtil.getSaleList( miejscaUtil.IdOddzialu(oddzial.getValue())));
        sala.setItems(salaList);
        setNrMiejsce();
    }

    public void setNrMiejsce() throws SQLException, ClassNotFoundException 
    {
        MiejscaUtil miejscaUtil = new MiejscaUtil();
        nrLozka.getItems().clear();
        nrLozkaList = FXCollections.observableArrayList();
        nrLozkaList.clear();
        nrLozkaList.addAll(miejscaUtil.getLozkaList(sala.getValue()));
        nrLozka.setItems(nrLozkaList);
    }

    */
}