// Honor Pledge:
//
// I pledge that I have neither given nor 
// received any help on this assignment.
//
//mkottala

//Concreate class creation for browsing items command that implements the Actions Command interface
public class BrowseItems implements Actions{
  private Admin admin;
  private Session session;
  
  //BrowseItems constructor with admin and session object
  public BrowseItems(Admin admin , Session session){
    this.admin = admin;
    this.session = session;
  }

  //execute method used in invoker
  @Override
  public void execute() {
    admin.browse(session);
  }
}
