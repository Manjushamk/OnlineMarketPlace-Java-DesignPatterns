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
	@Override
	public synchronized boolean adminLogin(Session session, String userId, String password, String type) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.checkLogin(userId,password,type);
	}

	@Override
	public synchronized boolean userLogin(Session session, String userId, String password, String type) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.checkLogin(userId,password,type);
	}

	@Override
	public Session sessionLogin(String type) throws RemoteException{
		Session session = new Session(type);
		return session;
	}


	@Override
	public String addItems(Session session) throws java.rmi.RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.addItems();
	}

	@Override
	public String updateItems(Session session) throws java.rmi.RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.updateItems();
	}

	@Override
	public String deleteItems(Session session) throws java.rmi.RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.deleteItems();
	}

	@Override
	public String[] browseAdminItems(Session session) throws java.rmi.RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.browseAdminItems();
	}


	// method to be implemented for user Registration
	public synchronized String register(String userName, String userId, String password) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		return model.registerUser(userName,userId,password);
	}


	public static void main(String args[]) throws RemoteException{
		// Set the RMI Security Manager...
		System.setSecurityManager(new SecurityManager());

		try {
			System.out.println("Creating a Server Connection!");

			// Location of Server
			String name = "//tesla.cs.iupui.edu:2525/MarketPlaceServer";

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
