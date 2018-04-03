// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.sql.*;
import java.util.*;

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
	public String registerUser(String userName, String userId, String password){
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		return "User Registration Successful " + this.userName ;
	}

	//server side login for displaying a user
	public String displayUser(){ 
		return "User Profile Display from Server";
	}

	//server side logic for adding items
	public String addItems(String[] itemRow){
		String addItemQuery = "INSERT INTO Items VALUES("+ Integer.parseInt(itemRow[0])+",'" + itemRow[1] +"','"+ itemRow[2]+"',"+Integer.parseInt(itemRow[3])+","+Double.parseDouble(itemRow[4])+")";
		if(conn != null) {
			statement = null;
			try {
				statement = conn.createStatement();
				try {
					statement.executeUpdate(addItemQuery);
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
			statement = conn.createStatement();
			results = statement.executeQuery("SELECT Quantity FROM Items WHERE itemId = "+ itemId);
			//available_quantity = results.getInt(1);
			while(results.next()){
				available_quantity = results.getInt("Quantity");
			}
			if(available_quantity >0 && available_quantity >= quantity){
				String purchaseUpdate = "UPDATE Items SET Quantity = "+ (available_quantity - quantity) + " WHERE itemId = "+itemId ;
				statement = null;
				try{
					statement = conn.createStatement();
					try{
						statement.executeUpdate(purchaseUpdate);
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
	public ArrayList browseItems(){
		ArrayList itemList = new ArrayList();
		String rowDate;
		int i = 0;
		try{
			statement = conn.createStatement(); 
			results = statement.executeQuery("SELECT * FROM Items");
			while(results.next()){
				rowDate = results.getInt(1)+ " \t " + results.getString(2) + " \t \t" + results.getString(3) + "\t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				itemList.add(i,rowDate);
				i++;
			}
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
			if(this.userId.equals(userId) && this.password.equals(password)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(this.adminId.equals(userId) && this.adminPassword.equals(password)) {
				return true; 
			}
			else {
				return false;
			}
		}

	}


}