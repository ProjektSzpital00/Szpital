package szpital.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import szpital.model.Mail;
import szpital.util.MailUtil;

import java.sql.SQLException;


public class AddMailController {
    private Stage dialoStage;
    private RejestracjaController rejestracjaController;
    private Integer id;

    @FXML
    private TextArea text;

    @FXML
    private void cancel()
    {
        dialoStage.close();
    }

    @FXML
    public void send() throws SQLException, ClassNotFoundException {
        MailUtil mailUtil = new MailUtil();
        Mail mail = new Mail();
        mail.setMail(text.getText(), mailUtil.getMail(id));
        cancel();
    }

    public void setRejestracjaController(RejestracjaController rejestracjaController, Integer id)
    {
        this.rejestracjaController = rejestracjaController;
        this.id = id;
    }

    public void setStage(Stage dialoStage)
    {
        this.dialoStage = dialoStage;
    }
}
