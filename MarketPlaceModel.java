// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Creation of Model for the MarketPlace Application, the database connections should be implemented here
public class MarketPlaceModel {
	private int customerId;
	private int cartId;
	private DBConnection dbConnObj;
	private ResultSet results = null;

	//constructor with user details init
	public MarketPlaceModel() {
		//default login
		dbConnObj = new DBConnection();
	}


	//model logic for registration of customer
	public String registerUser(String firstName, String lastName,String userName, String password){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateRegiserQuery(firstName,lastName,userName,password);
			results = dbConnObj.registrationInsert();
			try{
				if(results.next() != false){
					this.customerId = results.getInt(1);
					//selecting the cart id respective to the current customer id 
					ResultSet cartResult = dbConnObj.getCartId(customerId);
					if (cartResult.next() != false) {
						this.cartId = cartResult.getInt(1);
					}	
				}
				return "Registration Success";
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
			dbConnObj.generateItemSelectQuery(itemId);
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


	//server side logic for deleting Users
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
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateBrowseQuery();
			results = dbConnObj.executeSelectQueries();
			try{
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
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return itemList;
	}

	//server side logic for displaying Users List
	public ArrayList<String> displayUsersList(){
		//list of rows of result in strings format
		ArrayList<String> userList = new ArrayList<String>();
		String rowData;
		int i = 0;
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateDisplayUsersQuery();
			results = dbConnObj.executeSelectQueries();
			try{
				while(results.next()){
				//converting the Query result rows to string with formatting
					rowData = results.getInt(1)+ " \t " + results.getString(4);

				//adding of the row as string to the string array list
					userList.add(i,rowData);
					i++;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return userList;
	}


	//server side logic for browsing items
	public ArrayList<String> displayCart(){
		ArrayList<String> cartList = new ArrayList<String>();
		String rowData;
		int i = 0;
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateDisplayCartQuery(this.cartId);
			results = dbConnObj.executeSelectQueries();
			try{
				while(results.next()){
					rowData = results.getInt(1)+ " \t  " + results.getInt(2) + " \t   " + results.getInt(3);
					//adding of the row as string to the string array list
					cartList.add(i,rowData);
					i++;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();				
			}
		}
		return cartList;
	}


	//method to verify login information
	public boolean checkLogin(String userId, String password, String type) {
		if (dbConnObj.checkConnection()) {
			if(type.equalsIgnoreCase("User")) {
				dbConnObj.generateUserLoginQuery(userId,password);
				results = dbConnObj.executeSelectQueries();
				try{
					if(results.next() != false){
						this.customerId = results.getInt(1);
						this.cartId = results.getInt(2);
						return true;
					}
					else{
						return false;
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				dbConnObj.generateAdminLoginQuery(userId,password);
				results = dbConnObj.executeSelectQueries();
				try{
					if(results.next() != false){
						return true;
					}
					else{
						return false;
					}
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return false;	
	}


	//server side logic for updating items
	public String updateItems(int itemId, int itemField, String itemUpdate){
		if (dbConnObj.checkConnection()) {
			dbConnObj.generateItemSelectQuery(itemId);
			synchronized(this) {
				results = dbConnObj.executeSelectQueries();
				try{
					if (results.next()!= false) {
						if(itemField == 1){
							dbConnObj.generateItemDescriptionUpdateQuery(itemUpdate,itemId);
							if(dbConnObj.executeUpdateQueries()){
								return "Updated Item Description";
							}
						}
						else if(itemField == 2){
							try{
								double price = Double.parseDouble(itemUpdate);
								dbConnObj.generateItemPriceUpdateQuery(itemUpdate,itemId);
								//try catch for checking number format exception
								if(dbConnObj.executeUpdateQueries()){
									return "Updated Item Description";
								}
								
							}
							catch(NumberFormatException e){
								return "Enter a valid number";
							}
						}
						else if(itemField == 3){
							try{
								int quantity = Integer.parseInt(itemUpdate);
								dbConnObj.generateItemQuantityUpdateQuery(itemUpdate,itemId);
								//try catch for checking number format exception
								if(dbConnObj.executeUpdateQueries()){
									return "Updated Item Description";
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
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return "Error in Updating item, Enter valid option";
	}

	//Add items to cart
	public String addItemsToCart(int itemId, int quantity){
		int available_quantity = 0;
		if (dbConnObj.checkConnection()) {
			synchronized(this) {
				dbConnObj.generateQuantitySelectQuery(itemId);
				results = dbConnObj.executeSelectQueries();
				try{
					while(results.next()){
						available_quantity = results.getInt("Quantity");
					}
					if(available_quantity >0 && available_quantity >= quantity){
						dbConnObj.generateCartInsetyQuery(this.cartId,itemId,quantity);
						if (dbConnObj.executeUpdateQueries()) {
							return "Added item Successfully";
						}
					}
					else{
						return "Item out of Stock";
					}
				}
				catch (SQLException e) {
					e.printStackTrace();	
				}
			}
		}
		return "Error in Adding item to cart";
	}


	//server side logic for purchase item of user role
	public ArrayList<String> purchase(){
		ArrayList<String> returnList= new ArrayList<String>();
		String statusOfItem = "";
		ResultSet resultItems = null;
		int i = 0;
		if (dbConnObj.checkConnection()) {
			ArrayList<Integer> itemsList = new ArrayList<Integer>();
			ArrayList<Integer> quantityList = new ArrayList<Integer>();
			dbConnObj.generateCartItemsSelectQuery(this.cartId);
			results = dbConnObj.executeSelectQueries();
			try{
				if(results.next() != false){
					results.beforeFirst();
					while(results.next()){
						itemsList.add(i, results.getInt(1));
						quantityList.add(i, results.getInt(2));
						i++;
					}
					synchronized(this){
						for(i = 0; i< itemsList.size(); i++){
							int availableQuantity = 0;
							int quantity = quantityList.get(i);
							int itemId = itemsList.get(i);
							//Selecting the availble quantity of the given item id
							dbConnObj.generateQuantitySelectQuery(itemId);
							resultItems = dbConnObj.executeSelectQueries();
							while(resultItems.next()){
								availableQuantity = resultItems.getInt("Quantity");
							}
							if(availableQuantity >0 && availableQuantity >= quantity){
								//Query for updating the Quantity of the item for purchase
								dbConnObj.generateUpdateQuantityQuery(availableQuantity,quantity,itemId);
								if(dbConnObj.executeUpdateQueries()){
									statusOfItem = "Item Id : "+itemId+ " Successfully Purchased";
									returnList.add(i,statusOfItem);
								}
							}
							else{
								statusOfItem = "Item Id : "+itemId+ " is out of Stock";
								returnList.add(i,statusOfItem);
							}
						}
					}
					synchronized(this) {	
						//Query for deleting emptying the cart after purchase
						dbConnObj.generateDeleteCartItems(this.cartId);
						if (dbConnObj.executeUpdateQueries()) {
							return returnList;
						}
						else{
							returnList.clear();
							returnList.add(0,"Error in deleting items from cart");
							return returnList;
						}
					}
				}
				else{
					returnList.add(0,"Cart is empty");
					return returnList;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();	
			}
		}

		returnList.add(0,"Error in Purchasing items");
		return returnList;
	}

}