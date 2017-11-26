/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.view;

import java.sql.Date;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import szpital.model.Badania;
import szpital.model.Pacjent;
import szpital.util.BadaniaUtil;

/**
 *
 * @author Bartek
 */
public class BadaniaController 
{
    private ObservableList<Badania> badaniaList;
    
    
    @FXML
    private TableView<Badania> tabela;
    
    
    @FXML
    private TableColumn<Badania, String> nazwaBadania;
    
    @FXML
    private TableColumn<Badania, String> imiePacjenta;
    
    @FXML
    private TableColumn<Badania, Date> dataBadania;
    
    @FXML
    private TableColumn<Badania, String> wynikBadania;
    
    public void initialize()
    {
        try {    
            badaniaList = BadaniaUtil.getBadaniaList(new Integer(1));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        nazwaBadania.setCellValueFactory(cellData->cellData.getValue().getNazwaBadania());
        imiePacjenta.setCellValueFactory(cellData->cellData.getValue().getImiePacjenta());
       // dataBadania.setCellValueFactory(cellData->cellData.getValue().getDataBadania());
        wynikBadania.setCellValueFactory(cellData->cellData.getValue().getWynikBadania());
        
        tabela.setItems(badaniaList);
    }
    
    @FXML
    public void dodajBadanie()
    {
        System.out.println(badaniaList.get(1).getNazwaBadania());
    }
}
