// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

//interface for admin methods
public interface Admin {
	public int displayAdmin();
	public void add(Session session);
	public void update(Session session);
	public void browse(Session session);
	public void delete(Session session);
}
