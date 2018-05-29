package userModel;

import org.jdom2.Element;

public class Administrator extends User{
	protected int administratorId;
	
	public boolean Addmin(UserDB userDB, String adminLogin, String newAdminlogin,int adminID, String firstname, String surname, String pwd) {
		  
		Element admins = new Element("Administrators");
		   Element admin = new Element("Administrator");
		     Element login = new Element("login");
		     login.setText("su");
		     Element fname = new Element("firstname");
		     fname.setText("su");
		     Element sname = new Element("surname");
		     sname.setText("su");
		     Element pwd = new Element("pwd");
		     pwd.setText("superUser");
		     Element adminId = new Element("adminId");
		     adminId.setText("0");
		    admin.addContent(login);admin.addContent(fname);admin.addContent(sname);admin.addContent(pwd); admin.addContent(adminId); 
		   admins.addContent(admin); 
		  rootElt.addContent(admins);
		return false;
	}
	
	public boolean AddStudent(UserDB userDB,String adminLogin, String newStudentLogin,int studentID, String firstname, String surname, String pwd) {
		return false;
	}
	
	public boolean AddTeacher(UserDB userDB,String adminLogin, String newteacherLogin,int teacherID, String firstname, String surname, String pwd) {
		return false;
	}
	
	public boolean removeUser(UserDB userDB,String adminLogin, String userLogin){
		return false;
	}
	
	public boolean removeGroup(UserDB userDB,String adminLogin, int groupId) {
		return false;
	}
	
	public boolean associateStudToGroup(UserDB userDB ,String adminLogin, String studentLogin, int groupId){
		return false;
	}
	
	public void bookroom() {}
	
	public int getadmId() {
		return this.administratorId;
	} 

}
