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

	public void dispatch(String request, Session session) {
		// Condition for selection of view
		if(request.equalsIgnoreCase("User")) {
			User userObj = userView.getUserView(request);
			int choice = 1;
			BrowseUserItems browseUserItems = new BrowseUserItems(userObj,session);
			Purchase purchase = new Purchase(userObj,session);
			Invoker invokerObj = new Invoker();
			while(choice == 1||choice ==2 ){
				choice = userObj.displayUser(session);
				switch(choice) {
				case 1 : invokerObj.getUserActions(browseUserItems);
				break; 
				case 2 : invokerObj.getUserActions(purchase);
				break; 
				default : System.out.println("Exiting");
				}
				//perform one of the above selected options
				invokerObj.performUserActions();
			}
		} 
		else {
			//this is entered when user requests for admin view
			Admin adminObj = adminView.getAdminView(request);

			//creating instances for new concrete classes
			AddItems addItems = new AddItems(adminObj,session);
			DeleteItems deleteItems = new DeleteItems(adminObj,session);
			UpdateItems updateItems = new UpdateItems(adminObj,session);
			BrowseItems browseItems = new BrowseItems(adminObj,session);
			AddAdminConcrete addAdmin = new AddAdminConcrete(adminObj,session);
			AddUserConcrete addUser = new AddUserConcrete(adminObj,session);
			Invoker invokerObj = new Invoker();
			int choice = 1;
			while(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5 || choice == 6){
				choice = adminObj.displayAdmin();
				//admin can select his commands from the below choices
				switch(choice) {
				case 1 : invokerObj.getActions(addItems);
				break;
				case 2 : invokerObj.getActions(deleteItems);
				break;
				case 3 : invokerObj.getActions(updateItems);
				break;
				case 4 : invokerObj.getActions(browseItems);
				break;  
				case 5 : invokerObj.getActions(addAdmin);
				break;
				case 6 : invokerObj.getActions(addUser);
				break;
				default : System.out.println("Exiting");
				}
				//perform one of the above selected options
				invokerObj.performActions();
			}

		}	
	}
}
