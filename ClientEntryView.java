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
		System.out.println("Enter Action as either 1, 2 or 3:  ");
		System.out.println("1. User");
		System.out.println("2. Admin");
		System.out.println("3. Customer Registration");
		int option = userInput.nextInt();	
		//options checking for displaying the requested functionality
		if (option == 1) {
			return "User";
		}
		else if (option == 2) {
			return "Admin";
		}
		else if (option == 3){
			return "Register";
		}
		else{
			System.out.println("Not a valid option");
			System.exit(0);
		}
		return "";
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

	//method for entering the user information for registration
	public void registerUser(){
		userInput = new Scanner(System.in);
		System.out.println("Enter first Name: ");
		firstName = userInput.nextLine();
		System.out.println("Enter last Name: ");
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

	//returns user user Name
	public String getUserName() {
		return this.userName;
	}

	//returns user First Name
	public String getFirstName() {
		return this.firstName;
	}

	//returns user Last name
	public String getLastName() {
		return this.lastName;
	}

}
