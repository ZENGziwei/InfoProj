package userModel;

public class Administrator extends User{
	private int administratorId;
	
	public boolean Addmin(UserDB userDB, String adminLogin, String newAdminlogin,int adminID, String firstname, String surname, String pwd) {
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
