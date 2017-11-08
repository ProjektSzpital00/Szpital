package szpital;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainProgram extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
            FXMLLoader loader = new FXMLLoader(MainProgram.class.getResource("view/LoginScreen.fxml"));

            StackPane stackPane = (StackPane)loader.load();
             
            Scene scene = new Scene(stackPane);

            primaryStage.setScene(scene);
            primaryStage.setTitle("System Szpitalny");
            primaryStage.setResizable(false);
            primaryStage.show();
    }
}