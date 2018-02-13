// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

public class UpdateItems implements Actions{
   private Admin admin;

   public UpdateItems(Admin admin ){
      this.admin = admin;
   }

   public void execute() {
      admin.update();
}
}