// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

// Creation of Model for the MarketPlace Application, the database connections should be implemented here
public class MarketPlaceModel {
	private String userName;
	private String userId;
	private String adminId;
	private String password;
	private String adminPassword;
	private String[] items = new String[20];
	private DBConnection dbConnObj;
	private ResultSet results = null;
	private Connection conn = null;
	private Statement statement = null;

	//constructor with user details init
	public MarketPlaceModel() {
		//default login
		userName = "Manjusha";
		userId = "mkottala";
		password = "mkottala";
		adminId = "manju";
		adminPassword = "manju";
		dbConnObj = new DBConnection();
		conn = dbConnObj.connect();
		items = new String[]{"Book","Pen","Cycle","Camera"};

	}

	//server side login for registering a user
	public String registerUser(String firstName, String lastName,String userName, String password){
		//Query for Insertion of the new items into the data base
		String registerUser = "INSERT INTO tbl_customer(firstName,lastName,userName,password) VALUES('"+ firstName+"','" + lastName +"','"+userName+"','"+password+"')";
		if(conn != null) {
			statement = null;
			try {
				//statemt creation to run the sql query
				statement = conn.createStatement();
				try {
					//Execution of Query
					statement.executeUpdate(registerUser);
					//statement close
					statement.close();
					return "Registration Success";
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			catch(SQLException e) {
				System.out.println("Error in Statement Creation");
			}
		}
		return "Error in Registration";
	}

	//server side login for displaying a user
	public String displayUser(){ 
		return "User Profile Display from Server";
	}

	//server side logic for adding items
	public String addItems(String[] itemRow){
		//Query for Insertion of the new items into the data base
		String addItemQuery = "INSERT INTO Items VALUES("+ Integer.parseInt(itemRow[0])+",'" + itemRow[1] +"',"+Integer.parseInt(itemRow[2])+","+Double.parseDouble(itemRow[3])+")";
		if(conn != null) {
			statement = null;
			try {
				//statemt creation to run the sql query
				statement = conn.createStatement();
				try {
					//Execution of Query
					statement.executeUpdate(addItemQuery);
					//statement close
					statement.close();
					return "Item has been added";
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
			catch(SQLException e) {
				System.out.println("Error in Statement Creation");
			}
		}
		return "Error in Adding Items";
	}

	//server side logic for deleting items
	public String deleteItems(){
		return "Called server delete Items Method";
	}

	//server side logic for updating items
	public String updateItems(){
		return "Called server Update Items Method";
	}

	//server side logic for purchase item of user role
	public String purchase(int itemId, int quantity){
		int available_quantity = 0;
		statement = null;
		try{
			//statement creation
			statement = conn.createStatement();
			//statement execution and storing it in the result set
			results = statement.executeQuery("SELECT Quantity FROM Items WHERE itemId = "+ itemId);
			//Accessing the elements of the ResultSet results
			while(results.next()){
				available_quantity = results.getInt("Quantity");
			}
			// closing the ResultSet results
			results.close();
			if(available_quantity >0 && available_quantity >= quantity){
				//Query for updating the Quantity of the item for purchase
				String purchaseUpdate = "UPDATE Items SET Quantity = "+ (available_quantity - quantity) + " WHERE itemId = "+itemId ;
				statement = null;
				try{
					statement = conn.createStatement();
					try{
						//Query execution
						statement.executeUpdate(purchaseUpdate);
						//Closing the statement
						statement.close();
						return "Item Purchase Successful";
					}
					catch(SQLException e){
						e.printStackTrace();
					}
				}
				catch(SQLException e){
					e.printStackTrace();
				}
			}
			else{
				return "Out of Stock or enter an existing item Id";
			}

		}
		catch(SQLException e){
			e.printStackTrace();;
		}

		return "purchase item";
	}

	//server side logic for browsing items
	public ArrayList<String> browseItems(){
		//list of rows of result in strings format
		ArrayList<String> itemList = new ArrayList<String>();
		String rowDate;
		int i = 0;
		try{
			//Statement creation
			statement = conn.createStatement(); 
			//executing the Query and results contain the output of the Query as a ResultSet Object
			results = statement.executeQuery("SELECT * FROM tbl_items");
			while(results.next()){
				if(results.getString(2).length() <6){
					//converting the Query result rows to string with formatting
					rowDate = results.getInt(1)+ " \t " + results.getString(2) + "\t \t " + results.getString(3) +"\t \t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				else{
					rowDate = results.getInt(1)+ " \t " + results.getString(2) + " \t \t " + results.getString(3) + " \t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				//adding of the row as string to the string array list
				itemList.add(i,rowDate);
				System.out.println(rowDate);
				i++;
			}
			// closing Statement and ResultSet objects statement and results
			statement.close();
			results.close();
		}
		catch (SQLException e1){
			System.out.println("Error while executing browse query");
		}
		return itemList;
	}

	//server side logic for getting items list
	public String[] getItemList() {
		return this.items;
	}

	//method to verify login information
	public boolean checkLogin(String userId, String password, String type) {
		if(type.equalsIgnoreCase("User")) {
			try{
				//Statement creations
				statement = conn.createStatement(); 
				//executing the Query and results contain the output of the Query as a ResultSet Object
				results = statement.executeQuery("SELECT * FROM tbl_customer where userName = '"+ userId + "' and password = '"+password+"'");
				// return true if login is successful
				if(results.next() != false){
					return true;
				}
				else{
					return false;
				}
			}		
			catch (SQLException e1){
			System.out.println("Error while while checking user login");
		}

		}
		else {
			try{
				//Statement creations
				statement = conn.createStatement(); 
				//executing the Query and results contain the output of the Query as a ResultSet Object
				results = statement.executeQuery("SELECT * FROM tbl_admin where userName = '"+ userId + "' and password = '"+password+"'");
				// return true if admin login is successful
				if(results.next() != false){
					return true;
				}
				else{
					return false;
				}
			}		
			catch (SQLException e1){
			System.out.println("Error while while checking admin login");
		}
		}
		return false;

	}
}