// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

public class UserFactory extends AbstractFactory{
	
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
