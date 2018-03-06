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
	boolean userLogin(String userId, String password, String type) throws java.rmi.RemoteException;

	boolean adminLogin(String userId, String password, String type) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String addItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String updateItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String deleteItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String[] browseAdminItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("User")
	public String displayUser(Session session) throws java.rmi.RemoteException;

	Session sessionLogin(String request) throws java.rmi.RemoteException;
	String register(String userName, String userId, String password) throws java.rmi.RemoteException;
}
