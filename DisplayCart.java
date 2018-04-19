// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concrete class creation for add items to cart command that implements the User Actions Command interface
public class DisplayCart implements UserActions{
	private User user;
	private Session session;

	//BrowseItems constructor with admin and session object
	public DisplayCart(User user , Session session){
		this.user = user;
		this.session = session;
	}

	//execute method used in invoker
	@Override
	public void execute() {
		user.displayCart(session);
	}
}
