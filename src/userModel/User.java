package userModel;

public class User {
	protected String firstname;
	protected String surname;
	protected String pwd;
	protected String login;
	
	public User(String login, String firstname, String surname, String pwd)
	  {
	    this.login = login;
	    this.firstname = firstname;
	    this.surname = surname;
	    this.pwd = pwd;
	  }
	
	public void ViewTimeTable() {
		
	}
	
	public String getfirstname() {
		return this.firstname;
	}
	
	public String getsurtname() {
		return this.surname;
	}
	
	public String getpwd() {
		return this.pwd;
	}
	
	public String getlogin() {
		return this.login;
	}
	public String setfirstname() {
		return this.firstname;
	}
	
	public String setsurtname() {
		return this.surname;
	}
	
	public String setpwd() {
		return this.pwd;
	}
	
	public String setlogin() {
		return this.login;
	}
	

}
