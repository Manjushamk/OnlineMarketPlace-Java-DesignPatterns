// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for adding items command that implements the Actions Command interface
public class AddItems implements Actions{
   private Admin admin;
   private Session session;

   public AddItems(Admin adminObj , Session session){
      this.admin = adminObj;
      this.session = session;
   }

   //execute method used in invoker
   @Override
   public void execute() {
      admin.add(session);
   }
}
