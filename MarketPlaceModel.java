// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

// importing classes for implementing JAVA RMI
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Creation of Model for the MarketPlace Application, the database connections should be implemented here
public class MarketPlaceModel {
	private int itemId;
	private int itemQuantity;
	private String userName;
	private String userId;
	private String adminName;
	private String adminId;
	private String password;
	private String itemType;
	
	public MarketPlaceModel() {
		//default login
		userName = "Manjusha";
		userId = "mkottala";
		password = "mkottala";
	}
	
	//Method to register user with details received from controller
	public String registerUser(String userName, String userId, String password){
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		return "User Registration Successful " + this.userName ;
	}
	
	public String[] getItemList(String itemType) {
		//generally should return items from database of the given type
		//some sample return
		return  new String[] { "item1", "item2" , "item3" };
	}
	
	//Method for Checking the user login from the view
	public String checkLogin(String userId, String password) {
		if(this.userId.equals(userId) && this.password.equals(password)) {
			return "Successful login";
		}
		else {
			return "login failed";
		}
	}
	
	public void updateItemQuantity() {
		//should implement logic for Updating Quantity
	}
	
	public void removeItems() {
		//should implement logic for removing items
	}
	
	public void addItems() {
		// should implement logic for adding items
	}
	
}

