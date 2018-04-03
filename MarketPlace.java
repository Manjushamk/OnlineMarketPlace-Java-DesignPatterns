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
import java.util.*;

public interface MarketPlace extends Remote {
	//interfaces that MarketPlaceView uses remotely

	//interface methods used for user or customer login
	boolean userLogin(String userId, String password, String type) throws java.rmi.RemoteException;
	boolean adminLogin(String userId, String password, String type) throws java.rmi.RemoteException;

	//admin related functions with role based access
	//add update delete and browse items
	@RoleAnnotations("Admin")
	public String addItems( Session session, String[] itemRow) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String updateItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public String deleteItems(Session session) throws java.rmi.RemoteException;

	@RoleAnnotations("Admin")
	public ArrayList<String> browseAdminItems(Session session) throws java.rmi.RemoteException;

	//user related function with role based access
	@RoleAnnotations("User")
	public String displayUser(Session session) throws java.rmi.RemoteException;

	// user browse items function role based access
	@RoleAnnotations("User")
	public ArrayList<String> browseUserItems(Session session) throws java.rmi.RemoteException;

	//user purchase items function with user role
	@RoleAnnotations("User")
	public String purchase(Session session, int ItemId, int quantity) throws java.rmi.RemoteException;

	//session creation method
	Session sessionLogin(String request) throws java.rmi.RemoteException;

	//registers a user
	String register(String userName, String userId, String password) throws java.rmi.RemoteException;
}
