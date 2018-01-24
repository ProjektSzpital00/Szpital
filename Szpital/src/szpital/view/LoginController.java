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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import szpital.MainProgram;
import szpital.model.Account;
import szpital.util.Laczenie;
import szpital.util.Utils;
import szpital.util.Uwierzytelnianie;
import szpital.util.PacjentUtil;

public class LoginController 
{
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
            Statement stmt = Laczenie.getStatement();
            if(Utils.walidacjaPola(userId, "login") && Utils.walidacjaPola(password, "haslo"))
            {
                String id = userId.getText();
                String haslo = password.getText();

                if(Uwierzytelnianie.walidacja(id, haslo, stmt))
                {
                    Account account = new Account(id, haslo, stmt);
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
                        default:
                            Utils.alertWyswietl("Błąd!", "Konto bez przypisanego stanowiska.\n"
                                    + "Skontaktuj się z administratorem");
                    }      
                }
                else
                {
                    userId.clear();
                    password.clear();
                    Utils.alertWyswietl("Błąd podczas logowania!", "Podano błędne dane logowania lub konto nie istnieje!");    
                }
            }
        }
        catch(SQLException | ClassNotFoundException exc)
        {
            Laczenie.closeConnection();
            Utils.alertWyswietl(exc);
        }
    }

    @FXML
    public void exit()
    {
        Laczenie.closeConnection();
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

    private void setRejestracjaController(Account account)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("RejestracjaScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            RejestracjaController rejestracjaController = loader.getController();
            rejestracjaController.setAccount(account);
            rejestracjaController.setLoginController(this);
            rejestracjaController.setPacjentList(PacjentUtil.getPacjentList());
          
            primaryStage.close();
            Scene scene = new Scene(anchorPane);
            primaryStage.centerOnScreen();
            primaryStage.setWidth(1390);
            primaryStage.setHeight(800);
            primaryStage.setMinWidth(1390);
            primaryStage.setMinHeight(800);
            primaryStage.setMaxWidth(1390);
            primaryStage.setMaxHeight(800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IllegalStateException ex)
        {
            Utils.alertWyswietl(ex);
            exit();
        }
        catch (IOException | SQLException | ClassNotFoundException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }

    private void setLekarzController(Account account)
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("LekarzScreen.fxml"));
            AnchorPane splitPane = loader.load();
            
            LekarzController lekarzController = loader.getController();
            lekarzController.setAccount(account);
            lekarzController.setLoginController(this);
            lekarzController.setPacjentList(PacjentUtil.getPacjentList());
            lekarzController.setRezerwacjeSal();
            lekarzController.setDyzuryTabelka();
            
            primaryStage.close();
            Scene scene = new Scene(splitPane);
            primaryStage.centerOnScreen();
            primaryStage.setWidth(1390);
            primaryStage.setHeight(800);
            primaryStage.setMinWidth(1390);
            primaryStage.setMinHeight(800);
            primaryStage.setMaxWidth(1390);
            primaryStage.setMaxHeight(800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IllegalStateException ex)
        {
            Utils.alertWyswietl(ex);
            exit();
        }
        catch (IOException | SQLException | ClassNotFoundException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }

    private void setOrdynatorController(Account account) 
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("OrdynatorScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            OrdynatorController ordynatorController = loader.getController();
            ordynatorController.setAccount(account);
            ordynatorController.setLoginController(this);
            ordynatorController.setPacjentList(PacjentUtil.getPacjentList());
            ordynatorController.setRezerwacjeSal();
            ordynatorController.setDyzuryTabelka();
            
            primaryStage.close();
            Scene scene = new Scene(anchorPane);
            primaryStage.centerOnScreen();
            primaryStage.setWidth(1390);
            primaryStage.setHeight(800);
            primaryStage.setMinWidth(1390);
            primaryStage.setMinHeight(800);
            primaryStage.setMaxWidth(1390);
            primaryStage.setMaxHeight(800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
         catch(IllegalStateException ex)
        {
            Utils.alertWyswietl(ex);
            exit();
        }
        catch (IOException | SQLException | ClassNotFoundException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }

    private void setPielegniarkaController(Account account) throws SQLException, ClassNotFoundException
    {
       try
        {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PielegniarkaScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            PielegniarkaController pielegniarkaController = loader.getController();
            pielegniarkaController.setAccount(account);
            pielegniarkaController.setLoginController(this);
            pielegniarkaController.setPacjentList(PacjentUtil.getPacjentList());
            pielegniarkaController.setDyzuryTabelka();
            
            primaryStage.close();
            Scene scene = new Scene(anchorPane);
            primaryStage.centerOnScreen();
            primaryStage.setWidth(1390);
            primaryStage.setHeight(800);
            primaryStage.setMinWidth(1390);
            primaryStage.setMinHeight(800);
            primaryStage.setMaxWidth(1390);
            primaryStage.setMaxHeight(800);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(IllegalStateException ex)
        {
            Utils.alertWyswietl(ex);
            exit();
        }
        catch (IOException | SQLException | ClassNotFoundException exc) 
        {
            Utils.alertWyswietl(exc);
        }
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
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void setLoginScreen()
    {
        try
        {
            FXMLLoader loader = new FXMLLoader(MainProgram.class.getResource("view/LoginScreen.fxml"));
            AnchorPane anchorPane = loader.load();
            
            LoginController loginController = loader.getController();
            loginController.setStage(primaryStage);
            primaryStage.setMaxHeight(320);
            primaryStage.setMaxWidth(310);
            primaryStage.setMinHeight(320);
            primaryStage.setMinWidth(310); 
            primaryStage.centerOnScreen();
            loginController.setScreen(anchorPane);
        }
        catch(IllegalStateException ex)
        {
            Utils.alertWyswietl(ex);
            exit();
        }
        catch (IOException exc) 
        {
            Utils.alertWyswietl(exc);
        }
    }
    
    public Stage getPrimaryStage() 
    {
        return primaryStage;
    }
}