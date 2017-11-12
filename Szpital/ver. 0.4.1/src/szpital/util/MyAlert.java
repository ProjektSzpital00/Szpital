package szpital.util;

import javafx.scene.control.Alert;


public class MyAlert 
{
    public static void alertWyswietl(String headerText, String contentText)
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
    }
    
    public static void alertWyswietl(Exception exc)
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Błąd!");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
    }
}
