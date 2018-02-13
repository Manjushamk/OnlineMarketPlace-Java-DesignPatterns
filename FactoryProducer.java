// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

public class FactoryProducer {
   public static AbstractFactory getSelectedView(String type){
   
      if(type.equalsIgnoreCase("user")){
         return new UserFactory();
         
      }else if(type.equalsIgnoreCase("admin")){
         return new AdminFactory();
      }
      
      return null;
   }
}
