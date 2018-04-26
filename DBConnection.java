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
	//initialization of connection object and making it volatile, So that the 
	//access to the connection acts as synchronized block, but doen't lock the object
	private volatile Connection conn = null;
	private boolean isConnected = false;
	private ResultSet results;
	private Statement statement = null;
	private String Query = "";

	public DBConnection(){
		//Called only the boolean variable is set to false, when connection is not established
		if (!isConnected) {
			this.conn = this.connect();
			isConnected = true;
		}
	}


	// function for establishing database connection
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


	//Method for executing the Select Queries and returning Resultset object
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

	//Method for executing Delete, Update or Insert Queries and return true on successful execution
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

	//Separate method for Registration Query Execution
	//RETURN_GENERATED_KEYS is used to get the auto-incremented key
	public ResultSet registrationInsert(){
		if (conn != null) {
			this.createStatementCheck();
			try{
				statement.executeUpdate(Query,Statement.RETURN_GENERATED_KEYS);
				//gets the auto-incremented field result
				results = statement.getGeneratedKeys();
			}
			catch (SQLException e) {
				System.out.println("Error in insertion while registering");
			}
		}
		return results;
	}
	
	//Method for statement creation
	public void createStatementCheck(){
		statement = null;
		try{
			statement = conn.createStatement();
		}
		catch(SQLException e){
			System.out.println("Exception in statement creation");
		}
	}

	//function for checking database connection
	public boolean checkConnection(){
		if(conn != null){
			return true;
		}
		return false;
	}

	//Method for getting auto-incremented cartId during user registration
	public ResultSet getCartId(int customerId){
		this.Query = "SELECT cart_id from tbl_cart WHERE customer_id = "+customerId;
		return executeSelectQueries();
	}

	//Method setting Query for Insertion of Registration data
	public void generateRegiserQuery(String firstName, String lastName,String userName, String password){
		this.Query = "INSERT INTO tbl_customer(firstName,lastName,userName,password) VALUES('"+ firstName+"','" + lastName +"','"+userName+"','"+password+"')";
	}

	//Method setting Query for Insertion of Items by Admin
	public void generateAddItemsQuery(String[] itemRow){
		this.Query = "INSERT INTO tbl_items(itemName,description,price,quantity) VALUES("+ "'" + itemRow[0] +"','" + itemRow[1] +"',"+Integer.parseInt(itemRow[2])+","+Double.parseDouble(itemRow[3])+")";
	}

	//Method setting Query for selecting customers
	public void generateDeleteUserSelectQuery(int customerId){
		this.Query = "SELECT * FROM tbl_customer where customer_id = "+customerId;
	}	

	//Method setting Query for deletion of customer
	public void generateDeleteUsersQuery(int customerId){
		this.Query = "DELETE FROM tbl_customer where customer_id = "+ customerId;
	}
	
	//Method setting Query for deletion of items
	public void generateDeleteItemsQuery(int itemId){
		this.Query = "DELETE FROM tbl_items where item_id = "+ itemId;
	}

	//Method setting Query for insertion into admin table
	public void generateAddAdminQuery(String[] adminRow){
		this.Query = "INSERT INTO tbl_admin(firstName,lastName,userName,password) VALUES("+ "'" + adminRow[0] +"','" + adminRow[1] +"','"+adminRow[2]+"','"+adminRow[3]+"')";
	}

	//Method setting Query for
	public void generateAddUserQuery(String[] userRow){
		this.Query ="INSERT INTO tbl_customer(firstName,lastName,userName,password) VALUES("+ "'" + userRow[0] +"','" + userRow[1] +"','"+userRow[2]+"','"+userRow[3]+"')";
	}

	//Method setting Query for getting items list
	public void generateBrowseQuery(){
		this.Query = "SELECT * FROM tbl_items";
	}

	//Method setting Query for selecting customers
	public void generateDisplayUsersQuery(){
		this.Query = "SELECT * FROM tbl_customer";
	}

	//Method setting Query for selecting items of cart
	public void generateDisplayCartQuery(int cartId){
		this.Query = "SELECT * FROM tbl_cartItems where cart_id ="+cartId;
	}

	//Method setting Query for for selecting given user
	public void generateUserLoginQuery(String userId, String password){
		this.Query = "SELECT c.customer_id, ct.cart_id FROM tbl_customer c JOIN tbl_cart ct ON c.customer_id = ct.customer_id where c.userName = '"+ userId + "' and c.password = '"+password+"'";
	}

	//Method setting Query for selecting given user
	public void generateAdminLoginQuery(String userId, String password){
		this.Query = "SELECT * FROM tbl_admin where userName = '"+ userId + "' and password = '"+password+"'";
	}

	//Method setting Query for selecting items list
	public void generateItemSelectQuery(int itemId){
		this.Query = "SELECT * FROM tbl_items where item_id = "+itemId;
	}
	
	//Method setting Query for updating description of item
	public void generateItemDescriptionUpdateQuery(String itemUpdate, int itemId){
		this.Query = "UPDATE tbl_items SET description = '"+itemUpdate+"' WHERE item_id ="+itemId;
	}
	
	//Method setting Query for updating quantity of item
	public void generateItemQuantityUpdateQuery(String itemUpdate, int itemId){
		this.Query = "UPDATE tbl_items SET quantity = "+itemUpdate+" WHERE item_id ="+itemId;
	}

	//Method setting Query for updating price of item
	public void generateItemPriceUpdateQuery(String itemUpdate, int itemId){
		this.Query = "UPDATE tbl_items SET price = "+itemUpdate+" WHERE item_id ="+itemId;
	}

	//Method setting Query for selecting the quantity of the item
	public void generateQuantitySelectQuery(int itemId){
		this.Query = "SELECT Quantity FROM tbl_items WHERE item_id = "+ itemId;
	}

	//Method setting Query for inserting data into cart
	public void generateCartInsetyQuery(int cartId,int itemId,int quantity){
		this.Query = "INSERT INTO tbl_cartItems(cart_id,item_id,quantity) VALUES ("+cartId+","+itemId+","+quantity+")";
	}

	//Method setting Query for selecting items and quantity for the given cart
	public void generateCartItemsSelectQuery(int cartId){
		this.Query = "SELECT item_id, quantity FROM tbl_cartItems WHERE cart_id = "+ cartId;
	}
	
	//Method setting Query for updating items table with the quantity
	public void generateUpdateQuantityQuery(int availableQuantity, int quantity, int itemId){
		this.Query = "UPDATE tbl_items SET Quantity = "+ (availableQuantity - quantity) + " WHERE item_id = "+itemId ;
	}

	//Method setting Query for delete items from cart
	public void generateDeleteCartItems(int cartId){
		this.Query = "DELETE FROM tbl_cartItems WHERE cart_id = "+cartId;
	}
}