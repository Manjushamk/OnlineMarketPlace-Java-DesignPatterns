// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

import java.util.Scanner;


//Generic view for the point of entry such as Login
public class ClientEntryView {
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private Scanner userInput;

	//Method for selecting the view based on input
	public String performAction() {
		userInput = new Scanner(System.in);
		System.out.println("Enter Action as either 1 or 2:  ");
		System.out.println("1. User");
		System.out.println("2. Admin");
		System.out.println("3. Customer Registration");
		int option = userInput.nextInt();	
		if (option == 1) {
			return "User";
		}
		else if (option == 2) {
			return "Admin";
		}
		else{
			return "Register";
		}
	}


	//method for input of id, password and setting them.
	public void checkLogin() {
		userInput = new Scanner(System.in);
		System.out.println("Enter username: ");
		userName = userInput.nextLine();
		userInput = new Scanner(System.in);
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
	}

	public void registerUser(){
		userInput = new Scanner(System.in);
		System.out.println("Enter firstName: ");
		firstName = userInput.nextLine();
		System.out.println("Enter lastName: ");
		lastName = userInput.nextLine();
		System.out.println("Enter username: ");
		userName = userInput.nextLine();
		userInput = new Scanner(System.in);
		System.out.println("Enter Password: ");
		password = userInput.nextLine();
	}


	//get methods
	public String getPassword() {
		return this.password;
	}

	//returns user Id
	public String getUserName() {
		return this.userName;
	}

	//returns user Id
	public String getFirstName() {
		return this.firstName;
	}

	//returns user Id
	public String getLastName() {
		return this.lastName;
	}

}
