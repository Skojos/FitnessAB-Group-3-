
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class StaffPortal {
	
	
	public static void StaffSignIn() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessab.db";  
		   // Namnet på den driver som används av java för att prata med SQLite
		   final String DRIVER = "org.sqlite.JDBC";   

		
		 Connection conn = null;
	      PreparedStatement pstmt = null;
	    
		
	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); // Denna kodrad ser till att sätta databasen i ett läge där den ger felmeddelande ifall man bryter mot någon främmande-nyckel-regel
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          // Om java-progammet inte lyckas koppla upp sig mot databasen (t ex om fel sökväg eller om driver inte hittas) så kommer ett felmeddelande skrivas ut
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		
	    
		
	      boolean signIn = true;
	      
	      
	      
	      while(signIn) {
	    	  
	    	  System.out.println("Enter employee ID: "); 
	          int userName = Integer.parseInt(reader.readLine());
	          
	          System.out.println("Enter password"); 
	          String password = reader.readLine();
			

	          try {
	          
	    	  String credentials = "Select * from Staff WHERE employeeID = ? and password = ?";

	    	  pstmt = conn.prepareStatement(credentials);                
	          
	          pstmt.setInt(1, userName);
	          pstmt.setString(2, password);
	          
	          ResultSet rs = pstmt.executeQuery();
	         
	          

	             
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
	      
	      
	      
	      String staffMenu = "";
	      
	      while(!staffMenu.contentEquals("Q")) {
	    	  
	          System.out.println(""); 
	          System.out.println("Customer menu - använd kortkommandon för respektive meny");
	          System.out.println();
	          
	          System.out.println("R - Register new customer");
	          System.out.println("Q - Sign out");
	          
	          
	          staffMenu = reader.readLine();
	          staffMenu = staffMenu.toUpperCase();  
	          
	          switch(staffMenu) {
	          
	          case "R":
	        	  
	        	  RegisterCustomer.register();
	          
	          case "Q":
	        	  System.out.println("Signing out");
	          break;
	        	 
	          default:
	        	  
	        	  System.out.println("fel");
	        	 
	        	  break;
	         
	          } 
	          
	          }
		
		 
		
	}

	
	

}

