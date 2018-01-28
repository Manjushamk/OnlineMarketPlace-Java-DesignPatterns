// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

/**
 * This interface serves as the proxy between the View and the
 * Controller. The Controller must implement this method.
 *
 */

import java.rmi.Remote;

public interface MarketPlace extends Remote {
	 void login(String userId, String password) throws java.rmi.RemoteException;
}
