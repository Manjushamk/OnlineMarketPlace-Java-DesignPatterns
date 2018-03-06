// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//creation of factory generation class
public class FactoryProducer {
	public static AbstractFactory getSelectedView(String type){
		if(type.equalsIgnoreCase("User")){
			return new UserFactory();  
		}else if(type.equalsIgnoreCase("Admin")){
			return new AdminFactory();
		}
		return null;
	}
}
