// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

//FrontController class is responsible for entry point, user authentication
//and dispatchinig to respective view
public class FrontController {
	//instance of Dispatcher class
	private Dispatcher dispatcher;
	private Session session;
	private MarketPlaceClientController clientControllerLogin;

	//constructor for the FrontController class
	public FrontController() {
		//intializing the dispatcher object
		dispatcher = new Dispatcher();
		clientControllerLogin = new MarketPlaceClientController();
	}

	//Sample authentication check
	private boolean isAuthenticUser(String request) {
		return clientControllerLogin.loginCheck(request); 
	}

	//method for dispatching the request to the Dispatcher class
	public void dispatchRequest(String request) {
		System.out.println("View : " + request);   

		// If the user has been authenticated - dispatch request
		if(isAuthenticUser(request)) {
			System.out.println(request + " authentication is successful.");
			session = clientControllerLogin.sessionLogin(request);
			dispatcher.dispatch(request,session);
		}	
		else {
			System.out.println(request + " authentication failed.");
		}
	}

}
