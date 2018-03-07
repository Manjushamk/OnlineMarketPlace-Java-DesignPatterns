
// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.util.Scanner;

//MarketPlaceAdminView implements from Admin interface and contains all admin related 
//functions implementation
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


	//method to be implemented for adding items
	@Override
	public void add(Session session){
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Adding items");
		System.out.println(clientControllerObj.addItems(session));
	}


	// Method to be implemented for Browsing items
	@Override
	public void browse(Session session) {
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Browsing Items displayed here");
		String[] items = clientControllerObj.browseAdminItems(session);
		for (String element: items) {
			System.out.println(element);
		}
	}

	//Method to be implented for Adding Items
	@Override
	public void update(Session session){
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Update items");
		System.out.println(clientControllerObj.updateItems(session));
	}

	//method to be implemented for deleting items
	@Override
	public void delete(Session session){
		MarketPlaceClientController clientControllerObj = new MarketPlaceClientController();
		System.out.println("Delete items");
		System.out.println(clientControllerObj.deleteItems(session));
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
