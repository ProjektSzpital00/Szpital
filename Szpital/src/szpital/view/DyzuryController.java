package szpital.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import szpital.model.Dyzur;
import szpital.model.Lekarz;
import szpital.model.Oddzial;
import szpital.model.Pielegniarka;
import szpital.util.DyzurUtil;
import szpital.util.LekarzUtil;
import szpital.util.PielegniarkaUtil;
import szpital.util.Utils;

public class DyzuryController 
{
    private Stage dialogStage;
    private OrdynatorController ordynatorController;
    private ObservableList <String> oddzialyList;
    private ObservableList <Dyzur> dyzurList;
    private ObservableList <String> dyzurTempList;
    private ObservableList <String> dyzurTempList2;
    private ObservableList <String> dyzurTempList3;
    private ObservableList <String> dyzurTempList4;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ChoiceBox <String> choiceBoxOddzial;
    
    @FXML
    private CheckBox checkBoxPierwszyDyzur;
    
    @FXML
    private CheckBox checkBoxDrugiDyzur;
    
    @FXML
    private ComboBox <String> comboBoxLekarzPierwszyDyzur;
    
    @FXML
    private ComboBox <String> comboBoxPielegniarka1PierwszyDyzur;
    
    @FXML
    private ComboBox <String> comboBoxPielegniarka2PierwszyDyzur;
     
    @FXML
    private ComboBox <String> comboBoxLekarzDrugiDyzur;
    
    @FXML
    private ComboBox <String> comboBoxPielegniarka1DrugiDyzur;
    
    @FXML
    private ComboBox <String> comboBoxPielegniarka2DrugiDyzur;
    
    @FXML
    private void initialize()
    {
        dyzurList = FXCollections.observableArrayList();
        dyzurTempList = FXCollections.observableArrayList();
        dyzurTempList2 = FXCollections.observableArrayList();
        dyzurTempList3 = FXCollections.observableArrayList();
        dyzurTempList4 = FXCollections.observableArrayList();
    }
    
