// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

import java.util.Scanner;


//Generic view for the point of entry such as Login
public class ClientEntryView {
	private String userId;
	private String password;
	private Scanner userInput;
	
	//Method for selecting the view 
	public String performAction() {
		userInput = new Scanner(System.in);
		System.out.println("Enter Action as either 1 or 2:  ");
		System.out.println("1. User");
		System.out.println("2. Admin");
		int option = userInput.nextInt();	
		if (option == 1) {
		    return "User";
		}
		else {
		    return "Admin";
		}
		
		
	}
	
	
	//method for input of id, password and setting them.
	public void checkLogin() {
		userInput = new Scanner(System.in);
		System.out.println("Enter Id: ");
		userId = userInput.nextLine();
		userInput = new Scanner(System.in);
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
	}
	
	
	//get methods
	public String getPassword() {
		return this.password;
	}
	
	public String getId() {
		return this.userId;
	}
	
}
