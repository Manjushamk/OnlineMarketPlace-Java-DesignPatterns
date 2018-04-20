
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

// Ryan: Do you really need everything in this package?
// Fixed: I have modified the import statement accordingly to import the minimum number of 
// classes/interfaces required for database connectivity
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	//Database connection variables initialization
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mkottala_db";

	//initialization of conenction object
	private volatile Connection conn;

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
