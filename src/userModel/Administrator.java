package userModel;

import org.jdom2.Element;

public class Administrator extends User{
	protected int administratorId;
	public Administrator(String adminLogin, int adminID, String firstname, String surname, String pwd) {
		super(adminLogin,firstname,surname,pwd);
		this.administratorId = adminID;	
	}
	public boolean Addadmin(UserDB userDB, String adminLogin, String newAdminlogin,int adminID, String firstname, String surname, String pwd) {
	  //  userDB.loadDB();
		Element rootElt = userDB.document.getRootElement();
		Element admins = new Element("Administrators");
		   Element admin = new Element("Administrator");
		     Element login = new Element("login");
		     login.setText(adminLogin);
		     Element fname = new Element("firstname");
		     fname.setText(adminLogin);
		     Element sname = new Element("surname");
		     sname.setText(surname);
		     Element admpwd = new Element("pwd");
		     admpwd.setText(pwd);
		     Element adminId = new Element("adminId");
		     adminId.setText(String.valueOf(adminID));
		    admin.addContent(login);admin.addContent(fname);admin.addContent(sname);admin.addContent(pwd); admin.addContent(adminId); 
		   admins.addContent(admin); 
		  rootElt.addContent(admins);
		 Administrator newadmin = new Administrator();
		 newadmin.firstname = firstname;
		 newadmin.login = newAdminlogin;
		 newadmin.pwd = pwd;
		 newadmin.surname = surname;
		 newadmin.administratorId = adminID;
		 userDB.Administrators.add(newadmin);
;		return userDB.saveDB();
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
