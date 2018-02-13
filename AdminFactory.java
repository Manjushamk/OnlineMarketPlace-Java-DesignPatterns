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
