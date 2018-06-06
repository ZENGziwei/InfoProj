package userModel;

import java.util.Hashtable;

public final class Group
{
  public Hashtable students;
  private int numero;
  private int nombres;
  
  public Group(int num)
  {
    this.numero = num;
    this.nombres = 0;
    students = new Hashtable();
  }
  
  public final int getgroupnumber()
  {
    return this.numero;
  }
  
  public final void associateToGroup(Student stu)
  {
    if (students.get(stu.getstudentLogin()) == null)
    {
      students.put(stu.getstudentLogin(), stu);
      stu.setgroup(numero);
      nombres += 1;
    }
  }
  
  public final void removeStudent(Student stu)
  {
    if (students.get(stu.getstudentLogin()) != null)
    {
      stu.setgroup(-1);
      students.remove(stu.getstudentLogin());
      nombres -= 1;
    }
  }
  
  public final String toString()
  {
    return "Group " + numero + " | Stud Nb : " + nombres;
  }
}
