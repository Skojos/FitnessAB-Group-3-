import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class CustomerPortal {
	
	
	public static void CustomerSignIn() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		   
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
	      int pNr = 0;
	      
	      
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
	          pNr = rs.getInt(1);
	       
	          
	             
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
	          
	          System.out.println("1 - Book a class"); //Done
	          System.out.println("2 - Delete a Booking");//Done
	          System.out.println("3 - My Bookings");//Done
	          System.out.println("4 - Change Contact information");
	          System.out.println("0 - Sign out");//Done
	          
	          
	          customerMenu = Integer.parseInt(reader.readLine());
	           
	          
	          switch(customerMenu) {
	          
	          case 1:
	        	  ClassPtEnrollmentSubsystem.BookClass(pNr);
	        	  break;
	          
	        
	          case 3:
	        	  ClassPtEnrollmentSubsystem.Boookings(pNr);
	        	  break;
	          case 2:
	        	  ClassPtEnrollmentSubsystem.DeleteBooking(pNr);
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
