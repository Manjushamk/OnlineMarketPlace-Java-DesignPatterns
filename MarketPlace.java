// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

/**
 * This interface serves as the proxy between the View and the
 * Controller,Model . The MarketPlaceController implements this method.
 *
 */

import java.rmi.Remote;

public interface MarketPlace extends Remote {
	//interfaces that MarketPlaceView uses remotely
	 String login(String userId, String password) throws java.rmi.RemoteException;
	 String register(String userName, String userId, String password) throws java.rmi.RemoteException;
	 String[] getItems(String itemType) throws java.rmi.RemoteException;
}
