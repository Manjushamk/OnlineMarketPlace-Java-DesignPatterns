// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for updating items command that implements the Actions Command interface
public class UpdateItems implements Actions{
   private Admin admin;
   private Session session;
   
   //UpdateItems constructor intitlaizes admin and session object
   public UpdateItems(Admin admin , Session session){
      this.admin = admin;
      this.session = session;      
   }

   //execute method used in invoker
   @Override
   public void execute() {
      admin.update(session);
   }
}
