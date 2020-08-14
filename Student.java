import java.util.*;
import java.io.*;
//HashMap hmap=new HashMap();
public class Student
{
 HashMap cmap;
 HashMap studentMAP=new HashMap();
 DAGT dag=new DAGT();
 Scanner scan=new Scanner(System.in);
// 
void commonCourse()
{
    String name1,name2;
    System.out.println("\nEnter Name of Student");
     name1=scan.next();
     HashMap smap=(HashMap)studentMAP.get(name1);
     Set set1=smap.keySet();
        int ch=1;
      while(ch==1)
       {
           System.out.println("\nEnter Name of Another Student");
           name2=scan.next();
           HashMap smap1=(HashMap)studentMAP.get(name2);
           Set set2=smap1.keySet();
           set1.retainAll(set2);
           System.out.println("\nPress 1 for more");
           ch=scan.nextInt();
    
       }
   
      System.out.println("\nCommon Subjects are"+set1);
    
}
//...............................................................
void printRegisteredStudents(String crse,String prfx)
{
System.out.println("Under Construction");
}

//...............................................................
void getName(String sub[])
{ 
   HashSet hsnew;
   cmap=new HashMap();
   for(int i=0;i<sub.length;i++)
      {
       hsnew=createHS(sub[i]);
       cmap.put(sub[i],hsnew);
        System.out.println("List of Course and Registered Students");
        System.out.println("Course->"+sub[i]+" Students->"+hsnew);
        
      }

}
HashSet createHS(String k)
{
 HashSet hst=new HashSet();
 Set nset=studentMAP.keySet();
  Object[] o=nset.toArray();
   for(int j=0;j<o.length;j++)
     {
       HashMap hv=(HashMap)(studentMAP.get(o[j]));
       if(hv.containsKey(k))
            {
             hst.add(o[j]);
            }
     }
    return hst;
}
//...............................................................
 
//REG STUDENT MODULES 
 public HashMap regStudent()
   { int ch=1;
       while(ch==1)
       {
      String s_name;
      System.out.println("\nEnter Name of Student");
      s_name=scan.next();
       
      studentMAP.put(s_name,register(s_name));
      System.out.println("Do Yo Want to Register More Studens");
       ch=scan.nextInt();
      }
   return studentMAP;
    }//regstudent
  HashMap  register(String s_name)
   {
       String c_code;
       float cgpa; 
 
       HashMap hmap=new HashMap();
       int ch=1;
     //System.out.println("\nEnter Name of Student");
     System.out.println("\nRegister Student for the Course");
        while(ch==1)
         {
          System.out.println("\nEnter Course Code");
           c_code=scan.next();
           System.out.println("\nEnter CGPA of student in given Course");
           cgpa=scan.nextFloat();
            // hmap.put("Code",10.0);
        
                boolean hv=dag.checkpre(hmap,c_code);
                if(hv)
                 {
                  hmap.put(c_code,cgpa);
                 } 
                    else
                       { 
                         System.out.println("U r not eligible please complete desired course first");
                         } 
                  System.out.println("press 1 if you want to continue  ");
                  ch=scan.nextInt();   
           }//while
       return hmap;
     }//register
}//class
