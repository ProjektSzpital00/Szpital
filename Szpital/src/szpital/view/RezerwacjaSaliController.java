package szpital.view;

import javafx.stage.Stage;

public class RezerwacjaSaliController 
{
    private Stage dialoStage;
    private LekarzController lekarzController;

    public void setStage(Stage dialoStage) 
    {
        this.dialoStage = dialoStage;
    }

    public void setLekarzController(LekarzController lekarzController) 
    {
        this.lekarzController = lekarzController;
    }
}
