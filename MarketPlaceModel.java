// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.sql.*;

// Creation of Model for the MarketPlace Application, the database connections should be implemented here
public class MarketPlaceModel {
	private String userName;
	private String userId;
	private String adminId;
	private String password;
	private String adminPassword;
	private String[] items = new String[20];

	//constructor with user detials init
	public MarketPlaceModel() {
		//default login
		userName = "Manjusha";
		userId = "mkottala";
		password = "mkottala";
		adminId = "manju";
		adminPassword = "manju";
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
		DBConnection DatabaseObj = new DBConnection();
		Connection conn = DatabaseObj.connect();
		if(conn != null) {
			System.out.println("Connection Established");
		}
		return "User Profile Display from Server";
	}

	//server side logic for adding items
	public String addItems(){
		return "Called server addItems Method";
	}
	
	//server side logic for deleting items
	public String deleteItems(){
		return "Called server delete Items Method";
	}

	//server side logic for updating items
	public String updateItems(){
		return "Called server Update Items Method";
	}
	
	//server side logic for browsing items
	public String[] browseAdminItems(){
		return getItemList();
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

