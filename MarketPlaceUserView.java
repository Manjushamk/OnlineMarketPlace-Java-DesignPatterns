// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

// importing Scanner for input operation
import java.util.ArrayList;
import java.util.Scanner;
//importing Naming class for obtaining reference to remote object
import java.rmi.Naming;


// View class of the MVC pattern and it acts as the client for the Java RMI
public class MarketPlaceUserView implements User{
	private String userName;
	private String userId;
	private String password;
	private Session session;

	//method for entering user login information

	//Get methods for userId, UserName and Password
	public String getUserId() {
		return this.userId;
	}

	//get methods
	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}


	// Method to be implemented for Browsing items
	public void browseItems() {
		System.out.println("Browsing Items displayed here");
	}


	// Method o be implemented for Displaying User or Admin Profile
	@Override
	public int displayUser(Session session) {
		Scanner userInput = new Scanner(System.in);
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Displaying User Profile");
		System.out.println(clientControllerObj.displayUser(session));
		System.out.println("Enter Action");
		System.out.println("1.Purchase Items");
		System.out.println("2.Delete Items");
		userInput = new Scanner(System.in);
		int option = userInput.nextInt();
		return option;
	}


	//method for user registration
	public void registration() {
		Scanner userInput = new Scanner(System.in);
		System.out.println(" Enter User Registration Details:  ");
		System.out.println("User Name: ");
		userName = userInput.nextLine();
		System.out.println("User Id: ");
		userId = userInput.nextLine();
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
		userInput.close();
	}
	
	// Method to be implemented for Browsing items
	@Override
	public void browse(Session session) {
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Browsing Items displayed here");
		ArrayList items = clientControllerObj.browseUserItems(session);
		for(int i = 0; i< items.size(); i++){
			System.out.println(items.get(i));
		}
	}

	// Ryan: This is a violation of separation of concerns as we are mixing 
	// View logic with "framework" functionality. Instead this needs to be 
	// separated using a Controller.

	// FIXED: This is how I fixed what was wrong.
	// I have separated the framework functionality by including an additional controller 
	// which implements the RMI client functionality

}
