package szpital.view;

import javafx.fxml.FXML;
import szpital.model.Account;
import szpital.util.LekarzUtil;
import szpital.util.OddzialUtil;
import szpital.util.PacjentUtil;

public class AdministracjaController 
{
    private Account account;
    private LoginController log;

    @FXML
    public void logout()
    {
        PacjentUtil.clearPacjentList();
        LekarzUtil.clearLekarzList();
        OddzialUtil.clearOddzialyList();
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
