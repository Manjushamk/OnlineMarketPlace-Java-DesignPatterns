// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

// Factory class implementation for Admin extending abstract factory class
public class AdminFactory extends AbstractFactory{

	//Overriding the methods for selection of the concrete class

	@Override
	public User getUserView(String type) {
		// Ryan: Do you really want to return null here?

		// FIXED: In the AdminFactory class, the getUserView will never be used. When we extend the AbstractFactory class,
		// its abstract methods should be implemented here. So, as it never be user I am returning null for the User Object.
		return null;
	}

	@Override
	Admin getAdminView(String type) {
		if(type.equalsIgnoreCase("admin")) {
			return new MarketPlaceAdminView();
		}
		return null;
	}
}
