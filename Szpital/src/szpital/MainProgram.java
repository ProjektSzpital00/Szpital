package szpital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.util.MyAlert;
import szpital.view.LoginController;

public class MainProgram extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }
	

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(MainProgram.class.getResource("view/LoginScreen.fxml"));       
            AnchorPane anchorPane = (AnchorPane)loader.load();
            
            LoginController loginController = loader.getController();
            loginController.setStage(primaryStage);
            
            Scene scene = new Scene(anchorPane);

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IllegalStateException ex)
        {
            MyAlert.alertWyswietl(ex);
        }
    }
}