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

// Creation of Controller class that implements the Remote Interface
public class MarketPlaceController extends UnicastRemoteObject implements MarketPlace{
	//The variable 'name' must include the location where the Server is going to be registered with RMI to run.
	private String name;
	
	//Constructor for the controller
	public MarketPlaceController(String name) throws RemoteException {
		super();
		this.name = name;
	}

	//Implementation of remote method
	public synchronized String login(String userId, String password) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.checkLogin(userId,password);
	}
	
	// method to be implemented for user Registration
	public synchronized String register(String userName, String userId, String password) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.registerUser(userName,userId,password);
	}
	
	// method to be implemented to get the items for browsing in the view
	public synchronized String[] getItems(String itemType) throws RemoteException{
		// Have to implement to get the items from the database of the specified type
		MarketPlaceModel model = new MarketPlaceModel();
		return model.getItemList(itemType);
	}
	
	public static void main(String args[]) throws RemoteException{
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());
		
		try {
			System.out.println("Creating a Server Connection!");
			
			// Location of Server
			String name = "//tesla.cs.iupui.edu:2518/home/mkottala/OOAD/Assignment1/MarketPlaceServer";
			
			// Create a new instance of a Market Place Controller.
			MarketPlaceController controller = new MarketPlaceController(name);
			
			System.out.println("MarketPlaceModel: binding it to name: " + name);
			
			// Binds the Controller(with remote method implementation) to the RMI Service.
			Naming.rebind(name, controller);
			
			System.out.println("Market Place Server is Ready!");
		} 
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
