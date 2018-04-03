// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for browsing items command that implements the User Actions Command interface
public class BrowseUserItems implements UserActions{
	private User user;
	private Session session;

	//BrowseItems constructor with admin and session object
	public BrowseUserItems(User user , Session session){
		this.user = user;
		this.session = session;
	}

	//execute method used in invoker
	@Override
	public void execute() {
		user.browse(session);
	}
}
