// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

import java.util.ArrayList;
import java.util.List;

public class Invoker {
	private List<Actions> actionsList = new ArrayList<Actions>(); 
	
	public void getActions(Actions action) {
		actionsList.add(action);
	}
	
	public void performActions() {
		for (Actions action : actionsList) {
			action.execute();
		}
		actionsList.clear();
	}
}
