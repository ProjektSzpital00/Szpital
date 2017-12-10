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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import szpital.model.Rezerwacja;
import szpital.model.Sala;
import szpital.util.RezerwacjaUtil;
import szpital.util.Utils;

public class RezerwacjaSaliController 
{
    private Stage dialoStage;
    private LekarzController lekarzController;
    private ObservableList <String> salaNazwyList;
    private HashMap <String, Integer> salaIdList;
    private ObservableList <Rezerwacja> rezerwacjaList;
    private ObservableList <Rezerwacja> tempList1;
    private ObservableList <Rezerwacja> tempList2;
    private ArrayList <String> godzinyList;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ChoiceBox choiceBoxSale;
    
    @FXML
    private TableView<Rezerwacja> tabela;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnData;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnCzas;
    
    @FXML
    private TableColumn<Rezerwacja, String> ColumnRezerwujacy;
    
    @FXML
    private void initialize()
    {
        ColumnData.setCellValueFactory(cellData -> cellData.getValue().getTerminData());
        ColumnCzas.setCellValueFactory(cellData->cellData.getValue().getTerminCzas());
        ColumnRezerwujacy.setCellValueFactory(cellData->cellData.getValue().getRezerwujacy());
        
        godzinyList = new ArrayList<String>();
        rezerwacjaList = FXCollections.observableArrayList();
        tempList1 = FXCollections.observableArrayList();

        for(int i = 0; i < 24; i++)
        {
            if(i < 10)
                godzinyList.add("0"+i+":00:00");
            else
                godzinyList.add(i+":00:00");
        }     
    }
    
    public void init()
    {
        datePicker.valueProperty().addListener(new ChangeListener<LocalDate>()
        {
            @Override
            public void changed(ObservableValue observable, LocalDate oldValue, LocalDate newValue)
            {
                RezerwacjaUtil.clearRezerwacjaList();
                rezerwacjaList.clear();
                setRezerwacjeList();
            }
        });
        
        choiceBoxSale.valueProperty().addListener(new ChangeListener<String>()
        {
            @Override
            public void changed(ObservableValue observable, String oldValue, String newValue)
            {
                RezerwacjaUtil.clearRezerwacjaList();
                rezerwacjaList.clear();
                setRezerwacjeList();
            }
        });
    }

    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

    public void setLekarzController(LekarzController lekarzController) 
    {
        this.lekarzController = lekarzController;
    }
    
    public void setSaleList(ObservableList <Sala> salaList)
    {
        salaNazwyList = FXCollections.observableArrayList();
        salaIdList = new HashMap<String, Integer>();
        for(Sala s : salaList)
        {
            this.salaNazwyList.add(s.getNazwa().getValue());
            this.salaIdList.put(s.getNazwa().getValue(), s.getIdSali().getValue());
        }
        choiceBoxSale.setItems(this.salaNazwyList);
        choiceBoxSale.getSelectionModel().select(this.salaNazwyList.get(0));
    }
    
    public void setRezerwacjeList()
    {
        try
        {
            String data = datePicker.getValue().toString();
            String sala = choiceBoxSale.getSelectionModel().getSelectedItem().toString();
            Integer idSali = salaIdList.get(sala);
            
            if(idSali != null)
            {
                for(String godzina : godzinyList)
                    tempList1.add(new Rezerwacja(data, godzina, idSali, sala));
                tempList2 = RezerwacjaUtil.getRezerwacjaList(data, idSali);
                
                if(tempList2.isEmpty())
                {
                    for(Rezerwacja r1 : tempList1)
                        rezerwacjaList.add(r1);
                }
                else
                {
                    for(Rezerwacja r1 : tempList1)
                    {
                        for(Rezerwacja r2 : tempList2)
                        {
                            if(r1.getTerminCzas().getValue().equals(r2.getTerminCzas().getValue()))
                            {
                                rezerwacjaList.add(r2);
                            }
                            else
                            {
                                rezerwacjaList.add(r1);
                            }
                        }
                    }
                }
                
                tempList1.clear();
                tempList2.clear();
                tabela.setItems(rezerwacjaList);
            }
            else
                throw new Exception("Błąd przypisania nazwy sali do id");
        }
        catch(SQLException | ClassNotFoundException ex)
        {
            Utils.alertWyswietl(ex);
        } 
        catch (Exception ex) 
        {
            Utils.alertWyswietl(ex);
        }
    }
    
    public void setDate()
    {
        datePicker.setValue(LocalDate.now());
    }
}
