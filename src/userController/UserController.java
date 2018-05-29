package userController;

import userModel.UserDB;
import org.jdom2.input.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.filter.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom2.*;
import java.io.*;

/**
 * Cette classe est le contrôleur d'utilisateurs que vous devez implémenter.
 * Elle contient un attribut correspondant à la base de données utilisateurs que
 * vous allez créer. Elle contient toutes les fonctions de l'interface
 * IUserController que vous devez implémenter.
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

// TODO Classe à modifier

public class UserController implements IUserController {

	/**
	 * Contient une instance de base de données d'utilisateurs
	 * 
	 */
	private UserDB userDB = null;
	static org.jdom2.Document document = null;

	/**
	 * Constructeur de controleur d'utilisateurs créant la base de données
	 * d'utilisateurs
	 * 
	 * @param userfile
	 *            Fichier XML contenant la base de données d'utilisateurs
	 */
	public UserController(String userfile) {
		UserDB userDB = new UserDB(userfile);
		this.setUserDB(userDB);
		Boolean OpenDB = this.loadDB();
	}

	@Override
	public String getUserName(String userLogin) {
		// TODO Auto-generated method stub
		String usrname = "ZENG";
		// org.jdom2.Document document = null;
		Boolean OpenDB = this.loadDB();
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element stuElt = rootElt.getChild("Students");
			Element teaElt = rootElt.getChild("Teachers");
			Element admElt = rootElt.getChild("Administrators");
			List<Element> Elts = stuElt.getChildren("Student");
			Elts.addAll(teaElt.getChildren("Teacher"));
			Elts.addAll(admElt.getChildren("Administrator"));
			Iterator<Element> itElts = Elts.iterator();
			while (itElts.hasNext()) {
				Element unElts = (Element) itElts.next();
				String logDunElts = unElts.getChild("login").getText();
				if (userLogin == logDunElts) {
					usrname = unElts.getChild("firstname").getText() + unElts.getChild("surname").getText();
				}
			}
		}
		return usrname;
	}

	@Override
	public String getUserClass(String userLogin, String userPwd) {
		// TODO Auto-generated method stub
		String userClass = "";
		Boolean OpenDB = this.loadDB();
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element stuElt = rootElt.getChild("Students");
			Element teaElt = rootElt.getChild("Teachers");
			Element admElt = rootElt.getChild("Administrators");
			List<Element> sElts = stuElt.getChildren("Student");
			Iterator<Element> stuElts = sElts.iterator();
			while (stuElts.hasNext()) {
				Element unstuElts = (Element) stuElts.next();
				String logDunstuElts = unstuElts.getChild("login").getText();
				String pwdDunstuElts = unstuElts.getChild("pwd").getText();
				if (userLogin == logDunstuElts && userPwd == pwdDunstuElts) {
					userClass = "Student";
				}
			}

			List<Element> tElts = teaElt.getChildren("Teacher");
			Iterator<Element> teaElts = tElts.iterator();
			while (teaElts.hasNext()) {
				Element unteaElts = (Element) teaElts.next();
				String logDunteaElts = unteaElts.getChild("login").getText();
				String pwdDunteaElts = unteaElts.getChild("pwd").getText();
				if (userLogin == logDunteaElts && userPwd == pwdDunteaElts) {
					userClass = "Teacher";
				}
			}

			List<Element> adElts = admElt.getChildren("Administrator");
			Iterator<Element> admElts = adElts.iterator();
			while (teaElts.hasNext()) {
				Element unadmElts = (Element) admElts.next();
				String logDunadmElts = unadmElts.getChild("login").getText();
				String pwdDunadmElts = unadmElts.getChild("pwd").getText();
				if (userLogin == logDunadmElts && userPwd == pwdDunadmElts) {
					userClass = "Administrator";
				}
			}

		}

		return userClass;
	}

	@Override
	public int getStudentGroup(String studentLogin) {
		// TODO Auto-generated method stub
		int stuGroup = 0;
		Boolean OpenDB = this.loadDB();
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element stuElt = rootElt.getChild("Students");
			List<Element> sElts = stuElt.getChildren("Student");
			Iterator<Element> itstuElts = sElts.iterator();
			while (itstuElts.hasNext()) {
				Element unstuElt = (Element) itstuElts.next();
				String logDunstuElts = unstuElt.getChild("login").getText();

				if (studentLogin == logDunstuElts) {
					String stugroupId = unstuElt.getChild("groupId").getText();
					stuGroup = Integer.valueOf(stugroupId).intValue();
				}
			}
		}

		return stuGroup;
	}

	@Override
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,
			String pwd) {
		// TODO Auto-generated method stub
		Boolean OpenDB = this.loadDB();
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element admElt = rootElt.getChild("Administrators");
			List<Element> admElts = admElt.getChildren("Administrator");
			Iterator<Element> itadmElts = admElts.iterator();
			List<String> list = new ArrayList<String>();
			while (itadmElts.hasNext()) {
				Element unadmElt = (Element) itadmElts.next();
				list.add(unadmElt.getChildText("login"));
			}

			if (list.contains(adminLogin) && !list.contains(newAdminlogin)) {
				Element admin = new Element("Administrator");
				Element login = new Element("login");
				login.setText(newAdminlogin);
				Element fname = new Element("firstname");
				fname.setText(firstname);
				Element sname = new Element("surname");
				sname.setText(surname);
				Element admpwd = new Element("pwd");
				admpwd.setText(pwd);
				Element adminId = new Element("adminId");
				adminId.setText(String.valueOf(adminID));
				admin.addContent(login);
				admin.addContent(fname);
				admin.addContent(sname);
				admin.addContent(admpwd);
				admin.addContent(adminId);
				admElt.addContent(admin);
				rootElt.addContent(admElt);
			}
		}
		return this.saveDB();
	}

	@Override
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname,
			String surname, String pwd) {
		// TODO Auto-generated method stub
		
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element teaElt = rootElt.getChild("Teachers");
			Element admElt = rootElt.getChild("Administrators");
			List<Element> admElts = admElt.getChildren("Administrator");
			List<Element> teaElts = teaElt.getChildren("Teacher");
			Iterator<Element> itteaElts = teaElts.iterator();
			List<String> list = new ArrayList<String>();
			while (itteaElts.hasNext()) {
				Element unteaElt = (Element) itteaElts.next();
				list.add(unteaElt.getChildText("login"));
			}

			if (!list.contains(newteacherLogin)) {
				Element teach = new Element("Teacher");
				Element login = new Element("login");
				login.setText(newteacherLogin);
				Element fname = new Element("firstname");
				fname.setText(firstname);
				Element sname = new Element("surname");
				sname.setText(surname);
				Element teapwd = new Element("pwd");
				teapwd.setText(pwd);
				Element teachId = new Element("teacherId");
				teachId.setText(String.valueOf(teacherID));
				teach.addContent(login);
				teach.addContent(fname);
				teach.addContent(sname);
				teach.addContent(teapwd);
				teach.addContent(teachId);
				teaElt.addContent(teach);
				rootElt.addContent(teaElt);
			}
		}
		return this.saveDB();
	}

	@Override
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		// TODO Auto-generated method stub
		Boolean OpenDB = this.loadDB();
		Element rootElt;

		if (OpenDB) {
			rootElt = document.getRootElement();
			Element stuElt = rootElt.getChild("Students");
			List<Element> stuElts = stuElt.getChildren("Student");
			Iterator<Element> itstuElts = stuElts.iterator();
			List<String> list = new ArrayList<String>();
			while (itstuElts.hasNext()) {
				Element unstuElt = (Element) itstuElts.next();
				list.add(unstuElt.getChildText("login"));
			}

			if ( !list.contains(newStudentLogin)) {
				Element student = new Element("Student");
				Element login = new Element("login");
				login.setText(newStudentLogin);
				Element fname = new Element("firstname");
				fname.setText(firstname);
				Element sname = new Element("surname");
				sname.setText(surname);
				Element stupwd = new Element("pwd");
				stupwd.setText(pwd);
				Element studentId = new Element("studentId");
				studentId.setText(String.valueOf(studentID));
				student.addContent(login);
				student.addContent(fname);
				student.addContent(sname);
				student.addContent(stupwd);
				student.addContent(studentId);
				stuElt.addContent(student);
				rootElt.addContent(stuElt);
			}
		}
		return this.saveDB();
	}

	@Override
	public boolean removeUser(String adminLogin, String userLogin) {
		// TODO Auto-generated method stub
		Boolean OpenDB = this.loadDB();
		Element rootElt;
		rootElt = document.getRootElement();
		Element oldstuElt = rootElt.getChild("Students");
		Element teaElt = rootElt.getChild("Teachers");
		Element admElt = rootElt.getChild("Administrators");
		List<Element> Elts = oldstuElt.getChildren("Student");
		Elts.addAll(teaElt.getChildren("Teacher"));
		Elts.addAll(admElt.getChildren("Administrator"));
		Iterator<Element> itElts = Elts.iterator();
		
		
		Element newstuElt = new Element("Students");
		if(itElts.hasNext()) {
			Element courant = (Element)itElts.next();
			if(courant.getChild("login").getText()!=userLogin) {
				newstuElt.addContent(courant);
			}
		}
	      //On parcours la liste grâce à un iterator
		return false;
	}

	@Override
	public boolean addGroup(String adminLogin, int groupId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeGroup(String adminLogin, int groupId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] usersToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] usersLoginToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] studentsLoginToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] groupsIdToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] groupsToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean loadDB() {
		// TODO Auto-generated method stub
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(this.userDB.getFile()));
		} catch (Exception e) {
		}
		if (document != null)
			return true;
		else
			return false;

	}

	@Override
	public boolean saveDB() {
		// TODO Auto-generated method stub
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(this.userDB.getFile()));
		} catch (java.io.IOException e) {
		}
		if (document != null)
			return true;
		else
			return false;
	}

	public UserDB getUserDB() {
		return userDB;
	}

	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}

}
