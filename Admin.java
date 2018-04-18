// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

//interface for admin methods
public interface Admin {

	//admin commands appended with session object
	public int displayAdmin();
	public void add(Session session);
	public void update(Session session);
	public void browse(Session session);
	public void delete(Session session);
	public void addAdmin(Session session);
	public void addUser(Session session);
}
