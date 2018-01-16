package szpital.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
import szpital.model.Oddzial;
import szpital.util.Utils;

public class DyzuryController 
{
    private Stage dialogStage;
    private OrdynatorController ordynatorController;
    private ObservableList <String> oddzialyList;
    private ObservableList <Dyzur> dyzurList;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ChoiceBox choiceBoxOddzial;
    
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
        //ColumnData.setCellValueFactory(cellData -> cellData.getValue().getTerminData());
        //ColumnCzas.setCellValueFactory(cellData->cellData.getValue().getTerminCzas());
        //ColumnRezerwujacy.setCellValueFactory(cellData->cellData.getValue().getRezerwujacy());
        
        //godzinyList = new ArrayList<String>();
        //rezerwacjaList = FXCollections.observableArrayList();
        //tempList1 = FXCollections.observableArrayList();
        //tempList3 = FXCollections.observableArrayList();
        //tempList4 = FXCollections.observableArrayList();
        //tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
    }
    
    @FXML
    public void dodajDyzur()
    {
        
    }
    
    @FXML
    public void usunDyzur()
    {
        
    }
    
    public void init()
    {
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
                //dyzurList.clear();
                try
                {
                    setDyzurList();
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
        
    }
    
    public void setOddzialyList(ObservableList<Oddzial> oddzialyList)
    {
        this.oddzialyList = FXCollections.observableArrayList();
        for(Oddzial o : oddzialyList)
            if(!(o.getNazwaOddzialu().getValue().equals("nieznany")))
                this.oddzialyList.add(o.getNazwaOddzialu().getValue());
        choiceBoxOddzial.setItems(this.oddzialyList);
        choiceBoxOddzial.getSelectionModel().select(this.oddzialyList.get(0));
    }
}