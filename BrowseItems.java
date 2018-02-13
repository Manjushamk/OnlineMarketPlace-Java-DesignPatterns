// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for browsing items command that implements the Actions Command interface
public class BrowseItems implements Actions{
   private Admin admin;

   public BrowseItems(Admin admin ){
      this.admin = admin;
   }

   //execute method used in invoker
   public void execute() {
      admin.browse();
}
}
