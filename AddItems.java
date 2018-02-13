// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

public class AddItems implements Actions{
   private Admin admin;

   public AddItems(Admin adminObj ){
      this.admin = adminObj;
   }

   public void execute() {
      admin.add();
   }
}
