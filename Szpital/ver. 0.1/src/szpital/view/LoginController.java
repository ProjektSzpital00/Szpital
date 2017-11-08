package szpital.view;

import java.io.IOException;
import java.sql.Statement;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import szpital.MainProgram;
import szpital.model.Account;
import szpital.util.Laczenie;
import szpital.util.Uwierzytelnianie;

public class LoginController 
{
    private boolean sprawdzeniePola = false;
    private String sprawdzeniePolaAdnotacja = new String();

    @FXML
    private StackPane mainStackPane;

    @FXML
    TextField userId;

    @FXML
    PasswordField password;

    @FXML
    public void initialize()
    {
            userId.setPromptText("Id");
            
            addTextLimiter(userId, 30);
            addTextLimiter(password, 30);
    }

    @FXML
    public void logIn()
    {
        Statement stmt = Laczenie.connect();
        if(walidacjaPola(userId) && walidacjaPola(password))
        {
            String id = userId.getText();
            String haslo = password.getText();

            if(Uwierzytelnianie.walidacja(id, haslo, stmt))
            {
                Account account = new Account(new Integer(id), haslo, stmt);
                Laczenie.closeConnection();
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
                }      
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Blad");
                alert.setHeaderText("Blad podczas logowania");
                alert.setContentText("Podano bledne dane logowania lub konto nie istnieje!");
                alert.showAndWait();
            }
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

    private void alertWyswietl(String message)
    {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Błąd walidacji danych");
            alert.setHeaderText(message);
            alert.showAndWait();
    }

    private boolean walidacjaPola(TextField textField)
    {
            if(textField.getText() == null || textField.getText().isEmpty())
            {
                sprawdzeniePolaAdnotacja = "Błąd ! Puste pole ( "+textField.getPromptText()+" ) !";
                alertWyswietl(sprawdzeniePolaAdnotacja);
                sprawdzeniePola = false;
            }
            else if(textField.getText().startsWith(" "))
            {
                sprawdzeniePolaAdnotacja = "Błąd ! Pole "+textField.getPromptText()+" zaczyna sie od spacji !";
                alertWyswietl(sprawdzeniePolaAdnotacja);
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
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        RejestracjaController rejestracjaController = loader.getController();
        rejestracjaController.setAccount(account);
        rejestracjaController.setLoginController(this);
        setScreen(pane);
    }

    private void setLekarzController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LekarzScreen.fxml"));
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        LekarzController lekarzController = loader.getController();
        lekarzController.setAccount(account);
        lekarzController.setLoginController(this);
        setScreen(pane);
    }

    private void setOrdynatorController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OrdynatorScreen.fxml"));
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        OrdynatorController ordynatorController = loader.getController();
        ordynatorController.setAccount(account);
        ordynatorController.setLoginController(this);
        setScreen(pane);
    }

    private void setPielegniarkaController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PielegniarkaScreen.fxml"));
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        PielegniarkaController pielegniarkaController = loader.getController();
        pielegniarkaController.setAccount(account);
        pielegniarkaController.setLoginController(this);
        setScreen(pane);
    }

    private void setAdministracjaController(Account account)
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AdministracjaScreen.fxml"));
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        AdministracjaController administracjaController = loader.getController();
        administracjaController.setAccount(account);
        administracjaController.setLoginController(this);
        setScreen(pane);
    }
        
    public void setScreen(Pane pane) 
    {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
    
    public Pane getLoginPane()
    {
        FXMLLoader loader = new FXMLLoader(MainProgram.class.getResource("view/LoginScreen.fxml"));
        Pane pane = null;
        try
        {
            pane = loader.load();
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        
        return pane;
    }
    
    public StackPane getMainStackPane() 
    {
        return mainStackPane;
    }
}