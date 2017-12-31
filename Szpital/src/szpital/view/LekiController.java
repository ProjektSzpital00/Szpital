package szpital.view;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import szpital.model.Badania;
import szpital.model.Leki;
import szpital.model.Pacjent;
import szpital.util.BadaniaUtil;
import szpital.util.LekiUtil;

public class LekiController 
{
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
    
    private Integer idPacjenta; // do pobierania danych z bazy(setter)
    private Pacjent wybranyPacjent;
    
    private LekarzController lekarzController;
    
    
    
    
    
    
     public void ladujListe(Integer i)
    {
       // if(badaniaList == null)
       // {
            try {    
                
                lekiList = LekiUtil.getLekiList(i);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BadaniaController.class.getName()).log(Level.SEVERE, null, ex);
            }
       // }
       /*
        for(Badania b: badaniaList)
        {
            System.out.println(b.getWynikBadania());
        }
        */
        nazwaLeku.setCellValueFactory(cellData->cellData.getValue().getNazwa());
        //imiePacjenta.setCellValueFactory(cellData->cellData.getValue().getImieNazwisko());
        dataOd.setCellValueFactory(cellData->cellData.getValue().getOdTermin());
        dataDo.setCellValueFactory(cellData->cellData.getValue().getDoTermin());
        dawkowanie.setCellValueFactory(cellData->cellData.getValue().getDawkowanie());
        
        
        tabela.setItems(lekiList);
    
    }
    
    
    
    
    @FXML
    public void dodajLek()
    {
        
    }
    
    @FXML
    public void edytujLek()
    {
        
    }
    
    
    public void setRejestracjaController(LekarzController lekarzController) 
    {
        this.lekarzController = lekarzController;
    }
    
    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }
    
    public void setWybranyPacjent(Pacjent wybranyPacjent) {
        this.wybranyPacjent = wybranyPacjent;
    }
}
