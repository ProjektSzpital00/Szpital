package szpital.view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import szpital.model.Leki;
import szpital.model.Pacjent;
import szpital.util.LekiUtil;

public class LekiPielegniarkaController {

    private ObservableList<Leki> lekiList;
    private Stage dialoStage;
    
    @FXML
    private TableView<Leki> tabela;
    
    
    @FXML
    private TableColumn<Leki, String> nazwaLeku;
    
    @FXML
    private TableColumn<Leki, String> imiePacjenta;
    
    @FXML
    private TableColumn<Leki, String> dataOd;
    
    @FXML
    private TableColumn<Leki, String> dataDo;
    
    @FXML
    private TableColumn<Leki, String> dawkowanie;
    
    private Integer idPacjenta;
    private Pacjent wybranyPacjent;

    PielegniarkaController  pielegniarkaController;

    public void ladujListe(Integer i)
    {
       
            try {    
                lekiList = LekiUtil.getLekiList(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
       
        nazwaLeku.setCellValueFactory(cellData->cellData.getValue().getNazwa());
        dataOd.setCellValueFactory(cellData->cellData.getValue().getOdTermin());
        dataDo.setCellValueFactory(cellData->cellData.getValue().getDoTermin());
        dawkowanie.setCellValueFactory(cellData->cellData.getValue().getDawkowanie());
        
        
        tabela.setItems(lekiList);
    
    }
     
      public void setPielegniarkaController(PielegniarkaController  pielegniarkaController) 
    {
        this.pielegniarkaController = pielegniarkaController;
    }
      
       public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }
    
    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
}
