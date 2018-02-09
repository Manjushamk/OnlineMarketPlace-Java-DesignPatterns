import java.rmi.Naming;
import java.util.Scanner;

// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala


public class MarketPlaceClientController {
	
	//Main Method for RMI Client
		public static void main(String args[]){
			// RMI Security Manager
			System.setSecurityManager(new SecurityManager());
			try{
				String name = "//tesla.cs.iupui.edu:2525/MarketPlaceServer";
				// Attempt to locate the MarketPlace Server
				MarketPlace marketPlace = (MarketPlace) Naming.lookup(name);
				
				MarketPlaceView view1 = new MarketPlaceView();
				System.out.println("Enter Action : ");
				System.out.println("1. Login");
				System.out.println("2. Register User");
				Scanner userInput = new Scanner(System.in);
				int option = userInput.nextInt();
				if (option == 1) {
					//user login
					view1.enterLogin();
					String userId = view1.getUserId();
					String password = view1.getPassword();
					System.out.println(marketPlace.login(userId,password));
				}
				else if (option == 2 ){
					//user registration
					view1.registration();
					String userId = view1.getUserId();
					String password = view1.getPassword();
					String userName = view1.getUserName();
					System.out.println(marketPlace.register(userName, userId, password));
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
