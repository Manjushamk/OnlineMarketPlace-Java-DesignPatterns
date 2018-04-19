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
import java.lang.reflect.Proxy;
import java.util.ArrayList;

// Creation of Controller class that implements the Remote Interface
public class MarketPlaceController extends UnicastRemoteObject implements MarketPlace{
	//The variable 'name' must include the location where the Server is going to be registered with RMI to run.
	private String name;
	private MarketPlaceModel model = null;

	//Constructor for the controller

	public MarketPlaceController(String name) throws RemoteException {
		super();
		this.name = name;
		model = new MarketPlaceModel();
	}

	//Implementation of remote method
	@Override
	public synchronized boolean adminLogin(String userId, String password, String type) throws RemoteException{
		return model.checkLogin(userId,password,type);
	}

	//overriding interface implemented userlogin method
	@Override
	public synchronized boolean userLogin(String userId, String password, String type) throws RemoteException{
		return model.checkLogin(userId,password,type);
	}

	//overriding interface implemented sessionLogin method
	@Override
	public Session sessionLogin(String type) throws RemoteException{
		Session session = new Session(type);
		return session;
	}

	//overriding interface implemented addItems method
	@Override
	public synchronized String addItems( Session session, String[] itemRow) throws java.rmi.RemoteException{
		return model.addItems(itemRow);
	}

	//overriding interface implemented addUsers method
	@Override
	public synchronized String addUser( Session session, String[] userRow) throws java.rmi.RemoteException{
		return model.addUser(userRow);
	}

	//overriding interface implemented addAdmin method
	@Override
	public synchronized String addAdmin( Session session, String[] adminRow) throws java.rmi.RemoteException{
		return model.addAdmin(adminRow);
	}

	//overriding interface implemented updateIems method
	@Override
	public String updateItems(Session session,int itemId, int itemField, String itemUpdate) throws java.rmi.RemoteException{
		return model.updateItems(itemId,itemField,itemUpdate);
	}

	//overriding interface implemented deleteItems method
	@Override
	public String deleteItems(Session session, int itemId) throws java.rmi.RemoteException{
		return model.deleteItems(itemId);
	}

	//overriding interface implemented removeUser method
	@Override
	public String removeUser(Session session, int customerId) throws java.rmi.RemoteException{
		return model.removeUser(customerId);
	}

	//overriding interface implemented browseItems method
	@Override
	public synchronized ArrayList<String> browseAdminItems(Session session) throws java.rmi.RemoteException{
		return model.browseItems();
	}

	//overriding interface implemented browseItems method
	@Override
	public synchronized ArrayList<String> displayUsersList(Session session) throws java.rmi.RemoteException{
		return model.displayUsersList();
	}

	//overriding interface implemented browseItems method
	@Override
	public synchronized ArrayList<String> browseUserItems(Session session) throws java.rmi.RemoteException{
		return model.browseItems();
	}


	// method to be implemented for user Registration
	public String register(String firstName,String lastName,String userName, String password) throws RemoteException{
		return model.registerUser(firstName,lastName,userName,password);
	}

	//displayUser method implementaion
	@Override
	public synchronized String displayUser(Session session) throws java.rmi.RemoteException{
		return model.displayUser();
	}

	//Purchase method for User
	@Override
	public synchronized String purchase(Session session, int ItemId, int quantity) throws java.rmi.RemoteException{
		return model.purchase(ItemId,quantity);
	}


	public static void main(String args[]) throws RemoteException{
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());

		try {
			System.out.println("Creating a Server Connection!");

			// Location of Server
			String name = "//10.234.136.55:2526/MarketPlaceServer";

			//creating a dynamic proxy
			MarketPlace reflection_proxy = (MarketPlace) Proxy.newProxyInstance(MarketPlace.class.getClassLoader(),
					new Class<?>[] {MarketPlace.class},
					new AuthorizationInvocationHandler(new MarketPlaceController(name)));

			// Create a new instance of a Market Place Controller.
			// MarketPlaceController controller = new MarketPlaceController(name);

			System.out.println("MarketPlaceModel: binding it to name: " + name);

			// Binds the Controller(with remote method implementation) to the RMI Service.
			Naming.rebind(name, reflection_proxy);

			System.out.println("Market Place Server is Ready!");
		} 
		catch (Exception e){
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
