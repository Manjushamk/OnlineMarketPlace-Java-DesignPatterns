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
import java.util.*;

//MarketPlaceClientController is a client side controller responsbile for RMI communication
public class MarketPlaceClientController {
	static private MarketPlace marketPlace;
	Session session;
	//Main Method for RMI Client
	public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
			String name = "//10.234.136.55:2525/MarketPlaceServer";
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
		String id = loginView.getId();
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
		return true;
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
	public String addItems(Session session){
		String value = "";
		try{
			value = marketPlace.addItems(session);
		}
		catch(Exception e){
			System.out.println("Error in adding items" +
					e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//updateItems calls server side update items method
	public String updateItems(Session session){
		String value = "";
		try{
			value = marketPlace.updateItems(session);
		}
		catch(Exception e){
			System.out.println("Error in update items" +
					e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//deleteItems calls server side deletemthod for admin
	public String deleteItems(Session session){
		String value = "";
		try{
			value = marketPlace.deleteItems(session);
		}
		catch(Exception e){
			System.out.println("Error in deleteItems items" +
					e.getMessage());
			e.printStackTrace();
		}
		return value;	
	}

	//browse admin items server side method is called
	public ArrayList browseAdminItems(Session session){
		ArrayList value = new ArrayList();
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
	public ArrayList browseUserItems(Session session){
		ArrayList value = new ArrayList();
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

}


