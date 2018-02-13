
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala


// Creation of abstract factory class
public abstract class AbstractFactory {
	abstract User getUserView(String type);
	abstract Admin getAdminView(String type);
}
