// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

public class AdminFactory extends AbstractFactory{

	@Override
	public User getUserView(String type) {
		return null;
	}

	@Override
	Admin getAdminView(String type) {
		if(type.equalsIgnoreCase("user")) {
			return new MarketPlaceAdminView();
		}
		return null;
	}
}
