package szpital.view;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import szpital.model.Sala;

public class RezerwacjaSaliController 
{
    private Stage dialoStage;
    private LekarzController lekarzController;
    private ObservableList <String> salaList;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private ChoiceBox choiceBoxSale;

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
        this.salaList = FXCollections.observableArrayList();
        for(Sala s : salaList)
            this.salaList.add(s.getNazwa().getValue());
        choiceBoxSale.setItems(this.salaList);
        choiceBoxSale.getSelectionModel().select(this.salaList.get(0));
    }
    
    public void setDate()
    {
        datePicker.setValue(LocalDate.now());
    }
}
