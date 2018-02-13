// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for deleting items command that implements the Actions Command interface
public class DeleteItems implements Actions{
   private Admin admin;

   public DeleteItems(Admin admin ){
      this.admin = admin;
   }

   //execute method used in invoker
   public void execute() {
      admin.delete();
}
}
