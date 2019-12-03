package Utils;

import java.sql.*;

public class DBUtils {
//add a static methos to return DB conntn instacne
	public static Connection fetchConnection() throws Exception {
		// load jdbc drvr
		Class.forName("oracle.jdbc.OracleDriver");// optional step.
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		// get conntn to DB using Driver Manager.
		return DriverManager.getConnection(url, "system", "system");
	}
}
