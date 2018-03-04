// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
//mkottala

//user defined exception class for Authorization
public class AuthorizationException extends RunTimeException{
	public AuthorizationException(String functionName) {
		super("Access Denined to " + functionName);
	}
}