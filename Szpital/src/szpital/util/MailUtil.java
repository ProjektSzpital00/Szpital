package szpital.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MailUtil {

    public static String getMail(Integer id) throws SQLException, ClassNotFoundException
    {
        String ret = null;
            try
            {
                Statement stmt = Laczenie.getStatement();

                String query = "Select email from Pacjenci where id = "+ id.toString() +";";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    ret = rs.getString("Pacjenci.email");
                }
            }
            catch ( ClassNotFoundException ex)
            {
                throw ex;
            }
        return ret;
    }
}
