// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MarketPlaceController extends UnicastRemoteObject implements MarketPlace{
	private String name;
	
	public MarketPlaceController(String name) throws RemoteException {
		super();
		this.name = name;
	}

	public synchronized void login(String userId, String password) throws RemoteException{
		MarketPlaceModel model = new MarketPlaceModel();
		model.checkLogin(userId,password);
	}
}
