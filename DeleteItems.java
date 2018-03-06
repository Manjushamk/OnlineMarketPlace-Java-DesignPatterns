// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for deleting items command that implements the Actions Command interface
public class DeleteItems implements Actions{
   private Admin admin;
   private Session session;
   
   //DeleteItems constructor with admin object and session object
   public DeleteItems(Admin admin , Session session){
      this.admin = admin;
      this.session = session;
   }

   //execute method used in invoker
   @Override
   public void execute() {
      admin.delete(session);
   }
}
