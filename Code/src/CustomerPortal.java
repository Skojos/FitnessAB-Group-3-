import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class CustomerPortal {
	
	
	public static void CustomerSign() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Databas/PersonTidbok.db";  
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
	      String user = "";
	      
	      
	      while(signIn) {
	    	  
	    	  System.out.println("Type in username"); 
	          String userName = reader.readLine();
	          
	          System.out.println("Type in password"); 
	          String password = reader.readLine();
			

	          try {
	          
	    	  String credentials = "Select * from Customer WHERE Email = ? and Password = ?";

	    	  pstmt = conn.prepareStatement(credentials);                
	          
	          pstmt.setString(1, userName);
	          pstmt.setString(2, password);
	          
	          ResultSet rs = pstmt.executeQuery();
	          ResultSetMetaData rsmd = rs.getMetaData();
	          
	          
	          
	          user = rs.getString(2);
	       
	          
	             
	            	  if (rs.next() == false) {
	           
	            		  break;
	            	  } else {
	            		  
	            		  signIn = false;
	            	  }
	            	  
	            	  

	             
	          } catch(SQLException e){
	        	  
	        	  System.out.println("Wrong credentials");
	        	  return;
	           }
	         
	     }
	      
	    	  
	     
	      
	      System.out.println("Sign in sucessfull");
	      
	      System.out.println("Welcome " + user);
	      
	      String customerMenu = "";
	      
	      while(!customerMenu.contentEquals("Q")) {
	    	  
	          System.out.println(""); 
	          System.out.println("Customer menu - använd kortkommandon för respektive meny");
	          System.out.println();
	          
	          System.out.println("B - Book a class");
	          System.out.println("Q - Sign out");
	          
	          
	          customerMenu = reader.readLine();
	          customerMenu = customerMenu.toUpperCase();  
	          
	          switch(customerMenu) {
	          
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
