

import java.io.*;


public class Main {


   
   public static void main(String[] args) throws IOException {
   

	  BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));      
      int menu = 1000;

      System.out.print("\033[H\033[2J");//Clear console
   
      
      while (menu != 0) {
    	  
         System.out.println(""); 
         System.out.println("Fitness AB - Main page funtions");
         System.out.println();         
         System.out.println("1 - Become member");
         System.out.println("2 - Sign in customer");
         System.out.println("3 - Sign in staff");
         System.out.println("0 - Quit");
                          
         menu = Integer.parseInt(reader.readLine());
                 
       
         switch(menu) {
         
         case 1:
        	 MembershipSubSystem.register();
        	 break;      
         case 2:
	          CustomerPortal.CustomerSignIn();
	          break;     
	     case 3:
	    	 StaffPortal.StaffSignIn();
	    	 break;
         case 0:
            	System.out.println("Exiting program");
            	System.exit(0);    
	     default:
	        	  System.out.println("Wrong input");
	        	  break;
         }
      
   
         
      } // While-loop slutar här.
      
   }  //Main slutar här  
}



