import java.util.Scanner;
import java.rmi.Naming;
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

public class MarketPlaceView {
	

	public static void main(String args[]){
/*		
		MarketPlaceView view = new MarketPlaceView();
		MarketPlaceModel model = new MarketPlaceModel();
		MarketPlaceController controller = new MarketPlaceController();*/
		
		System.out.println(" User login ");
		System.out.println("Enter User Id: ");
		Scanner userInput = new Scanner(System.in);
		String userId = userInput.nextLine();
		System.out.println("Enter Password: ");
		String password = userInput.nextLine();
		userInput.close();
		
		// RMI Security Manager
		System.setSecurityManager(new SecurityManager());
		
		int id;

		try{
			String name = "//tesla.cs.iupui.edu:2518/home/mkottala/OOAD/Assignment1/MarketPlaceServer";
			// Attempt to locate the MarketPlaceServer...
			MarketPlace marketPlace = (MarketPlace) Naming.lookup(name);
			marketPlace.login(userId,password);	
		} catch(Exception e){
			System.out.println("MarketPlace Client Exception: " +
			e.getMessage());
			e.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public void browseItems() {
		System.out.println("Browsing Items displayed here");
	}
	
	public void displayProfile() {
		System.out.println("Displaying User/Admin Profile");
	}
}
