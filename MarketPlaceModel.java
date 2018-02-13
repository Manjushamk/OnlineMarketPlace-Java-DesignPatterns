// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

// Creation of Model for the MarketPlace Application, the database connections should be implemented here
public class MarketPlaceModel {
	private String userName;
	private String userId;
	private String adminId;
	private String password;
	private String adminPassword;
	
	public MarketPlaceModel() {
		//default login
		userName = "Manjusha";
		userId = "mkottala";
		password = "mkottala";
		adminId = "manju";
		adminPassword = "manju";
	}
	
	public String registerUser(String userName, String userId, String password){
		this.userName = userName;
		this.userId = userId;
		this.password = password;
		return "User Registration Successful " + this.userName ;
	}
	
	public String[] getItemList(String itemType) {
		//generally should return items from database of the given type
		//some sample return
		return  new String[] { "item1", "item2" , "item3" };
	}
	
	//method to verify login information
	public boolean checkLogin(String userId, String password, String type) {
		if(type.equalsIgnoreCase("User")) {
			if(this.userId.equals(userId) && this.password.equals(password)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(this.adminId.equals(userId) && this.adminPassword.equals(password)) {
				return true; 
			}
			else {
				return false;
		}
		}
		
	}
	

}

