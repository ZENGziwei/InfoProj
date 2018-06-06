package userModel;

public class Student extends User {
   	private int studentId = 0;
	  private int GroupId = 0;
	
	public Student(String login,int StudentID, String firstname, String surname, String pwd)
	  {
	    super(login, firstname,surname,pwd);
	    studentId = StudentID;
	    GroupId = -1;
	  }
	public Student(String login,int StudentID, String firstname, String surname, String pwd,int groupId)
	  {
		super(login, firstname,surname,pwd);
		studentId = StudentID;
	    GroupId = groupId;
	  }
	public String getstudentLogin() {
		return this.login;
	}
	
	public void setgroup(int groupId) {
		this.GroupId = groupId;
	}
}
