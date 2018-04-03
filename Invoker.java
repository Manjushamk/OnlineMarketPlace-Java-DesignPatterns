// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.util.ArrayList;
import java.util.List;

//Invoker class for invoking the commands
public class Invoker {

	//Action list for Admin
	private List<Actions> actionsList = new ArrayList<Actions>(); 

	// method to get the commands
	public void getActions(Actions action) {
		actionsList.add(action);
	}

	// method to execute the commands from the list
	public void performActions() {
		for (Actions action : actionsList) {
			action.execute();
		}
		actionsList.clear();
	}


	//Action list for User
	private List<UserActions> userActionsList = new ArrayList<UserActions>(); 

	// method to get the commands
	public void getUserActions(UserActions userAction) {
		userActionsList.add(userAction);
	}

	// method to execute the commands from the list
	public void performUserActions() {
		for (UserActions userAction : userActionsList) {
			userAction.execute();
		}
		userActionsList.clear();
	}
}
