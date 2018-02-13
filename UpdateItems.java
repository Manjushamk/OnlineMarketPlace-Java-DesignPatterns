// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for updating items command that implements the Actions Command interface
public class UpdateItems implements Actions{
   private Admin admin;

   public UpdateItems(Admin admin ){
      this.admin = admin;
   }

   //execute method used in invoker
   public void execute() {
      admin.update();
}
}