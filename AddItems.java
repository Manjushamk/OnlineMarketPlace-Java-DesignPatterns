// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for adding items command that implements the Actions Command interface
public class AddItems implements Actions{
   private Admin admin;

   public AddItems(Admin adminObj ){
      this.admin = adminObj;
   }

   //execute method used in invoker
   public void execute() {
      admin.add();
   }
}
