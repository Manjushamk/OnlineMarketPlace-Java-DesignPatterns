// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

public class DeleteItems implements Actions{
   private Admin admin;

   public DeleteItems(Admin admin ){
      this.admin = admin;
   }

   public void execute() {
      admin.delete();
}
}
