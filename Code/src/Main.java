

import java.io.*;


public class Main {


   
   public static void main(String[] args) throws IOException {
   
	   
	   
	   BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
      
    

      
    
      
      String menu = "";
      
      
      //String staffMenu = "";
      
      System.out.print("\033[H\033[2J");//Clear console
   

      

  
      while (!menu.contentEquals("Q")) {
         System.out.println(""); 
         System.out.println("Välj meny - använd kortkommandon för respektive meny");
         System.out.println();
         
         System.out.println("R - Become member");
         System.out.println("C - Sign in customer");
         System.out.println("S - Sign in staff");
         System.out.println("Q - Quit");
               
              
             
         menu = reader.readLine();
         menu = menu.toUpperCase();         
       
         switch(menu) {
         
         case "R":
        	 RegisterCustomer.register();
        	 break;
         
         case "C":

	          CustomerPortal.CustomerSignIn();

	          break;
	          
	     case "S":
	    	 
	    	 StaffPortal.StaffSignIn();
	    	 
	    	 break;
         case "Q":
            	System.out.println("Exiting program");
            	System.exit(0);
	          
	     default:
	        	  System.out.println("Fel");
	        	  break;
         }
      
   
         
      } // While-loop slutar här.
      
   }  //Main slutar här  
}



