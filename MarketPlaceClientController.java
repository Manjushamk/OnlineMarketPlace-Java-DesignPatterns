// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala


// FIXED: This is how I fixed what was wrong.
// I have separated the framework functionality by including an additional controller 
// which implements the RMI client functionality


import java.rmi.Naming;
// Ryan: Do you really need everything in this package?
// Fixed: I have used only the Arraylist, Scanner classes from this package,
// So I have modified the import statement accordingly
import java.util.ArrayList;

//MarketPlaceClientController is a client side controller responsbile for RMI communication
public class MarketPlaceClientController {
	static private MarketPlace marketPlace;
	Session session;
	//Main Method for RMI Client
	public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
			String name = "//10.234.136.55:2526/MarketPlaceServer";
			// Attempt to locate the MarketPlace Server
			marketPlace = (MarketPlace) Naming.lookup(name);

			//Creating an instance of Entry View
			ClientEntryView entryPoint = new ClientEntryView();
			// Create new instance of a Front Controller
			FrontController frontController = new FrontController();

			// Dispatch request for respective views

			frontController.dispatchRequest(entryPoint.performAction());					
		} catch(Exception e){
			System.out.println("MarketPlace Client Exception: " +
				e.getMessage());
			e.printStackTrace();
		}

		System.exit(0);
	}


	//Method for interacting with the remote method for login check
	public boolean loginCheck(String request) {
		ClientEntryView loginView = new ClientEntryView();
		loginView.checkLogin();
		String id = loginView.getUserName();
		String password = loginView.getPassword();
		System.out.println("Login Checking");
		//exception handling
		try {
			if(request == "Admin"){
				return marketPlace.adminLogin(id, password, request);
			}
			else{
				return marketPlace.userLogin(id, password, request);
			}

		}
		catch(Exception e){
			System.out.println("MarketPlace Client login Exception: " +
				e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	//sessionLogin calls server side sessionLogin method
	public Session sessionLogin(String request){
		try{
			session = marketPlace.sessionLogin(request);
		}
		catch(Exception e){
			System.out.println("Session Creation Exception" +
				e.getMessage());
			e.printStackTrace();
		}
		return session;
	}

	//addItems calls server side add items for admin
	public String addItems( Session session, String[] itemRow){
		String value = "";
		try{
			value = marketPlace.addItems(session, itemRow);
		}
		catch(Exception e){
			System.out.println("Error in adding items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//addUser calls server side add user for admin role
	public String addUser( Session session, String[] userRow){
		String value = "";
		try{
			value = marketPlace.addUser(session, userRow);
		}
		catch(Exception e){
			System.out.println("Error in adding User" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//addAdmin calls server side addAdmin function for admin responsbile
	public String addAdmin( Session session, String[] AdminRow){
		String value = "";
		try{
			value = marketPlace.addAdmin(session, AdminRow);
		}
		catch(Exception e){
			System.out.println("Error in adding items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//purchase function that calls server side purchase function for user
	public ArrayList<String> purchase( Session session){
		ArrayList<String> value = new ArrayList<String>();
		try{
			value = marketPlace.purchase(session);
		}
		catch(Exception e){
			System.out.println("Error in purchasing items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}	

	//updateItems calls server side update items method
	public String updateItems(Session session,int itemId, int itemField, String itemUpdate){
		String value = "";
		try{
			value = marketPlace.updateItems(session,itemId,itemField,itemUpdate);
		}
		catch(Exception e){
			System.out.println("Error in update items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//deleteItems calls server side deletemthod for admin
	public String deleteItems(Session session, int itemId){
		String value = "";
		try{
			value = marketPlace.deleteItems(session, itemId);
		}
		catch(Exception e){
			System.out.println("Error in deleteItems items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//browse admin items server side method is called
	public ArrayList<String> browseAdminItems(Session session){
		ArrayList<String> value = new ArrayList<String>();
		try{
			value = marketPlace.browseAdminItems(session);
		}
		catch(Exception e){
			System.out.println("Error in adding items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//browse user items server side method is called
	public ArrayList<String> browseUserItems(Session session){
		ArrayList<String> value = new ArrayList<String>();
		try{
			value = marketPlace.browseUserItems(session);
		}
		catch(Exception e){
			System.out.println("Error in adding items" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//display user method onserver side for user is called
	public String displayUser(Session session){
		String value = "";
		try{
			value = marketPlace.displayUser(session);
		}
		catch(Exception e){
			System.out.println("Error in displaying " +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//Method calls server side register to enter the user details into the customer table
	public String registerUser(){
		ClientEntryView registerView = new ClientEntryView();
		registerView.registerUser();
		String value = "";
		String userName = registerView.getUserName();
		String firstName = registerView.getFirstName();
		String lastName = registerView.getLastName();
		String password = registerView.getPassword();
		System.out.println("Registering User");
		//exception handling
		try {	
			return marketPlace.register(firstName,lastName, userName, password);
		}
		catch(Exception e){
			System.out.println("MarketPlace Client registration Exception: " +
				e.getMessage());
			e.printStackTrace();
		}
		value = "Registration failed";
		return value;
	}

	//Display users List
	public ArrayList<String> displayUsersList(Session session){
		ArrayList<String> value = new ArrayList<String>();
		try{
			value = marketPlace.displayUsersList(session);
		}
		catch(Exception e){
			System.out.println("Error in displaying users" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}


	//Method calls server side removeUser for admin role
	public String removeUser(Session session, int customerId){
		String value = "";
		try{
			value = marketPlace.removeUser(session, customerId);
		}
		catch(Exception e){
			System.out.println("Error in deleting customer" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//function to call server side add items to cart method
	public String addItemsToCart(Session session, int itemId, int quantity){
		String value = "";
		try{
			value = marketPlace.addItemsToCart(session, itemId, quantity);
		}
		catch(Exception e){
			System.out.println("Error in adding items to cart" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;
	}

	//Method to call server side display cart
	public ArrayList<String> displayCart(Session session){
		ArrayList<String> value = new ArrayList<String>();
		try{
			value = marketPlace.displayCart(session);
		}
		catch(Exception e){
			System.out.println("Error in displaying cart" +
				e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

}