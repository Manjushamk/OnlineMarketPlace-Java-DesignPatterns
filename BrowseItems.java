// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala


public class BrowseItems implements Actions{
   private Admin admin;

   public BrowseItems(Admin admin ){
      this.admin = admin;
   }

   public void execute() {
      admin.browse();
}
}
