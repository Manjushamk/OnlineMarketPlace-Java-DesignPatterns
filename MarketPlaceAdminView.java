
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.util.Scanner;

public class MarketPlaceAdminView implements Admin{
	
	private String adminName;
	private String adminId;
	private String password;
	private Scanner userInput;
	
	//method for entering user login information
	public void enterLogin() {
		// Sample user login 
		System.out.println(" Admin login ");
		System.out.println("Enter Id: ");
		userInput = new Scanner(System.in);
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
	@Override
	public void browse() {
		System.out.println("Browsing Items displayed here");
	}


	//Method to be implented for Adding Items
	@Override
	public void update(){
		System.out.println("Update items");
	}
	
	//method to be implemented for deleting items
	@Override
	public void delete(){
		System.out.println("Deleting Items");
	}


	//method to be implemented for adding items
	@Override
	public void add(Session session){
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		clientControllerObj.browseItems(session);
		System.out.println("Adding items");
	}

	// Method o be implemented for Displaying User or Admin Profile
	@Override
	public int displayAdmin() {
		System.out.println("Displaying Admin Profile");
		System.out.println("Enter Action");
		System.out.println("1.Add Items");
		System.out.println("2.Delete Items");
		System.out.println("3.Update Items");
		System.out.println("4.Browse Items");
		userInput = new Scanner(System.in);
		int option = userInput.nextInt();
		return option;
	}
	
	//method for user registration - yet to be used
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
