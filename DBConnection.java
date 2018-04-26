
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
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	//Database connection variables initialization
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mkottala_db";
	private volatile Connection conn = null;
	private boolean isConnected = false;
	private ResultSet results;
	private Statement statement = null;
	private String Query = "";

	public DBConnection(){
		if (!isConnected) {
			this.conn = this.connect();
			isConnected = true;
		}
	}

	//initialization of conenction object and making it volatile, So that the 
	//access to the connection acts as synchrozied block, but doen't lock the object


	// funcition for establishing database connection
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



	public ResultSet executeSelectQueries(){
		try{
			createStatementCheck();
			results = statement.executeQuery(Query);
		}
		catch (SQLException e) {
			System.out.println("Error in select Query");
		}
		return results;
	}

	public boolean executeUpdateQueries(){
		try{
			createStatementCheck();
			statement.executeUpdate(Query);
			return true;
		}
		catch (SQLException e) {
			System.out.println("Error in select Query");
		}
		return false;
	}


	public ResultSet registrationInsert(){
		if (conn != null) {
			this.createStatementCheck();
			try{
				statement.executeUpdate(Query,Statement.RETURN_GENERATED_KEYS);
				results = statement.getGeneratedKeys();
			}
			catch (SQLException e) {
				System.out.println("Error in insertion while registering");
			}
		}
		return results;
	}

	public void createStatementCheck(){
		statement = null;
		try{
			statement = conn.createStatement();
		}
		catch(SQLException e){
			System.out.println("Exception in statement creation");
		}
	}


	//function for checking database conenction
	public boolean checkConnection(){
		if(conn != null){
			return true;
		}
		return false;
	}

	public ResultSet getCartId(int customerId){
		this.Query = "SELECT cart_id from tbl_cart WHERE customer_id = "+customerId;
		return executeSelectQueries();
	}

	public void generateRegiserQuery(String firstName, String lastName,String userName, String password){
		this.Query = "INSERT INTO tbl_customer(firstName,lastName,userName,password) VALUES('"+ firstName+"','" + lastName +"','"+userName+"','"+password+"')";
	}

	public void generateAddItemsQuery(String[] itemRow){
		this.Query = "INSERT INTO tbl_items(itemName,description,price,quantity) VALUES("+ "'" + itemRow[0] +"','" + itemRow[1] +"',"+Integer.parseInt(itemRow[2])+","+Double.parseDouble(itemRow[3])+")";
	}

	public void generateDeleteItemsSelectQuery(int itemId){
		this.Query = "SELECT * FROM tbl_items where item_id = "+itemId;
	}	

	public void generateDeleteItemsQuery(int itemId){
		this.Query = "DELETE FROM tbl_items where item_id = "+ itemId;
	}

	public void generateAddAdminQuery(String[] adminRow){
		this.Query = "INSERT INTO tbl_admin(firstName,lastName,userName,password) VALUES("+ "'" + adminRow[0] +"','" + adminRow[1] +"','"+adminRow[2]+"','"+adminRow[3]+"')";
	}

	public void generateAddUserQuery(String[] userRow){
		this.Query ="INSERT INTO tbl_customer(firstName,lastName,userName,password) VALUES("+ "'" + userRow[0] +"','" + userRow[1] +"','"+userRow[2]+"','"+userRow[3]+"')";
	}
}
