// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

// Factory class implementation for User extending abstract factory class
public class UserFactory extends AbstractFactory{
	//Overriding the methods for selection of the concrete class
	
	@Override
	public User getUserView(String type) {
		if(type.equalsIgnoreCase("user")) {
			return new MarketPlaceUserView();
		}
		return null;
	}

	@Override
	Admin getAdminView(String type) {
		return null;
	}
}
