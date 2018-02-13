// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala


public class FrontController {
	//instance of Dispatcher class
	private Dispatcher dispatcher;
	
	//constructor for the FrontController class
	public FrontController() {
		//intializing the dispatcher object
		dispatcher = new Dispatcher();
	}

	//Sample authentication check
	private boolean isAuthenticUser(String request) {
		MarketPlaceClientController clientControllerLogin = new MarketPlaceClientController();
		return clientControllerLogin.loginCheck(request); 
	}
	
	//method for dispatching the request to the Dispatcher class
	public void dispatchRequest(String request) {
		System.out.println("View : " + request);   
		
		// If the user has been authenticated - dispatch request
		if(isAuthenticUser(request)) {
			System.out.println(request + " authentication is successful.");
			dispatcher.dispatch(request);
	    }	
		else {
			System.out.println(request + " authentication failed.");
		}
	}
	
}
