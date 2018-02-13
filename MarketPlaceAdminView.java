import java.util.Scanner;

// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala


public class MarketPlaceAdminView implements Admin{
	
	private String adminName;
	private String adminId;
	private String password;
	
	//method for entering user login information
	public void enterLogin() {
		// Sample user login 
		System.out.println(" Admin login ");
		System.out.println("Enter Id: ");
		Scanner userInput = new Scanner(System.in);
		adminId = userInput.nextLine();
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
		userInput.close();
	}
	
	//Get methods for adminId, adminName and Password
	public String getadminId() {
		return this.adminId;
	}
	
	public String getadminName() {
		return this.adminName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	
	// Method to be implemented for Browsing items
	public void browseItems() {
		System.out.println("Browsing Items displayed here");
	}
	
	
	// Method o be implemented for Displaying User or Admin Profile
	public void displayAdmin() {
		System.out.println("Displaying Admin Profile");
	}
	
	//method for user registration
	public void registration() {
		Scanner userInput = new Scanner(System.in);
		System.out.println(" Enter Admin Registration Details:  ");
		System.out.println("Admin Name: ");
		adminName = userInput.nextLine();
		System.out.println("Id: ");
		adminId = userInput.nextLine();
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
		userInput.close();
	}
}