    @FXML
    public void dodajDyzur()
    {
        String lekarz1 = comboBoxLekarzPierwszyDyzur.getSelectionModel().getSelectedItem();
        String lekarz2 = comboBoxLekarzDrugiDyzur.getSelectionModel().getSelectedItem();
        String pielegniarka11 = comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().getSelectedItem();
        String pielegniarka12 = comboBoxPielegniarka2PierwszyDyzur.getSelectionModel().getSelectedItem();
        String pielegniarka21 = comboBoxPielegniarka1DrugiDyzur.getSelectionModel().getSelectedItem();
        String pielegniarka22 = comboBoxPielegniarka2DrugiDyzur.getSelectionModel().getSelectedItem();
        
        if(checkBoxPierwszyDyzur.isSelected() && checkBoxDrugiDyzur.isSelected())
        {
            if(!lekarz1.equals(lekarz2))
            {
                if(!pielegniarka11.equals(pielegniarka12) && !pielegniarka11.equals(pielegniarka21) && !pielegniarka11.equals(pielegniarka22))
                {
                    if(!pielegniarka12.equals(pielegniarka21) && !pielegniarka12.equals(pielegniarka22) && !pielegniarka21.equals(pielegniarka22))
                    {
                        if(!sprawdzCzyMaJuzDzisDyzur(lekarz1, true) && !sprawdzCzyMaJuzDzisDyzur(lekarz2, true) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka11, false) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka12, false) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka21, false) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka22, false))
                        {
                            String [] temp = lekarz1.split(" ");
                            Dyzur d1 = new Dyzur(LekarzUtil.searchLekarzId(temp[1], temp[0]), true, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                            DyzurUtil.addLekarz(d1, true);
                            temp = lekarz2.split(" ");
                            Dyzur d2 = new Dyzur(LekarzUtil.searchLekarzId(temp[1], temp[0]), true, datePicker.getValue().toString()+" 16:00:00", datePicker.getValue().plusDays(1).toString()+" 00:00:00");
                            DyzurUtil.addLekarz(d2, true);

                            temp = pielegniarka11.split(" ");
                            Dyzur d3 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                            DyzurUtil.addLekarz(d3, false);
                            temp = pielegniarka12.split(" ");
                            Dyzur d4 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                            DyzurUtil.addLekarz(d4, false);
                            temp = pielegniarka21.split(" ");
                            Dyzur d5 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 16:00:00", datePicker.getValue().plusDays(1).toString()+" 00:00:00");
                            DyzurUtil.addLekarz(d5, false);
                            temp = pielegniarka22.split(" ");
                            Dyzur d6 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 16:00:00", datePicker.getValue().plusDays(1).toString()+" 00:00:00");
                            DyzurUtil.addLekarz(d6, false);
                            Utils.informacjaWyswietl("Pomyślnie dodano dyżury");
                            DyzurUtil.clearDyzurList2();
                            ordynatorController.setDyzuryTabelka();
                            dialogStage.close();
                        }
                        else
                        {
                            Utils.alertWyswietl("Błąd wyboru!", "Wybrano personel który ma już dyżur w tym dniu\nProszę zmienić wybór personelu");
                        }
                    }
                    else
                    {
                        Utils.alertWyswietl("Błąd wyboru!", "Wybrano te same pielegniarki dla dwóch dyżurów."
                                + "Proszę zmienić wybór pielęgniarek");
                    }
                }
                else
                {
                    Utils.alertWyswietl("Błąd wyboru!", "Wybrano te same pielegniarki dla dwóch dyżurów."
                                + "Proszę zmienić wybór pielęgniarek");
                }
            }
            else
            {
                Utils.alertWyswietl("Błąd wyboru!", "Wybrano tych samych lekarzy dla dwóch dyżurów."
                        + "Proszę zmienić wybór lekarzy");
            }
        }
        else if(checkBoxPierwszyDyzur.isSelected())
        {
            if(!pielegniarka11.equals(pielegniarka12))
            {
                if(!sprawdzCzyMaJuzDzisDyzur(lekarz1, true) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka11, false) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka12, false))
                {
                    String [] temp = lekarz1.split(" ");
                    Dyzur d1 = new Dyzur(LekarzUtil.searchLekarzId(temp[1], temp[0]), true, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                    DyzurUtil.addLekarz(d1, true);

                    temp = pielegniarka11.split(" ");
                    Dyzur d2 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                    DyzurUtil.addLekarz(d2, false);
                    temp = pielegniarka12.split(" ");
                    Dyzur d3 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 00:00:00", datePicker.getValue().toString()+" 08:00:00");
                    DyzurUtil.addLekarz(d3, false);
                    Utils.informacjaWyswietl("Pomyślnie dodano dyżur");
                    DyzurUtil.clearDyzurList2();
                    ordynatorController.setDyzuryTabelka();
                    dialogStage.close();
                }
                else
                {
                    Utils.alertWyswietl("Błąd wyboru!", "Wybrano personel który ma już dyżur w tym dniu\nProszę zmienić wybór personelu");
                }
            }
            else
            {
                Utils.alertWyswietl("Błąd wyboru!", "Wybrano te same pielęgniarki dla dwóch dyżurów."
                        + " Proszę zmienić wybór pielęgniarek");
            }
        }
        else if(checkBoxDrugiDyzur.isSelected())
        {
            if(!pielegniarka21.equals(pielegniarka22))
            {
                if(!sprawdzCzyMaJuzDzisDyzur(lekarz2, true) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka21, false) && !sprawdzCzyMaJuzDzisDyzur(pielegniarka22, false))
                {
                    String [] temp = lekarz2.split(" ");
                    Dyzur d1 = new Dyzur(LekarzUtil.searchLekarzId(temp[1], temp[0]), true, datePicker.getValue().toString() + " 16:00:00", datePicker.getValue().plusDays(1).toString() + " 00:00:00");
                    DyzurUtil.addLekarz(d1, true);

                    temp = pielegniarka21.split(" ");
                    Dyzur d2 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 16:00:00", datePicker.getValue().plusDays(1).toString()+" 00:00:00");
                    DyzurUtil.addLekarz(d2, false);
                    temp = pielegniarka22.split(" ");
                    Dyzur d3 = new Dyzur(PielegniarkaUtil.searchPielegniarkaId(temp[1], temp[0]), false, datePicker.getValue().toString()+" 16:00:00", datePicker.getValue().plusDays(1).toString()+" 00:00:00");
                    DyzurUtil.addLekarz(d3, false);
                    Utils.informacjaWyswietl("Pomyślnie dodano dyżur");
                    DyzurUtil.clearDyzurList2();
                    ordynatorController.setDyzuryTabelka();
                    dialogStage.close();
                }
                else
                {
                    Utils.alertWyswietl("Błąd wyboru!", "Wybrano personel który ma już dyżur w tym dniu\nProszę zmienić wybór personelu");
                }
            }
            else
            {
                Utils.alertWyswietl("Błąd wyboru!", "Wybrano te same pielęgniarki dla dwóch dyżurów."
                        + " Proszę zmienić wybór pielęgniarek");
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano dyżuru!", "Proszę wybrać który dyżur/y chcesz dodać");
        }
        DyzurUtil.clearDyzurList2();
        ordynatorController.setDyzuryTabelka();
    }
    
    @FXML
    public void usunDyzur()
    {
        if(checkBoxPierwszyDyzur.isSelected() && checkBoxDrugiDyzur.isSelected())
        {
            try 
            {
                for(Dyzur d : DyzurUtil.getDyzurList())
                {
                    if(d.getLekarzDyzurujacy() != null)
                    {
                        if(d.getLekarzDyzurujacy().getValue().equals(comboBoxLekarzPierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), true);
                        }
                        else if(d.getLekarzDyzurujacy().getValue().equals(comboBoxLekarzDrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), true);
                        } 
                    }
                    else if(d.getPielegniarkaDyzurujaca() != null)
                    {
                        if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                        else if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka2PierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                        else if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka1DrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                        else if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka2DrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                    }
                }
                Utils.informacjaWyswietl("Pomyślnie usunięto dyżury");
                DyzurUtil.clearDyzurList2();
                ordynatorController.setDyzuryTabelka();
                dialogStage.close();
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                Utils.alertWyswietl("Błąd przy usuwaniu dyzuru", "Błąd związany z pozyskaniem listy dyżurów");
            }
        }
        else if(checkBoxPierwszyDyzur.isSelected())
        {
            try 
            {
                for(Dyzur d : DyzurUtil.getDyzurList())
                {
                    if(d.getLekarzDyzurujacy() != null)
                    {
                        if(d.getLekarzDyzurujacy().getValue().equals(comboBoxLekarzPierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), true);
                        }
                    }
                    else if(d.getPielegniarkaDyzurujaca() != null)
                    {
                        if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                        else if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka2PierwszyDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                    }
                }
                Utils.informacjaWyswietl("Pomyślnie usunięto dyżury");
                DyzurUtil.clearDyzurList2();
                ordynatorController.setDyzuryTabelka();
                dialogStage.close();
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                Utils.alertWyswietl("Błąd przy usuwaniu dyzuru", "Błąd związany z pozyskaniem listy dyżurów");
            }
        }
        else if(checkBoxDrugiDyzur.isSelected())
        {
            try 
            {
                for(Dyzur d : DyzurUtil.getDyzurList())
                {
                    if(d.getLekarzDyzurujacy() != null)
                    {
                        if(d.getLekarzDyzurujacy().getValue().equals(comboBoxLekarzDrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), true);
                        }
                    }
                    else if(d.getPielegniarkaDyzurujaca() != null)
                    {
                        if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka1DrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                        else if(d.getPielegniarkaDyzurujaca().getValue().equals(comboBoxPielegniarka2DrugiDyzur.getSelectionModel().getSelectedItem()))
                        {
                            if(d.getTerminDataOd().getValue().equals(datePicker.getValue().toString()))
                                DyzurUtil.deleteDyzurPersonelu(d.getIdDyzuru(), false);
                        }
                    }
                }
                Utils.informacjaWyswietl("Pomyślnie usunięto dyżury");
                DyzurUtil.clearDyzurList2();
                ordynatorController.setDyzuryTabelka();
                dialogStage.close();
            } 
            catch (SQLException | ClassNotFoundException ex) 
            {
                Utils.alertWyswietl("Błąd przy usuwaniu dyzuru", "Błąd związany z pozyskaniem listy dyżurów");
            }
        }
        else
        {
            Utils.alertWyswietl("Nie wybrano dyżuru!", "Proszę wybrać który dyżur/y chcesz usunąć");
        }
    }
    
    public void init()
    {
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>()
        {
            @Override
            public void changed(ObservableValue observable, LocalDate oldValue, LocalDate newValue)
            {
                DyzurUtil.clearDyzurList();
                dyzurList.clear();
                dyzurTempList.clear();
                dyzurTempList2.clear();
                dyzurTempList3.clear();
                dyzurTempList4.clear();
                try
                {
                    setDyzurList();
                    checkBoxPierwszyDyzur.setSelected(false);
                    checkBoxDrugiDyzur.setSelected(false);
                }
                catch (SQLException | ClassNotFoundException exc) 
                {
                    Utils.alertWyswietl(exc);
                }
            }
        });
        
        datePicker.setDayCellFactory(picker -> new DateCell() 
        {
            @Override
            public void updateItem(LocalDate date, boolean empty) 
            {
                super.updateItem(date, empty);
                if(date.isBefore(LocalDate.now()))
                {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
        datePicker.setEditable(false);
        
        choiceBoxOddzial.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                dyzurList.clear();
                dyzurTempList.clear();
                dyzurTempList2.clear();
                dyzurTempList3.clear();
                dyzurTempList4.clear();
                DyzurUtil.clearDyzurList();
                try
                {
                    setDyzurList();
                    checkBoxPierwszyDyzur.setSelected(false);
                    checkBoxDrugiDyzur.setSelected(false);
                }
                catch (SQLException | ClassNotFoundException exc) 
                {
                    Utils.alertWyswietl(exc);
                }
            }
        });
    }
    
    private boolean sprawdzCzyMaJuzDzisDyzur(String dyzurujacy, Boolean czyLekarz)
    {
        String currentDate = datePicker.getValue().toString();
        for(Dyzur d : dyzurList)
        {
            if(d.getTerminDataDo().getValue().equals(currentDate))
            {
                if(czyLekarz && (d.getLekarzDyzurujacy() != null))
                {
                    if(d.getLekarzDyzurujacy().getValue().equals(dyzurujacy))
                    {
                        return true;
                    }
                }
                else if(d.getPielegniarkaDyzurujaca() != null)
                {
                    if(d.getPielegniarkaDyzurujaca().getValue().equals(dyzurujacy))
                    {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public void setStage(Stage dialoStage) 
    {
        this.dialogStage = dialoStage;
    }
    
    public void setOrdynatorController(OrdynatorController ordynatorController) 
    { 
        this.ordynatorController = ordynatorController; 
    }
    
    public void setDate()
    {
        datePicker.setValue(LocalDate.now());
    }
    
    public void setDyzurList() throws SQLException, ClassNotFoundException
    {
        String lekarz1 = "", lekarz2 = "";
        ArrayList <String> pielegniarki1 = new ArrayList <String>();
        ArrayList <String> pielegniarki2 = new ArrayList <String>();
        String wybranyOddzial = choiceBoxOddzial.getSelectionModel().getSelectedItem();
        String dzisiejszaData = datePicker.getValue().toString();
        
        for(Lekarz l : LekarzUtil.getLekarzList())
        {
            if(l.getOddzial().getValue().equals(wybranyOddzial))
            {
                dyzurTempList.add(l.getNazwisko().getValue() + " " + l.getImie().getValue());
                dyzurTempList2.add(l.getNazwisko().getValue() + " " + l.getImie().getValue());
            }
        }
        
        for(Pielegniarka p : PielegniarkaUtil.getPielegniarkaList())
        {
            if(p.getOddzial().getValue().equals(wybranyOddzial))
            {
                dyzurTempList3.add(p.getNazwisko().getValue() + " " + p.getImie().getValue());
                dyzurTempList4.add(p.getNazwisko().getValue() + " " + p.getImie().getValue());
            }
        }
        
        dyzurList = DyzurUtil.getDyzurList();
        
        for(Dyzur d : dyzurList)
        {
            if(d.getIdLekarza() != null && d.getOddzial().getValue().equals(wybranyOddzial))
            {
                if(d.getTerminDataOd().getValue().equals(datePicker.getValue().minusDays(1).toString()))
                {
                    dyzurTempList.remove(d.getLekarzDyzurujacy().getValue());
                    dyzurTempList2.remove(d.getLekarzDyzurujacy().getValue());
                }
                
                if((d.getTerminDataOd().getValue().equals(dzisiejszaData)) && (d.getTerminOd().getValue().charAt(11) == '0'))
                {
                    lekarz1 = d.getLekarzDyzurujacy().getValue();
                }
                else if((d.getTerminDataOd().getValue().equals(dzisiejszaData)) && (d.getTerminOd().getValue().charAt(11) == '1'))
                {
                    lekarz2 = d.getLekarzDyzurujacy().getValue();
                }
            } 
            else if(d.getIdPielęgniarki()!= null && d.getOddzial().getValue().equals(wybranyOddzial))
            {
                if(d.getTerminDataOd().getValue().equals(datePicker.getValue().minusDays(1).toString()))
                {
                    dyzurTempList3.remove(d.getPielegniarkaDyzurujaca().getValue());
                    dyzurTempList4.remove(d.getPielegniarkaDyzurujaca().getValue());
                }
                
                if((d.getTerminDataOd().getValue().equals(dzisiejszaData)) && (d.getTerminOd().getValue().charAt(11) == '0'))
                {
                    pielegniarki1.add(d.getPielegniarkaDyzurujaca().getValue());
                }
                else if((d.getTerminDataOd().getValue().equals(dzisiejszaData)) && (d.getTerminOd().getValue().charAt(11) == '1'))
                {
                    pielegniarki2.add(d.getPielegniarkaDyzurujaca().getValue());
                }
            }
        }
        
        comboBoxLekarzPierwszyDyzur.setItems(dyzurTempList);
        comboBoxLekarzDrugiDyzur.setItems(dyzurTempList2);
        comboBoxPielegniarka1PierwszyDyzur.setItems(dyzurTempList3);
        comboBoxPielegniarka1DrugiDyzur.setItems(dyzurTempList3);
        comboBoxPielegniarka2PierwszyDyzur.setItems(dyzurTempList4);
        comboBoxPielegniarka2DrugiDyzur.setItems(dyzurTempList4);
        
        if(!lekarz1.isEmpty() && !lekarz2.isEmpty())
        {
            comboBoxLekarzPierwszyDyzur.getSelectionModel().select(lekarz1);
            comboBoxLekarzDrugiDyzur.getSelectionModel().select(lekarz2);
        }
        else if(lekarz1.isEmpty() && !lekarz2.isEmpty())
        {
            comboBoxLekarzDrugiDyzur.getSelectionModel().select(lekarz2);
        }    
        else if(lekarz2.isEmpty() && !lekarz1.isEmpty())
        {    
            comboBoxLekarzPierwszyDyzur.getSelectionModel().select(lekarz1);
        }
        
        if(!pielegniarki1.isEmpty() && !pielegniarki2.isEmpty())
        {
            comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().select(pielegniarki1.get(0));
            comboBoxPielegniarka2PierwszyDyzur.getSelectionModel().select(pielegniarki1.get(1));
            comboBoxPielegniarka1DrugiDyzur.getSelectionModel().select(pielegniarki2.get(0));
            comboBoxPielegniarka2DrugiDyzur.getSelectionModel().select(pielegniarki2.get(1));
        }
        else if(pielegniarki1.isEmpty() && !pielegniarki2.isEmpty())
        {
            if(pielegniarki2.size() == 2)
            {
                comboBoxPielegniarka1DrugiDyzur.getSelectionModel().select(pielegniarki2.get(0));
                comboBoxPielegniarka2DrugiDyzur.getSelectionModel().select(pielegniarki2.get(1));
            }
            else
            {
                comboBoxPielegniarka1DrugiDyzur.getSelectionModel().select(pielegniarki2.get(0));
            }
        }
        else if(!pielegniarki1.isEmpty() && pielegniarki2.isEmpty())
        {
            if(pielegniarki1.size() == 2)
            {
                comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().select(pielegniarki1.get(0));
                comboBoxPielegniarka2PierwszyDyzur.getSelectionModel().select(pielegniarki1.get(1));
            }
            else
            {
                comboBoxPielegniarka1PierwszyDyzur.getSelectionModel().select(pielegniarki1.get(0));
            }
        }
    }
        
    public void setOddzialyList(ObservableList<Oddzial> oddzialyList)
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            if(!(o.getNazwaOddzialu().getValue().equals("nieznany")))
                this.oddzialyList.add(o.getNazwaOddzialu().getValue());
        choiceBoxOddzial.setItems(this.oddzialyList);
        choiceBoxOddzial.getSelectionModel().select(this.oddzialyList.get(0));
        
        dyzurList.clear();
        dyzurTempList.clear();
        dyzurTempList2.clear();
        dyzurTempList3.clear();
        dyzurTempList4.clear();
        DyzurUtil.clearDyzurList();
    }
}