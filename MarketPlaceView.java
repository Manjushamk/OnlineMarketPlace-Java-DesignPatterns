// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

// importing Scanner for input operation
import java.util.Scanner;
//importing Naming class for obtaining reference to remote object
import java.rmi.Naming;


// View class of the MVC pattern and it acts as the client for the Java RMI
public class MarketPlaceView {
	private String userName;
	private String userId;
	private String password;
	
	//method for entering user login information
	public void enterLogin() {
		// Sample user login 
		System.out.println(" User login ");
		System.out.println("Enter User Id: ");
		Scanner userInput = new Scanner(System.in);
		userId = userInput.nextLine();
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
		userInput.close();
	}
	
	//Get methods for userId, UserName and Password
	public String getUserId() {
		return this.userId;
	}
	
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
	public void displayProfile() {
		System.out.println("Displaying User/Admin Profile");
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
	
	// Ryan: This is a violation of separation of concerns as we are mixing 
	// View logic with "framework" functionality. Instead this needs to be 
	// separated using a Controller.
	

}
