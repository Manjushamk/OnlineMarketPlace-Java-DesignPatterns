// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala
//

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class MarketPlaceModel {
	private int itemId;
	private int itemQuantity;
	private String userName;
	private String userId;
	private String adminName;
	private String adminId;
	private String password;
	
	public MarketPlaceModel() {
		//default login
		userName = "Manjusha";
		userId = "mkottala";
		password = "mkottala";
	}
	
	public void registration(String userName, String userId, String password){
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		System.out.println("User Registration Successful " + this.userName );
	}
	
	public void checkLogin(String userId, String password) {
		if(this.userId.equals(userId) && this.password.equals(password)) {
			System.out.println("Successful login");
		}
		else {
			System.out.println("login failed");
		}
	}
	
	public void updateItemQuantity() {
		
	}
	
	public void removeItems() {
		
	}
	
	public void addItems() {
		
	}
	
	public static void main(String args[]) throws RemoteException{
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		
		try {
			System.out.println("Creating a Server Connection!");
			
			// Location of Server
			String name = "//tesla.cs.iupui.edu:2518/home/mkottala/OOAD/Assignment1/MarketPlaceServer";
			
			// Create a new instance of a Market Place Server.
			MarketPlaceController controller = new MarketPlaceController(name);
			
			System.out.println("MarketPlaceModel: binding it to name: " + name);
			
			// Binds the BankServer to the RMI Service.
			Naming.rebind(name, controller);
			
			System.out.println("Market Place Server is Ready!");
		} 
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}

