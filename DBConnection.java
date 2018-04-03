
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

//importing libraries required for sql connectivity
import java.sql.*;

public class DBConnection {
	//Database connection variables initialization
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mkottala_db";

	//initialization of conenction object
	private Connection conn;

	public Connection connect() {
		if (conn == null) {
			try {
				//Registering the driver
				Class.forName(DB_DRIVER);
				//Connecting to the database using the url and credentials given
				conn = DriverManager.getConnection(DB_URL, "mkottala","mkottala");
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	// disconnect database
	public void disconnect() {
		if (conn != null) {
			try {
				//CLose the database connection
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
