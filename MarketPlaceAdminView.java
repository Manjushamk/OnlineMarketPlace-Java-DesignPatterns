// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

// Ryan: Do you really need everything in this package?
// Fixed: I have used only the Arraylist, Scanner classes from this package,
// So I have modified the import statement accordingly
import java.util.Scanner;
import java.util.ArrayList;

//MarketPlaceAdminView implements from Admin interface and contains all admin related 
//functions implementation
public class MarketPlaceAdminView implements Admin{

	private Scanner userInput;
	private MarketPlaceClientController clientControllerObj;
	
	public MarketPlaceAdminView(){
		clientControllerObj = new MarketPlaceClientController();
	}

	//method to be implemented for adding items
	@Override
	public void add(Session session){
		System.out.println("******************************************** ADDING ITEMS ********************************************");
		userInput = new Scanner(System.in);
		String[] itemRow = {"","","","",""};
		System.out.println("Enter Item Name: ");
		itemRow[0] = userInput.nextLine();
		System.out.println("Enter Item Description: ");
		itemRow[1] = userInput.nextLine();
		System.out.println("Enter Item Price: ");
		itemRow[2] = userInput.nextLine();
		System.out.println("Enter Item Quantity: ");
		itemRow[3] = userInput.nextLine();
		// string array itemRow has the data for item to be added to the database table
		System.out.println(clientControllerObj.addItems(session,itemRow));
		System.out.println("*******************************************************************************************************");
	}


	// Method to be implemented for Browsing items
	@Override
	public void browse(Session session) {
		System.out.println("******************************************** ITEMS DISPLAYED HERE ********************************************");
		System.out.println("ITEM ID  ITEM NAME \t\t DESCRIPTION  \t\t\tQUANTITY  \tPRICE");
		ArrayList<String> items = clientControllerObj.browseAdminItems(session);
		for(int i = 0; i< items.size(); i++){
			System.out.println(items.get(i));
		}
		System.out.println("**************************************************************************************************************");
	}

	//Method to be implented for Adding Items
	@Override
	public void update(Session session){
		ArrayList<String> items = clientControllerObj.browseAdminItems(session);
		System.out.println("*******************************************************************************************************");
		System.out.println("ITEM ID  ITEM NAME \t\t DESCRIPTION  \t\t\tQUANTITY  \tPRICE");
		for(int i = 0; i< items.size(); i++){
			System.out.println(items.get(i));
		}
		String itemUpdate = "";
		System.out.println("Enter Item Id from above list: ");
		userInput = new Scanner(System.in);
		int itemId = userInput.nextInt();
		System.out.println("*******************************************************************************************************");
		System.out.println("Enter update action 1, 2 or 3");
		System.out.println("1. Update item Description");
		System.out.println("2. Update item Price");
		System.out.println("3. Update item Quantity");
		int itemField = userInput.nextInt();
		System.out.println("Enter Update Value :");
		Scanner userUpdate = new Scanner(System.in);
		itemUpdate = userUpdate.nextLine();
		System.out.println(clientControllerObj.updateItems(session, itemId, itemField, itemUpdate));
		System.out.println("*******************************************************************************************************");
	}

	//method to be implemented for deleting items
	@Override
	public void delete(Session session){
		ArrayList<String> items = clientControllerObj.browseAdminItems(session);
		System.out.println("********************************************* DELETE ITEMS **********************************************");
		System.out.println("Item Id  Item Name \t\t Description  \t\t\tQuantity  \tPrice");
		for(int i = 0; i< items.size(); i++){
			System.out.println(items.get(i));
		}
		System.out.println("Enter Item Id from above list: ");
		userInput = new Scanner(System.in);
		int itemId = userInput.nextInt();
		System.out.println(clientControllerObj.deleteItems(session, itemId));
		System.out.println("*******************************************************************************************************");
	}


	// Method implemented for Displaying Admin Profile
	@Override
	public int displayAdmin() {
		System.out.println("Displaying Admin Profile");
		System.out.println("For exit enter anything other than 1,2,3,4,5,6,7");
		System.out.println("1.Add Items");
		System.out.println("2.Delete Items");
		System.out.println("3.Update Items");
		System.out.println("4.Browse Items");
		System.out.println("5.Add Admin");
		System.out.println("6.Add Customer");
		System.out.println("7.Remove Customer");
		userInput = new Scanner(System.in);
		int option = userInput.nextInt();
		return option;
	}

	//method for adding admin and passing data to client controller
	@Override
	public void addAdmin(Session session) {
		System.out.println("******************************************* ADDING ADMIN **********************************************");
		System.out.println("Adding Admin");
		userInput = new Scanner(System.in);
		String[] AdminRow = {"","","",""};
		System.out.println("Enter First Name: ");
		AdminRow[0] = userInput.nextLine();
		System.out.println("Enter Last Name: ");
		AdminRow[1] = userInput.nextLine();
		System.out.println("Enter username: ");
		AdminRow[2] = userInput.nextLine();
		System.out.println("Enter password: ");
		AdminRow[3] = userInput.nextLine();
		// string array itemRow has the data for item to be added to the database table
		System.out.println(clientControllerObj.addAdmin(session,AdminRow));
		System.out.println("*******************************************************************************************************");

	}

	//method for adding user and passing data to client controller
	@Override
	public void addUser(Session session) {
		System.out.println("********************************************* ADD USER *************************************************");
		System.out.println("Adding User");
		userInput = new Scanner(System.in);
		String[] UserRow = {"","","",""};
		System.out.println("Enter First Name: ");
		UserRow[0] = userInput.nextLine();
		System.out.println("Enter Last Name: ");
		UserRow[1] = userInput.nextLine();
		System.out.println("Enter username: ");
		UserRow[2] = userInput.nextLine();
		System.out.println("Enter password: ");
		UserRow[3] = userInput.nextLine();
		// string array itemRow has the data for item to be added to the database table
		System.out.println(clientControllerObj.addUser(session,UserRow));
		System.out.println("*******************************************************************************************************");
	}

	//method for adding user and passing data to client controller
	@Override
	public void removeUser(Session session){
		System.out.println("******************************************** REMOVE USER ***********************************************");
		ArrayList<String> users = clientControllerObj.displayUsersList(session);
		System.out.println("Id \t User Name ");
		for(int i = 0; i< users.size(); i++){
			System.out.println(users.get(i));
		}
		System.out.println("Enter Customer Id to delete from above list: ");
		userInput = new Scanner(System.in);
		int customerId = userInput.nextInt();
		System.out.println(clientControllerObj.removeUser(session, customerId));
		System.out.println("*******************************************************************************************************");
	}
}