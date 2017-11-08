package szpital.view;

import javafx.fxml.FXML;
import szpital.model.Account;

public class RejestracjaController 
{
    private Account account;
    private LoginController log;

    @FXML
    public void logout()
    {
        log.setLoginScreen();
    }
    
    public void setAccount(Account account)
    {
        this.account = account;
    }
    
    public void setLoginController(LoginController log)
    {
        this.log = log;
    }
}
