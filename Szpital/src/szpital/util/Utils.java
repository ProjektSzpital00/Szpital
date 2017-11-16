package szpital.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;


public class Utils 
{
    public static void informacjaWyswietl(String contentText)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
    
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
    
    public static boolean walidacjaPola(TextField textField, String nazwaTextField)
    {
        String sprawdzeniePolaAdnotacja;
        
        if(textField.getText() == null || textField.getText().isEmpty())
        {
            sprawdzeniePolaAdnotacja = "Błąd ! Puste pole ( "+nazwaTextField+" ) !";
            Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
            return false;
        }
        else if(textField.getText().startsWith(" "))
        {
            sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" zaczyna sie od spacji !";
            Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
            textField.clear();
            return false;
        }
        else
        {	
            return true;
        }
    }
    
    public static boolean walidacjaPola(ChoiceBox<String> choiceBox, String choiceBoxName)
    {
        String sprawdzeniePolaAdnotacja;
        
        if(choiceBox.getSelectionModel().getSelectedItem() == null)
        {
            sprawdzeniePolaAdnotacja = "Błąd ! Nie wybrano nic z listy "+choiceBoxName+" !";
            Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static boolean walidacjaPolaNumerycznego(TextField textField, String nazwaTextField)
    {
        Long temp;
        String sprawdzeniePolaAdnotacja;
        
        if(textField.getText() == null || textField.getText().isEmpty())
        {
            sprawdzeniePolaAdnotacja = "Błąd ! Puste pole ( "+nazwaTextField+" ) !";
            Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
            return false;
        }
        else if(textField.getText().startsWith(" "))
        {
            sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" zaczyna sie od spacji !";
            Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
            textField.clear();
            return false;
        }
        else
        {
            if(nazwaTextField.equals("Pesel") && (textField.getText().length() < 11 || textField.getText().length() > 11))
            {
                sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" nie ma wymaganej długości !";
                Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
                textField.clear();
                return false;
            }
            else
            {
                try 
                {
                    temp = new Long(textField.getText());
                    
                    if(nazwaTextField.equals("ID") && temp < 0)
                    {
                        sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" nie może być ujemne !";
                        Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
                        textField.clear();
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                } 
                catch (NumberFormatException nfe) 
                {
                    sprawdzeniePolaAdnotacja = "Błąd ! Pole "+nazwaTextField+" nie jest w formacie numerycznym !";
                    Utils.alertWyswietl("Błąd walidacji danych", sprawdzeniePolaAdnotacja);
                    textField.clear();
                    return false;
                } 
            }
        }
    }
}
