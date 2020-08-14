import java.util.*;
import java.io.*;
public class DAGT
{
int nos;
static HashMap hmap;
String sub[];
static Scanner scan=new Scanner(System.in);
HashMap visited;
static HashMap  precheck;
HashSet hs;
DAGT()
 {
 }

//Constructor for DAG Structure  
DAGT(int n)
  {
    nos=n;
    hmap=new HashMap(n);
    visited=new HashMap(n);
  }
//END
boolean checkpre(HashMap hp,String code)
{
   int counter=0;
   float cgpa=0.0f;
   System.out.println("\nHash Map="+hp);
   
      //System.out.println(precheck); 
    
      //System.out.println("\nHash Map="+hs);
    HashSet hs=(HashSet)precheck.get(code);
   // System.out.println("\n CHECKPRE for code......"+hs);
     Object[] harray=hs.toArray();
     for(int i=0;i<harray.length;i++)
      {
           //System.out.println("number of baap"+harray.length);
        if(!hp.containsKey(harray[i]))
          {
             System.out.println("Course  "+harray[i]+"  is required desired ");
             //return false; 
          }
           else
              {counter=counter+1;
               cgpa=cgpa+(float)hp.get(harray[i]);
               
              }
          }
             if((counter==harray.length)||(counter>harray.length/2)&&(cgpa/counter>=8.5))
                 return true;
             else
               return false;
    
  }  
 
     




//SETPRE METHOD
void pre(String c_name)
{
  int ch=1,kk;
  String dc;
  System.out.println("\n Enter the Courses code for which "+c_name+" is the prerequistic");
  HashSet hset=(HashSet)hmap.get(c_name);
       while(ch==1) 
          {
              dc=scan.next();
              System.out.println("\n press 1 if code  has 1/more adjacent");
              kk=scan.nextInt();
               if(kk==1)
               { 
               hset.add(dc);
               }
               System.out.println("\n Press 1 for more");
               ch=scan.nextInt();
               
          }
}
//END
//...........................................................................................
//show pre
HashMap showpre()
  {
   HashSet hd;
   HashMap precheck1=new HashMap();
    for(int i=0;i<nos;i++)
        { precheck1.put(sub[i],new HashSet());
          //System.out.println("");
        }
  for(int j=0;j<nos;j++)
    {
      for(int k=0;k<nos;k++)
        {
          hd=(HashSet)hmap.get(sub[k]);
           if(hd.contains(sub[j]))
                 ((HashSet)(precheck1.get(sub[j]))).add(sub[k]);
         }
    }
   
   int more=1;
    String cname;
    while(more==1)
     {
     System.out.println("Enter Course for prerequistics courses");
     cname=scan.next();
      System.out.println(precheck1.get(cname));
            System.out.println(" 1 for more");
             more=scan.nextInt();
      } 
        
   //precheck=precheck1;   
    
 return  precheck1;

  }
//END
//.............................................................................................
//dft
void dft(String key,HashMap visited,Stack stk)
 {
       
        // Mark the current node as visited. 
        visited.put(key,true); 
        String ik=null; 
        
        
        Iterator it =((HashSet)(hmap.get(key))).iterator(); 
        while (it.hasNext()) 
        { 
            ik =(String)it.next(); 
             //System.out.println("Push.......... "+ik+"............into Stack");
            if ((boolean)visited.get(ik)==false) 
                dft(ik, visited, stk); 
        } 
        //System.out.println("Push "+ik+"into Stack");
  
        // Push current vertex to stack which stores result 
        stk.push(new String(key)); 
    

      
 }
//end
//TOPOLOGICAL SORT
void topologicalSort()
 {
  Stack stk=new Stack();
    
   //String sub[];
    sub=new String[nos];
       
   int j=0;
  Set<String> keys=hmap.keySet();
  //System.out.println("\n Set 88888888888888"+keys);
  for(String key:keys)
     {
       sub[j]=key;
      // System.out.println("\nsub me*****"+sub[j]);
      visited.put(sub[j],false);
       j++;
     }

   for(int k=0;k<nos;k++)
      {
        if((boolean)visited.get(sub[k])==false)
           {
              // System.out.println("\n >>>>>>>"+sub[k]);
              dft(sub[k],visited,stk);
           }
      } 

      while (stk.empty()==false) 
         { System.out.print(stk.pop() + " ");       
         }
}
//End
//MAIN METHOD
public static void main(String []er)
 {  
     
    int c_number,choice;
    String c_name;
    System.out.println("\n Enter Number of Course");
    c_number=scan.nextInt();
   DAGT dag=new DAGT(c_number);
   //initiateHM(c_number);
    Student student=new Student();
        
         for(int i=0;i<c_number;i++)
           {
               System.out.println("\n Enter Course code"+(i+1));
               c_name=scan.next();
               hmap.put(c_name,new HashSet());
               dag.pre(c_name);
           }
      System.out.println("DAG CREATED");
     dag.topologicalSort();
     int cont=1;
     while(cont==1)
     {
     System.out.println("\n Press 1 for show course and prerequistics ");
     System.out.println("\nPress 2 for Register Student to course");
     System.out.println("\n Press 3 for Common Course among students");
      System.out.println("\n Press 4 for List of Courses and Registered Students");
      System.out.println("\n Press 5 for Search students registered for given course");
      choice=scan.nextInt();
      switch(choice)
       {
           
             case 1:
              precheck=dag.showpre();
                  break;
              
                 case 2: student.regStudent(); 
                            break;
              
                  case 3: System.out.println("Common Courses among Students");
                            student.commonCourse(); 
                            break;
                   case 4: student.getName(dag.sub);
                           break;
                    case 5:     
                              System.out.println("\n Enter Name of Course");
                              String crse=scan.next();
                               System.out.println("\n Enter Prefix for Name of Students");
                               String prfx=scan.next();
                               student.printRegisteredStudents(crse,prfx);
                               break;
                  default: System.out.println("UC");
            }//switch    
              System.out.println("Press 1 for more...");
               cont=scan.nextInt();
      }//while   
  }//main
}
