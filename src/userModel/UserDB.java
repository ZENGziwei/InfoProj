package userModel;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import java.io.*;
import java.util.List;
import org.jdom2.output.*;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 
 * Cette classe gére la base de données d'utilisateurs. Elle doit permettre de
 * sauvegarder et charger les utilisateurs et les groupes à partir d'un fichier
 * XML. La structure du fichier XML devra être la même que celle du fichier
 * userDB.xml.
 * 
 * @see <a href="../../userDB.xml">userDB.xml</a>
 * 
 * @author Jose Mennesson (Mettre à jour)
 * @version 04/2016 (Mettre à jour)
 * 
 */

// TODO Classe à modifier

public class UserDB {

	/**
	 * 
	 * Le fichier contenant la base de données.
	 * 
	 */
	private String file;
	/*
	 * public List<Teacher> Teachers; public List<Student> Students; public
	 * List<Administrator> Administrators; public List<Group> Groups;
	 */
	private Hashtable users = null;
	private Hashtable groups = null;

	/**
	 * 
	 * Constructeur de UserDB.
	 * 
	 * !!!!!!!!!!!! PENSEZ À AJOUTER UN ADMINISTRATEUR (su par exemple) QUI VOUS
	 * PERMETTRA DE CHARGER LA BASE DE DONNÉES AU DEMARRAGE DE L'APPLICATION !!!!!!
	 * 
	 * @param file
	 *            Le nom du fichier qui contient la base de données.
	 */
	public UserDB(String file) {
		// TODO Fonction à modifier
		super();
		users.put("su", new Administrator("su", 0, "su", "su", "superUser"));

		/*
		 * Administrator adm = new Administrator(); adm.firstname = "su"; adm.surname =
		 * "su"; adm.login="su"; adm.pwd="superUser"; adm.administratorId = 0;
		 * Administrators.add(adm); Element rootElt = new Element("UsersDB"); document =
		 * new Document(rootElt); Element admins = new Element("Administrators");
		 * Element admin = new Element("Administrator"); Element login = new
		 * Element("login"); login.setText("su"); Element fname = new
		 * Element("firstname"); fname.setText("su"); Element sname = new
		 * Element("surname"); sname.setText("su"); Element pwd = new Element("pwd");
		 * pwd.setText("superUser"); Element adminId = new Element("adminId");
		 * adminId.setText("0");
		 * admin.addContent(login);admin.addContent(fname);admin.addContent(sname);admin
		 * .addContent(pwd); admin.addContent(adminId); admins.addContent(admin);
		 * rootElt.addContent(admins); this.saveDB(); this.setFile(file);
		 */

	}

	/**
	 * Getter de file
	 * 
	 * @return Le nom du fichier qui contient la base de données.
	 */

	public String getFile() {
		return file;
	}

	/**
	 * Setter de file
	 * 
	 * @param file
	 *            Le nom du fichier qui contient la base de données.
	 */

	public void setFile(String file) {
		this.file = file;
	}

	public boolean loadDB() {

		/*
		 * SAXBuilder sxb = new SAXBuilder(); try { this.document = sxb.build(new
		 * File(this.getFile())); } catch (Exception e) { } if (this.document != null)
		 * return true; else return false;
		 */
		int i = 0;
		int j = 0;
		org.jdom2.Document document = null;
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(this.getFile()));
		} catch (Exception localException) {
		}

		boolean bool1;
		if (document != null) {
			Iterator<Element> ItElts = (((Document) document).getRootElement()).getChild("Groups").getChildren("Group")
					.iterator();
			while (ItElts.hasNext()) {
				int k = Integer.parseInt(((ItElts).next()).getChild("groupId").getText());
				this.addGroup("su", k);
			}
			Element unElts;
			ItElts = (document.getRootElement().getChild("Students").getChildren("Student")).iterator();
			Student unstu;
			Group newgroup;
			while ((ItElts).hasNext()) {
				unElts = (Element) (ItElts).next();
				int i1 = Integer.parseInt((unElts).getChild("groupId").getText());
				String str4 = unElts.getChild("pwd").getText();
				String str3 = unElts.getChild("surname").getText();
				String str2 = unElts.getChild("firstname").getText();
				int n = Integer.parseInt((unElts).getChild("studentId").getText());
				String str1 = ((Element) unElts).getChild("login").getText();
				String adminLogin = "su";
				Student stu = null;
				if (((users.get(adminLogin) instanceof Administrator)) && (users.get(str1) == null)) {
					users.get(adminLogin);
					/*
					 * i1 = i1; str4 = str4; str3 = str3; str2 = str2; n = n;
					 */
					adminLogin = str1;
					stu = new Student(adminLogin, n, str2, str3, str4, i1);
					users.put(str1, stu);
				}
				unstu = stu;
				int m;
				Group groupasscoiate;
				if ((m = Integer.parseInt((unElts).getChild("groupId").getText())) != -1) {
					if (groups.get(unElts.getChild("groupId").getText()) != null) {
						groupasscoiate = (Group) groups.get(unElts.getChild("groupId").getText());
						groupasscoiate.associateToGroup(unstu);
					} else {
						boolean bool2;
						if ((bool2 = addGroup("su", m))) {
							newgroup = (Group) groups.get(String.valueOf(m));
							newgroup.associateToGroup(unstu);
						}
					}
				}
				j++;
			}
			ItElts = (document.getRootElement().getChild("Teachers").getChildren("Teacher")).iterator();
			while ((ItElts).hasNext()) {
				unElts = (ItElts).next();
				addTeacher("su", unElts.getChild("login").getText(),
						Integer.parseInt(unElts.getChild("teacherId").getText()),
						(unElts).getChild("firstname").getText(), (unElts).getChild("surname").getText(),
						(unElts).getChild("pwd").getText());
				j++;
			}

			ItElts = (document.getRootElement().getChild("Administrators").getChildren("Administrator")).iterator();
			while ((ItElts).hasNext()) {
				unElts = (Element) ItElts.next();
				addAdmin("su", (unElts).getChild("login").getText(),
						Integer.parseInt(((Element) unElts).getChild("adminId").getText()),
						((Element) unElts).getChild("firstname").getText(),
						((Element) unElts).getChild("surname").getText(), ((Element) unElts).getChild("pwd").getText());
				j++;
			}
			if (j == 0) {
				bool1 = false;
			} else {
				bool1 = true;
			}
		}
		return bool1;
	}

	public boolean saveDB() {
		try {
			XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
			sortie.output(this.document, new FileOutputStream(this.getFile()));
		} catch (java.io.IOException e) {
		}
		if (this.document != null)
			return true;
		else
			return false;
	}
	
	public final boolean addAdmin(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
	  {
	    boolean bool = false;
	    if (((a.get(paramString1) instanceof a)) && (a.get(paramString2) == null))
	    {
	      a.get(paramString1);
	      paramString5 = paramString5;
	      paramString4 = paramString4;
	      paramString3 = paramString3;
	      paramInt = paramInt;
	      paramString1 = paramString2;
	      paramString1 = new a(paramString1, paramInt, paramString3, paramString4, paramString5);
	      a.put(paramString2, paramString1);
	      bool = true;
	    }
	    return bool;
	  }
}
