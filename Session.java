
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

import java.io.Serializable;

public class Session implements Serializable{

	String roleType;

	public Session(String roleType){
		this.roleType=roleType;
	}

	public getRoleType(){
		return roleType;
	}
}