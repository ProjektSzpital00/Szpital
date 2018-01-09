/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.sql.SQLException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import szpital.model.Statystyka;
import szpital.util.StatystykaUtil;

/**
 *
 * @author Bartek
 */
public class StatystykiController
{
    private ObservableList<Statystyka> StatystykaList;
    private ObservableList<Statystyka> StatystykaOddzialuList = FXCollections.observableArrayList();
    
    @FXML
    TableView<Statystyka> tabelaStatystyk;
    
    //@FXML
   // private TableColumn<Statystyka, String> ColumnNazwaOddzialu;
    
    @FXML
    private TableColumn<Statystyka, String> ColumnSala;
    
    @FXML
    private TableColumn<Statystyka, Integer> ColumnNrLozka;
    
    @FXML
    private TableColumn<Statystyka, String> ColumnCzyZajete;
    
    @FXML
    private ChoiceBox ChoiceBoxOddzial;
    
    
    private ObservableList <String> listaBadan = FXCollections.observableArrayList();
    
    
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException
    {
        this.StatystykaList = StatystykaUtil.getStatystykaList();
        
        
        //ColumnNazwaOddzialu.setCellValueFactory(cellData->cellData.getValue().getNazwaOddzialu());
        ColumnSala.setCellValueFactory(cellData->cellData.getValue().getSala());
        ColumnNrLozka.setCellValueFactory(cellData -> cellData.getValue().getNrLozka().asObject());
        ColumnCzyZajete.setCellValueFactory(cellData->cellData.getValue().getCzyZajeteString());
        
        
        
        
        
        
        for(Statystyka s: StatystykaList)
        {
            String niez = "nieznany";
            if(!listaBadan.contains(s.getNazwaOddzialu().getValue()) && !s.getNazwaOddzialu().getValue().equals(niez) )
            {
                listaBadan.add(s.getNazwaOddzialu().getValue());
            }
            //System.out.println(s.getNazwaOddzialu() + " " + s.getSala() + " " + s.getNrLozka() + " " + s.getCzyZajete());
        }
        ChoiceBoxOddzial.setItems(listaBadan);
        
        /*
        ChoiceBoxOddzial.getSelectionModel().selectedIndexProperty().addListener(
        new ChangeListener<Number>()
        {
            public void changed(ObservableValue ov,Number value,Number new_value)
            {
                System.out.println(ChoiceBoxOddzial.getValue());
                //System.out.println(ChoiceBoxOddzial.getValue().toString());
                //wyswietl(ChoiceBoxOddzial.getValue().toString());
            }
        });
        */
        ChoiceBoxOddzial.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<String>()
        {
            public void changed(ObservableValue ov,String value,String new_value)
            {
                //System.out.println(ChoiceBoxOddzial.getValue());
                //System.out.println(ChoiceBoxOddzial.getValue().toString());
                wyswietl(ChoiceBoxOddzial.getValue().toString());
            }
        });
        
        //tabelaStatystyk.setItems(this.StatystykaList);
    }
    
    
    public void wyswietl(String str)
    {
        StatystykaOddzialuList.removeAll(StatystykaOddzialuList);
        for(Statystyka s: StatystykaList)
        {
            if(s.getNazwaOddzialu().getValue().equals(str))
            {
                StatystykaOddzialuList.add(s);
            }
        }
        
    tabelaStatystyk.setItems(this.StatystykaOddzialuList);
    }
    
}
