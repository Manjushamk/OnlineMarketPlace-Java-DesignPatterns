// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala


public class Dispatcher {
	//objects of the two views interacted with the front controller
	
	private AbstractFactory userView;
	private AbstractFactory adminView;

	// constructor for intializing the views
	public Dispatcher() {
		userView = FactoryProducer.getSelectedView("User");
		adminView = FactoryProducer.getSelectedView("Admin");
	}
	
	//dispatch method to dispatch the requested view
	
	public void dispatch(String request) {
		// Condition for selection of view
		if(request.equalsIgnoreCase("User")) {
			User userObj = userView.getUserView(request);
			userObj.displayUser();
	    } 
		else {
	    	Admin adminObj = adminView.getAdminView(request);
	    	System.out.println(adminObj);
	    	int choice = adminObj.displayAdmin();
	    	System.out.println(choice);
	    	AddItems addItems = new AddItems(adminObj);
	    	DeleteItems deleteItems = new DeleteItems(adminObj);
	    	UpdateItems updateItems = new UpdateItems(adminObj);
	    	BrowseItems browseItems = new BrowseItems(adminObj);
	    	Invoker invokerObj = new Invoker();
	    	switch(choice) {
	    	case 1 : invokerObj.getActions(addItems);
	    			 break;
	    	case 2 : invokerObj.getActions(deleteItems);
	    			 break;
	    	case 3 : invokerObj.getActions(updateItems);
			 		 break;
	    	case 4 : invokerObj.getActions(browseItems);
	 		 	     break;  
	    	default : System.out.println("Invalid Option");
	    	}
	    	invokerObj.performActions();
	    }	
	}
}