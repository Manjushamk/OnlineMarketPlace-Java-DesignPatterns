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
	
	
	
	//Main Method for RMI Client
	public static void main(String args[]){
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		try{
			String name = "//tesla.cs.iupui.edu:2518/home/mkottala/OOAD/Assignment1/MarketPlaceServer";
			// Attempt to locate the MarketPlace Server
			MarketPlace marketPlace = (MarketPlace) Naming.lookup(name);
			
			MarketPlaceView view1 = new MarketPlaceView();
			System.out.println("Enter Action : ");
			System.out.println("1. Login");
			System.out.println("2. Register User");
			Scanner userInput = new Scanner(System.in);
			int option = userInput.nextInt();
			if (option == 1) {
				view1.enterLogin();
				System.out.println(marketPlace.login(view1.userId,view1.password));
			}
			else if (option == 2 ){
				view1.registration();
				System.out.println(marketPlace.register(view1.userName, view1.userId,view1.password));
			}
			userInput.close();		
				
		} catch(Exception e){
			System.out.println("MarketPlace Client Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	

}
