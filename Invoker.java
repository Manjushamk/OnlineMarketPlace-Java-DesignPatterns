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
}
