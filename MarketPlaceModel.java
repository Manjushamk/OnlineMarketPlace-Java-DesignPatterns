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
	private int customerId;
	private int cartId;
	private String[] items = new String[20];
	private DBConnection dbConnObj;
	private ResultSet results = null;
	private Connection conn = null;
	private Statement statement = null;

	//constructor with user details init
	public MarketPlaceModel() {
		//default login
		dbConnObj = new DBConnection();
		conn = dbConnObj.connect();
	}


	//model logic for regidtration of customer
	public String registerUser(String firstName, String lastName,String userName, String password){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateRegiserQuery(firstName,lastName,userName,password);
			results = dbConnObj.registrationInsert();
			try{
				if(results.next() != false){
					this.customerId = results.getInt(1);
					this.userName = userName;
					//seleting the cart id respective to the current customer id 
					ResultSet cartResult = dbConnObj.getCartId(results.getInt(1));
					if (cartResult.next() != false) {
						this.cartId = cartResult.getInt(1);
					}	
				}
				return "Registration is successful";
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Registration failed";
	}


	//server side login for displaying a user
	public String displayUser(){ 
		return "User Profile Display from Server";
	}

	//server side logic for adding items
	public String addItems(String[] itemRow){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateAddItemsQuery(itemRow);
			if(dbConnObj.executeUpdateQueries()){
				return "item has been added";
			}
		}
		return "Error in Adding Items";
	}

	//server side logic for deleting items
	public String deleteItems(int itemId){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateDeleteItemsSelectQuery(itemId);
			results = dbConnObj.executeSelectQueries();
			try{
				if (results.next()!= false) {
					synchronized(this) {
						dbConnObj.generateDeleteItemsQuery(itemId);
						if(dbConnObj.executeUpdateQueries()){
							return "Deletion of Item successful";
						}
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Error in deleting Items";
	}


	//server side logic for deleting items
	public String removeUser(int customerId){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateDeleteUserSelectQuery(customerId);
			results = dbConnObj.executeSelectQueries();
			try{
				if (results.next()!= false) {
					synchronized(this) {
						dbConnObj.generateDeleteUsersQuery(customerId);
						if(dbConnObj.executeUpdateQueries()){
							return "Remove User successful";
						}
					}
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Error in deleting Items";
	}

	//server side logic for adding admins
	public String addAdmin(String[] adminRow){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateAddAdminQuery(adminRow);
			synchronized(this) {
				if(dbConnObj.executeUpdateQueries()){
					return "Admin has been added";
				}
			}
		}
		return "Error in Adding Admin";
	}

	//server side logic for adding Users
	public String addUser(String[] userRow){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateAddUserQuery(userRow);
			synchronized(this) {
				if(dbConnObj.executeUpdateQueries()){
					return "Customer has been added";
				}
			}

		}
		return "Error in Adding Customer";
	}



	//server side logic for browsing items
	public ArrayList<String> browseItems(){
		//list of rows of result in strings format
		ArrayList<String> itemList = new ArrayList<String>();
		String rowData;
		int i = 0;
		try{
			//Statement creation
			statement = conn.createStatement(); 
			//executing the Query and results contain the output of the Query as a ResultSet Object
			results = statement.executeQuery("SELECT * FROM tbl_items");
			while(results.next()){
				//formatting and adding to arraylist
				if(results.getString(2).length() <6 && results.getString(3).length() <6){
					//converting the Query result rows to string with formatting
					rowData = results.getInt(1)+ " \t " + results.getString(2) + "\t \t \t " + results.getString(3) +"\t \t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				else if(results.getString(2).length() <6 ){
					rowData = results.getInt(1)+ " \t " + results.getString(2) + "\t \t \t " + results.getString(3) +"\t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				else if(results.getString(3).length() <6){
					rowData = results.getInt(1)+ " \t " + results.getString(2) + "\t \t " + results.getString(3) +"\t \t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				else{
					rowData = results.getInt(1)+ " \t " + results.getString(2) + " \t \t " + results.getString(3) + " \t \t \t" + results.getInt(4) + " \t\t" + results.getDouble(5);
				}
				//adding of the row as string to the string array list
				itemList.add(i,rowData);
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


	//server side logic for displaying Users List
	public ArrayList<String> displayUsersList(){
		//list of rows of result in strings format
		ArrayList<String> userList = new ArrayList<String>();
		String rowData;
		int i = 0;
		try{
			//Statement creation
			statement = conn.createStatement(); 
			//executing the Query and results contain the output of the Query as a ResultSet Object
			results = statement.executeQuery("SELECT * FROM tbl_customer");
			while(results.next()){
				//converting the Query result rows to string with formatting
				rowData = results.getInt(1)+ " \t " + results.getString(4);

				//adding of the row as string to the string array list
				userList.add(i,rowData);
				i++;
			}
			// closing Statement and ResultSet objects statement and results
			statement.close();
			results.close();
		}
		catch (SQLException e1){
			System.out.println("Error while getting customers list");
		}
		return userList;
	}

	//server side logic for browsing items
	public ArrayList<String> displayCart(){
		ArrayList<String> cartList = new ArrayList<String>();
		if(conn != null){		

			//list of rows of result in strings format
			String rowData;
			int i = 0;
			try{
				//Statement creation
				statement = conn.createStatement(); 
				//executing the Query and results contain the output of the Query as a ResultSet Object
				results = statement.executeQuery("SELECT * FROM tbl_cartItems where cart_id ="+this.cartId);
				while(results.next()){
					rowData = results.getInt(1)+ " \t  " + results.getInt(2) + " \t   " + results.getInt(3);
					//adding of the row as string to the string array list
					cartList.add(i,rowData);
					i++;
				}
				// closing Statement and ResultSet objects statement and results
				statement.close();
				results.close();
			}
			catch (SQLException e1){
				System.out.println("Error while executing displaying cart");
			}

		}

		return cartList;
	}


	//method to verify login information
	public boolean checkLogin(String userId, String password, String type) {
		if(type.equalsIgnoreCase("User")) {
			try{
				//Statement creations
				statement = conn.createStatement(); 
				//executing the Query and results contain the output of the Query as a ResultSet Object
				results = statement.executeQuery("SELECT c.customer_id, ct.cart_id FROM tbl_customer c JOIN tbl_cart ct ON c.customer_id = ct.customer_id where c.userName = '"+ userId + "' and c.password = '"+password+"'");
				// return true if login is successful
				if(results.next() != false){
					this.customerId = results.getInt(1);
					this.cartId = results.getInt(2);
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


	//server side logic for updating items
	public String updateItems(int itemId, int itemField, String itemUpdate){
		if(conn != null) {
			statement = null;
			try {
				//statemt creation to run the sql query
				statement = conn.createStatement();
				//Synchronization the block so only one session can access this block for updating items
				synchronized(this) {
					try {
						//Query for checking if there is a nay data for entered itemId
						results = statement.executeQuery("SELECT * FROM tbl_items where item_id = "+itemId);
						if (results.next()!= false) {
							if(itemField == 1){
								try{
									// query for updating description
									statement.executeUpdate("UPDATE tbl_items SET description = '"+itemUpdate+"' WHERE item_id ="+itemId);
									return "Updated Item Description";
								}
								catch(SQLException e) {
									e.printStackTrace();
								}	
							}
							else if(itemField == 2){
								try{
									double price = Double.parseDouble(itemUpdate);
									//try catch for checking number format exception
									try{
										//query for updating price
										statement.executeUpdate("UPDATE tbl_items SET price = "+itemUpdate+" WHERE item_id ="+itemId);
										return "Updated Item Description";
									}
									catch(SQLException e) {
										e.printStackTrace();
									}
								}
								catch(NumberFormatException e){
									return "Enter a valid number";
								}
							}
							else if(itemField == 3){
								try{
									int quantity = Integer.parseInt(itemUpdate);
									//try catch for checking number format exception
									try{
										//query for updating quantity
										statement.executeUpdate("UPDATE tbl_items SET quantity = "+itemUpdate+" WHERE item_id ="+itemId);
										return "Updated Item Description";
									}
									catch(SQLException e) {
										e.printStackTrace();
									}
								}
								catch(NumberFormatException e){
									return "Enter a valid positive Integer for quantity";
								}
							}
							else{
								return "Invalid option entered";
							}
						}
						else{
							return "Enter valid item Id";
						}
					}
					catch(SQLException e) {
						e.printStackTrace();
					}

				}
			}
			catch(SQLException e) {
				System.out.println("Error in Statement Creation");
			}
		}
		return "Error in Updating item, Enter valid option";
	}


	//Add items to cart
	public String addItemsToCart(int itemId, int quantity){
		int available_quantity = 0;
		if(conn != null) {
			statement = null;
			try {
				//statement creation to run the sql query
				statement = conn.createStatement();
				//Synchronization the block so only one session can access this block for adding items to cart
				synchronized(this) {
					try{
						//Query for checking if there is a nay data for entered itemId
						results = statement.executeQuery("SELECT Quantity FROM tbl_items WHERE item_id = "+ itemId);
						//Accessing the elements of the ResultSet results
						while(results.next()){
							available_quantity = results.getInt("Quantity");
						}
						// closing the ResultSet results
						results.close();
						if(available_quantity >0 && available_quantity >= quantity){
							try{
								//query for inserting the requested item details into cart
								String insertQuery = "INSERT INTO tbl_cartItems(cart_id,item_id,quantity) VALUES ("+this.cartId+","+itemId+","+quantity+")";
								statement.executeUpdate(insertQuery);
								return "Added item Successfully";
							}
							catch(SQLException e) {
								return "Error in adding item to cart";
							}
						}
						else{
							return "Item may be out of Stock";
						}

					}	
					catch(SQLException e) {
						System.out.println("Error in executing quantity checking Query");
					}
				}
			}
			catch(SQLException e) {
				System.out.println("Error in Statement Creation");
			}
		}
		return "Error in Adding item to cart";

	}

	//server side logic for purchase item of user role
	public ArrayList<String> purchase(){
		ArrayList<String> returnList= new ArrayList<String>();
		String statusOfItem = "";
		ResultSet resultItems = null;
		if (conn != null) {
			try{
				statement = null;
				int i = 0;
				statement = conn.createStatement();
				ArrayList<Integer> itemsList = new ArrayList<Integer>();
				ArrayList<Integer> quantityList = new ArrayList<Integer>();
				try{
					//statement execution and storing it in the result set
					results = statement.executeQuery("SELECT item_id, quantity FROM tbl_cartItems WHERE cart_id = "+ this.cartId);
					if(results.next() != false){
						results.beforeFirst();
						while(results.next()){
							itemsList.add(i, results.getInt(1));
							quantityList.add(i, results.getInt(2));
							i++;
						}
						//Synchronization the block so only one session can access this block for purchasing items
						synchronized(this) {
							for(i = 0; i< itemsList.size(); i++){
								int available_quantity = 0;
								int quantity = quantityList.get(i);
								int itemId = itemsList.get(i);
								//Selecting the availble quantity of the given item id
								resultItems = statement.executeQuery("SELECT Quantity FROM tbl_items WHERE item_id = "+ itemId);
								while(resultItems.next()){
									available_quantity = resultItems.getInt("Quantity");
								}
								if(available_quantity >0 && available_quantity >= quantity){
								//Query for updating the Quantity of the item for purchase
									String purchaseUpdate = "UPDATE tbl_items SET Quantity = "+ (available_quantity - quantity) + " WHERE item_id = "+itemId ;
									try{
									//Query execution
										statement.executeUpdate(purchaseUpdate);
									//Closing the statement
										statusOfItem = "Item Id : "+itemId+ " Successfully Purchased";
										returnList.add(i,statusOfItem);
									}
									catch(SQLException e){
										e.printStackTrace();
									}
								}
								else{
									statusOfItem = "Item Id : "+itemId+ " is out of Stock";
									returnList.add(i,statusOfItem);
								}
							}
						}

						synchronized(this) {	
							try {
								//Query for deleting emptying the cart after purchase
								statement.executeUpdate("DELETE FROM tbl_cartItems WHERE cart_id = "+this.cartId);
							}
							catch(Exception e) {
								System.out.println("Error in deleting items from cart ");
								e.printStackTrace();
							}
						}
						return returnList;
					}
					else{
						returnList.add(0,"Cart is empty");
						return returnList;
					}
				}
				catch(SQLException e){
					System.out.println("Error while executing Query ");
					e.printStackTrace();
				}
			}
			catch(SQLException e){
				System.out.println("Error while creating statement ");
				e.printStackTrace();
			}
		}
		returnList.add(0,"Error in Purchasing items");
		return returnList;
	}


}
