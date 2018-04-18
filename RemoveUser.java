// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concrete class creation for removing users command that implements the Actions Command interface
public class RemoveUser implements Actions{
	private Admin admin;
	private Session session;

	//constructor with admin object and session object
	public RemoveUser(Admin adminObj , Session session){
		this.admin = adminObj;
		this.session = session;
	}

	//execute method used in invoker
	@Override
	public void execute() {
		admin.removeUser(session);
	}
}

