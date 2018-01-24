package szpital.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Laczenie {
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static String ip = "80.211.205.68:3306";
	private static String db = "Szpital2";
	private static final String databaseUrl1 = "jdbc:mysql://"+ip+"/"+db;
	private static String user = "szpital";
	private static String password = "haslo";
	private static Connection dbConnection;
	private static Statement stmt;

	private static void getConnection() throws ClassNotFoundException, SQLException {

		File file = new File("ustawienia.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ip  = in.nextLine();
		db = in.nextLine();
		user = in.nextLine();
		password = in.nextLine();
		if (dbConnection == null) {
			try {
				Class.forName(driver);
				dbConnection = (Connection) DriverManager.getConnection(databaseUrl1 + "?useUnicode=yes&characterEncoding=UTF-8", user, password);
			} catch (ClassNotFoundException ex) {
				throw new ClassNotFoundException("Błąd sterownika MySQL JDBC", ex);
			} catch (SQLException exc) {
				throw new SQLException("Nie udalo się nawiązać połączenia", exc);
			}
		}
	}

	public static void closeConnection() {
		if (dbConnection != null) {
			try {
				dbConnection.close();
			} catch (SQLException ex) {
				System.out.println("Blad przy zamykaniu polaczenia!");
			}
		}
	}

	public static Statement getStatement() throws SQLException, ClassNotFoundException {
		if (stmt != null) {
			return stmt;
		} else {
			try {
				if (dbConnection != null) {
					stmt = dbConnection.createStatement();
				} else {
					getConnection();
					stmt = dbConnection.createStatement();
				}
			} catch (ClassNotFoundException ex) {
				throw new ClassNotFoundException("Błąd sterownika MySQL JDBC", ex);
			} catch (SQLException exc) {
				throw new SQLException("Nie udalo się nawiązać połączenia", exc);
			}

			return stmt;
		}
	}
}
