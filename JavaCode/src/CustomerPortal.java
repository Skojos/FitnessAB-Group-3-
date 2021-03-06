import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class CustomerPortal {
	
	
	public static void CustomerSignIn() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Database/fitnessAB.db";  
		   
		   final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	    
		
	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); 
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		
	    
		
	      boolean signIn = true;
	      String user = "";
	      long pNr = 0;
	      String userPassword = "";
	      
	      
	      while(signIn) {
	    	  
	    	  System.out.println("Type in username"); 
	          String userName = reader.readLine();
	          
	          System.out.println("Type in password"); 
	          String password = reader.readLine();
			

	          try {
	          
	    	  String credentials = "Select * from Members WHERE Email = ? and Password = ?";

	    	  pstmt = conn.prepareStatement(credentials);                
	          
	          pstmt.setString(1, userName);
	          pstmt.setString(2, password);
	          
	          ResultSet rs = pstmt.executeQuery();
	          
	          
	          
	          
	          user = rs.getString(2);
	          pNr = rs.getLong(1);
	          userPassword = rs.getString(6);
	       
	          
	             
	            	  if (rs.next() == false) {
	           
	            		  break;
	            	  } else {
	            		  
	            		  signIn = false;
	            	  }
	            	  
	           conn.close();

	             
	          } catch(SQLException e){
	        	  
	        	  System.out.println("Wrong credentials");
	        	  return;
	           }
	         
	     }
	      
	    	  
	     
	      
	      System.out.println("Sign in sucessfull");
	      
	      System.out.println("Welcome " + user);
	      
	      int customerMenu = 1000;
	      
	      while (customerMenu != 0) {
	    	  
	          System.out.println(""); 
	          System.out.println("Customer menu");
	          System.out.println();
	          System.out.println("Class enrollment subsystem");
	          System.out.println();
	          System.out.println("1 - Book a class"); //Done
	          System.out.println("2 - Cancel a class");//Done
	          System.out.println("3 - My Bookings");//Done
	          
	          System.out.println("Membership subsystem");
	          System.out.println();
	          System.out.println("4 - Account(My Details), Update information");//Done
	          System.out.println("5 - Account, Change password");//Done
	          System.out.println("6 - Change tier ");//Done
	          System.out.println("7 - Cancel membership");//Done
	          
	          
	          
	          System.out.println("0 - Sign out");//Done
	   
	          
	          
	          customerMenu = Integer.parseInt(reader.readLine());
	           
	          
	          switch(customerMenu) {
	          
	          case 1:
	        	  ClassEnrollmentSubsystem.BookClass(pNr);
	        	  break;
	          case 4:
	        	  MembershipSubSystem.cAccountDetails(pNr);
	        	  break;
	        
	          case 5: 
	        	  MembershipSubSystem.cPassword(pNr, userPassword);
	        	  break;
	          case 6:
	        	  MembershipSubSystem.cMemberShipTier(pNr);
	        	  break;
	          case 7:
	        	  MembershipSubSystem.cMembership(pNr);
	        	  break;
	        	  
	          case 3:
	        	  ClassEnrollmentSubsystem.Boookings(pNr);
	        	  break;
	          case 2:
	        	  ClassEnrollmentSubsystem.DeleteBooking(pNr);
	        	  break;
	          case 0:
	        	  System.out.println("Signing out");
	          break;
	        	 
	          default:
	        	  
	        	  System.out.println("fel");
	        	 
	        	  break;
	         
	          } 
	          
	          }
		
		 
		
	}

	
	

}
