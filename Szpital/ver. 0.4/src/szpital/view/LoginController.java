package szpital.view;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.MainProgram;
import szpital.model.Account;
import szpital.util.Laczenie;
import szpital.util.ListaPacjentow;
import szpital.util.Uwierzytelnianie;

public class LoginController 
{
    private boolean sprawdzeniePola = false;
    private String sprawdzeniePolaAdnotacja = new String();
    private Stage primaryStage;

    @FXML
    TextField userId;

    @FXML
    PasswordField password;

    @FXML
    public void initialize()
    {
        addTextLimiter(userId, 30);
        addTextLimiter(password, 30);
    }

    @FXML
    public void logIn()
    {
        try
        {
            Statement stmt = Laczenie.connect();
            if(walidacjaPola(userId, "login") && walidacjaPola(password, "haslo"))
            {
                String id = userId.getText();
                String haslo = password.getText();

                if(Uwierzytelnianie.walidacja(id, haslo, stmt))
                {
                    Account account = new Account(id, haslo, stmt);
                    Laczenie.disconnect();
                    switch(account.getStanowisko())
                    {
                        case "rejestracja":
                            setRejestracjaController(account);
                            break;
                        case "lekarz":
                            setLekarzController(account);
                            break;
                        case "ordynator":
                            setOrdynatorController(account);
                            break;  
                        case "pielegniarka":
                            setPielegniarkaController(account);
                            break;
                        case "administracja":
                            setAdministracjaController(account);
                            break;
                        default:
                            alertWyswietl("Błąd!", "Konto bez przypisanego stanowiska.\n"
                                    + "Skontaktuj się z administratorem");
                    }      
                }
                else
                {
                    password.clear();
                    alertWyswietl("Błąd podczas logowania!", "Podano błędne dane logowania lub konto nie istnieje!");    
                }
            }
        }
        catch(SQLException | ClassNotFoundException exc)
        {
            Laczenie.disconnect();
            alertWyswietl(exc);
        }
    }

    @FXML
    public void exit()
    {
        Platform.exit();
    }

    public static void addTextLimiter(final TextField tf, final int maxLength) 
    {
        tf.textProperty().addListener(new ChangeListener<String>() 
        {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) 
            {
                if (tf.getText().length() > maxLength) 
                {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    private void alertWyswietl(String headerText, String contentText)
    {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(headerText);
            alert.setContentText(contentText);
            alert.showAndWait();
    }
    
    private void alertWyswietl(Exception exc)
    {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Błąd!");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
    }

    private boolean walidacjaPola(TextField textField, String nazwaTextField)
    {
            if(textField.getText() == null || textField.getText().isEmpty())
            {
                sprawdzeniePolaAdnotacja = "Błąd ! Puste pole ( "+nazwaTextField+" ) !";
                alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
                sprawdzeniePola = false;
            }
            else if(textField.getText().startsWith(" "))
            {
                sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" zaczyna sie od spacji !";
                alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
                textField.clear();
                sprawdzeniePola = false;
            }
            else
            {	
                sprawdzeniePola = true;
            }
            return sprawdzeniePola;
    }

    private void setRejestracjaController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("RejestracjaScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        RejestracjaController rejestracjaController = loader.getController();
        rejestracjaController.setAccount(account);
        rejestracjaController.setLoginController(this);
        try 
        {
            rejestracjaController.setPacjentList(ListaPacjentow.get());
        } 
        catch (SQLException | ClassNotFoundException exc) 
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Błąd!");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
        setScreen(anchorPane);
    }

    private void setLekarzController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LekarzScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        LekarzController lekarzController = loader.getController();
        lekarzController.setAccount(account);
        lekarzController.setLoginController(this);
        setScreen(anchorPane);
    }

    private void setOrdynatorController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OrdynatorScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        OrdynatorController ordynatorController = loader.getController();
        ordynatorController.setAccount(account);
        ordynatorController.setLoginController(this);
        setScreen(anchorPane);
    }

    private void setPielegniarkaController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PielegniarkaScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        PielegniarkaController pielegniarkaController = loader.getController();
        pielegniarkaController.setAccount(account);
        pielegniarkaController.setLoginController(this);
        setScreen(anchorPane);
    }

    private void setAdministracjaController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AdministracjaScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        AdministracjaController administracjaController = loader.getController();
        administracjaController.setAccount(account);
        administracjaController.setLoginController(this);
        setScreen(anchorPane);
    }
    
    public void setStage(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
    }
     
    public void setScreen(AnchorPane anchorPane) 
    {
        primaryStage.close();
        
        Scene scene = new Scene(anchorPane);
        primaryStage.centerOnScreen();
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setLoginScreen()
    {
        
        FXMLLoader loader = new FXMLLoader(MainProgram.class.getResource("view/LoginScreen.fxml"));
        AnchorPane anchorPane = null;
        try
        {
            anchorPane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        LoginController loginController = loader.getController();
        loginController.setStage(primaryStage); 
        loginController.setScreen(anchorPane);
    }
}