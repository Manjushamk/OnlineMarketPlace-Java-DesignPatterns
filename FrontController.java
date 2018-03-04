// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala


public class FrontController {
	//instance of Dispatcher class
	private Dispatcher dispatcher;
	Session session;
	
	//constructor for the FrontController class
	public FrontController() {
		//intializing the dispatcher object
		dispatcher = new Dispatcher();
	}

	//Sample authentication check
	private boolean isAuthenticUser(String request) {
		MarketPlaceClientController clientControllerLogin = new MarketPlaceClientController();
		session = clientControllerLogin.sessionLogin(request);
		return clientControllerLogin.loginCheck(session,request); 
	}
	
	//method for dispatching the request to the Dispatcher class
	public void dispatchRequest(String request) {
		System.out.println("View : " + request);   
		
		// If the user has been authenticated - dispatch request
		if(isAuthenticUser(request)) {
			System.out.println(request + " authentication is successful.");
			dispatcher.dispatch(request, session);
	    }	
		else {
			System.out.println(request + " authentication failed.");
		}
	}
	
}
