package szpital.view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import szpital.model.Rezerwacja;
import szpital.model.Sala;
import szpital.util.Laczenie;
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
    private ObservableList <Rezerwacja> tempList3;
    private ObservableList <Rezerwacja> tempList4;
    private ArrayList <String> godzinyList;
    //private TreeMap<String, Integer> godzinyMap;
    
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
        //godzinyMap = new TreeMap<String, Integer>();
        rezerwacjaList = FXCollections.observableArrayList();
        tempList1 = FXCollections.observableArrayList();
        tempList3 = FXCollections.observableArrayList();
        tempList4 = FXCollections.observableArrayList();
        tabela.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        for(int i = 0; i < 24; i++)
        {
            if(i < 10)
            {
                godzinyList.add("0"+i+":00:00");
                //godzinyMap.put("0"+i+":00:00", i);
            }
            else
            {
                godzinyList.add(i+":00:00");
                //godzinyMap.put(i+":00:00", i);
            }
        }     
    }
    
    @FXML
    public void dodajRezerwacje()
    {
        tempList3 = tabela.getSelectionModel().getSelectedItems();

        for(int h = 0; h < tempList3.size(); h++)
        {
            if(tempList3.get(h).getRezerwujacy() != null) 
            {
                if(tempList3.get(h).getRezerwujacy().getValue().isEmpty())
                    tempList4.add(tempList3.get(h));
            }
            else
                tempList4.add(tempList3.get(h));
        }
        tempList3 = tempList4;
        
        if(tempList3.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd operacji");
            alert.setHeaderText("Wybrano niedostępne pola");
            alert.showAndWait();
        }
        else
        {
            try
            {
                for(Rezerwacja r : tempList3)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potwierdzenie operacji");
                    alert.setHeaderText("Dokonanie rezerwacji");
                    alert.setContentText("Czy chcesz dokonać następującej rezerwacji: \n\n"
                            + "Data:\t\t\t\t"+r.getTerminData().getValue()+"\n"
                            + "Godzina:\t\t\t\t"+r.getTerminCzas().getValue()+"\n"
                            + "Sala:\t\t\t\t\t"+r.getSala().getValue()+"\n");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        r.setIdRezerwującego(new SimpleIntegerProperty(lekarzController.getAccount().getId_lekarza()));
                        RezerwacjaUtil.addRezerwacja(Laczenie.getStatement(), r);
                        RezerwacjaUtil.clearRezerwacjaList();
                        setRezerwacjeList();
                        RezerwacjaUtil.clearRezerwacjaList2();
                        lekarzController.setRezerwacjeSal();
                        alert.close();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Potwierdzenie dokonania operacji");
                        alert.setHeaderText("Pomyślnie dodano rezerwacje");
                        alert.showAndWait();   
                    }
                    else
                    {
                        alert.close();
                    }  
                }
                dialoStage.close();
            }
            catch (SQLException | ClassNotFoundException  ex) 
            {
                Utils.alertWyswietl(ex);
            }
        }
        /*for(int h = 0; h < tempList3.size(); h++)
        {
            if(tempList3.get(h).getRezerwujacy() != null) 
            {
                if(tempList3.get(h).getRezerwujacy().getValue().isEmpty() || (tempList3.get(h).getRezerwujacy().getValue().equals(lekarzController.getAccount().getNazwisko()+" "+lekarzController.getAccount().getImie())))
                    tempList4.add(tempList3.get(h));
            }
            else
                tempList4.add(tempList3.get(h));
        }*/
        //tempList3 = tempList4;
        
        /*
        ArrayList<String> tempBlock = new ArrayList<String>();
        ArrayList<String> tempBlock2 = new ArrayList<String>();
        TreeSet<Integer> temp1 = new TreeSet<Integer>();
        int [] temp2 = new int[tempList3.size()];
        int j = 0;
        for(Rezerwacja rr : tempList3)
        {
            temp2[j] = godzinyMap.get(rr.getTerminCzas().getValue());
            j++;
        }

        for(int i = 0; i < temp2.length; i++)
        {
            if(i == temp2.length - 1)
            {
                if(temp2[i] != temp2[i-1])
                {
                    temp1.add(temp2[i]);
                    tempBlock.add(temp1.toString());
                    temp1.clear();
                    break;
                }
                else
                    break;
            }

            if(temp2[i+1] == temp2[i]+1)
            {
                temp1.add(temp2[i]);
                temp1.add(temp2[i+1]);
                for(int k = i; k < temp2.length; k++)
                {
                    if(k == temp2.length - 1)
                    {
                        if(temp2[k] == temp2[k-1])
                        {
                            temp1.add(temp2[k]);
                            break;
                        }
                        else
                        {
                            i = k;
                            break;
                        }
                    }

                    if(temp2[k+1] == temp2[k]+1)
                    {
                        temp1.add(temp2[k+1]);
                    }
                    else
                    {
                        i = k;
                        break;
                    }
                }
                tempBlock.add(temp1.toString());
                temp1.clear();
            }
            else
            {
                temp1.add(temp2[i]);
                tempBlock.add(temp1.toString());
                temp1.clear();
            }
        }

        int[][] results = new int[tempBlock.size()][];
        int g = 0;
        for(String s : tempBlock)
        {
            String[] items = s.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
            results[g] = new int[items.length];

            for (int i = 0; i < items.length; i++) 
            {
                try 
                {
                    results[g][i] = Integer.parseInt(items[i]);
                } 
                catch (NumberFormatException nfe) 
                {

                }
            }   
            g++;
        }
          
        try
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            for(int [] k : results)
            {
                alert.setTitle("Potwierdzenie operacji");
                alert.setHeaderText("Dokonanie rezerwacji");
                alert.setContentText("Czy chcesz dokonać następującej rezerwacji: \n\n"
                        + "Data:\t\t\t\t"+tempList3.get(0).getTerminData().getValue()+"\n"
                        + "Godziny:\t\t\t\t"+k[0]+(k[k.length-1]+1)+"\n"
                        + "Sala:\t\t\t\t\t"+tempList3.get(0).getSala().getValue()+"\n");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    for(int o : k)
                        tempList1.add(new Rezerwacja(tempList3.get(0).getTerminData().getValue(), Integer.toString(o), tempList3.get(0).getIdSali().getValue(), tempList3.get(0).getSala().getValue()));
                    
                    dokończ tutaj
                    
                    for(Rezerwacja r : tempList3)
                        RezerwacjaUtil.addRezerwacja(Laczenie.getStatement(), r);
                    RezerwacjaUtil.clearRezerwacjaList();
                    setRezerwacjeList();
                    dialoStage.close();
                }
                else
                {
                    alert.close();
                }
            }
            
        } 
        catch (SQLException | ClassNotFoundException  ex) 
        {
            Utils.alertWyswietl(ex);
        } */
    }
    
    @FXML
    public void usunRezerwacje()
    {
        tempList3 = tabela.getSelectionModel().getSelectedItems();

        for(int h = 0; h < tempList3.size(); h++)
        {
            if(tempList3.get(h).getRezerwujacy() != null) 
            {
                if(!(tempList3.get(h).getRezerwujacy().getValue().isEmpty()) || (tempList3.get(h).getRezerwujacy().getValue().equals(lekarzController.getAccount().getNazwisko()+" "+lekarzController.getAccount().getImie())))
                    tempList4.add(tempList3.get(h));
            }
        }
        tempList3 = tempList4;
        
        if(tempList3.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Błąd operacji");
            alert.setHeaderText("Wybrano niedostępne pola");
            alert.showAndWait();
        }
        else
        {
            try
            {
                for(Rezerwacja r : tempList3)
                {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potwierdzenie operacji");
                    alert.setHeaderText("Usunięcie rezerwacji");
                    alert.setContentText("Czy chcesz usunąć następującą rezerwację: \n\n"
                            + "Data:\t\t\t\t"+r.getTerminData().getValue()+"\n"
                            + "Godzina:\t\t\t\t"+r.getTerminCzas().getValue()+"\n"
                            + "Sala:\t\t\t\t\t"+r.getSala().getValue()+"\n");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                    {
                        RezerwacjaUtil.deleteRezerwacja(Laczenie.getStatement(), RezerwacjaUtil.searchRezerwacjaId(Laczenie.getStatement(), r.getTermin().getValue()));
                        RezerwacjaUtil.clearRezerwacjaList();
                        setRezerwacjeList();
                        RezerwacjaUtil.clearRezerwacjaList2();
                        lekarzController.setRezerwacjeSal();
                        alert.close();
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Potwierdzenie dokonania operacji");
                        alert.setHeaderText("Pomyślnie usunięto rezerwacje");
                        alert.showAndWait();   
                    }
                    else
                    {
                        alert.close();
                    }  
                }
                dialoStage.close();
            }
            catch (SQLException | ClassNotFoundException  ex) 
            {
                Utils.alertWyswietl(ex);
            }
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
                    boolean znacznik = true;
                    for(Rezerwacja r1 : tempList1)
                    {
                        for(Rezerwacja r2 : tempList2)
                        {
                            if(r1.getTerminCzas().getValue().equals(r2.getTerminCzas().getValue()))
                            {
                                rezerwacjaList.add(r2);
                                znacznik = false;
                                break;
                            }
                        }
                        if(znacznik)
                        {
                            rezerwacjaList.add(r1);
                        }
                        znacznik = true;
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
