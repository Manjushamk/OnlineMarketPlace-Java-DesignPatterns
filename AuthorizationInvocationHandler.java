// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//importing libraries for reflection and object passing
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//AuthorizationInvocationHandler implements from Invocation Handler and Serializable to over ride
//invoke method and code with appropriate functionality
public class AuthorizationInvocationHandler implements InvocationHandler, Serializable {
	private Object objectImpl;

	//constructor
	public AuthorizationInvocationHandler(Object impl) {
		this.objectImpl = impl;
	}

	//overiding invoke method which can invoke an appropriate method
	//if the annotation from interface and session both matches
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//metadata checking using java reflections
		if (method.isAnnotationPresent(RoleAnnotations.class)) {
			RoleAnnotations test = method.getAnnotation(RoleAnnotations.class);
			Session session = (Session) args[0];

			if (session.getRoleType().equals(test.value())) {
				return method.invoke(objectImpl, args);
			} else {
				throw new AuthorizationException(method.getName());
			}
		} else {
			return method.invoke(objectImpl, args);
		}   
	}
}
