package userModel;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.List;
import org.jdom2.output.*;
/**
 * 
 * Cette classe gére la base de données d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes à partir d'un fichier XML. 
 * La structure du fichier XML devra être la même que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

//TODO Classe à modifier

public class UserDB {
	
	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	static org.jdom2.Document document = null;
	private String file;
	List<Teacher>  Teachers;
	List<Student>  Students;
	List<Administrator>  Administrators;
	List<Group> Groups;
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * !!!!!!!!!!!! PENSEZ À AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS PERMETTRA DE CHARGER LA BASE DE DONNÉES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	public UserDB(String file){
		//TODO Fonction à modifier
		super();
		Element rootElt = new Element("UsersDB");
		  document = new Document(rootElt);
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
		  
		  try{
			  XMLOutputter sortie =
			                 new XMLOutputter(Format.getPrettyFormat());
			  sortie.output(document, new FileOutputStream(file));
			 }catch (java.io.IOException e){}
	   
		  this.saveDB();
		this.setFile(file);
	}
	
	/**
	 * Getter de file
	 * 
	 * @return 
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public String getFile() {
		return file;
	}
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 		Le nom du fichier qui contient la base de données.
	 */
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public boolean loadDB() {

		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(this.getFile()));
		} catch (Exception e) {
		}
		if (document != null)
			return true;
		else
			return false;

	}

	public boolean saveDB() {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(document, new FileOutputStream(this.getFile()));
		} catch (java.io.IOException e) {
		}
		if (document != null)
			return true;
		else
			return false;
	}
}
