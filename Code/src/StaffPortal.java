
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class StaffPortal {
	
	
	public static void StaffSignIn() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
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
	           
	            	return;
	            		  
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
	      
	      
	      
	      int staffMenu = 1000;
	      
	      while(staffMenu != 0) {
	    	  
	          System.out.println(""); 
	          System.out.println("Staff menu");
	          System.out.println();
	          System.out.println();
	          System.out.println("Membership management subsystem");
	          System.out.println();//_______________________________
	          System.out.println("1 - Register new customer");
	          System.out.println("2 - Update customer information");
	          System.out.println("3 - Pause membership");
	          System.out.println("4 - Activate membership");
	          System.out.println("5 - Cancel a membership");
	          System.out.println();
	          System.out.println();
	          System.out.println("Equipment management subsystem");
	          System.out.println();
	          System.out.println("6 - Add branch"); //Done
	          System.out.println("7 - Add equiptment"); //Done
	          System.out.println("8 - Delete equipment");//Done
	          System.out.println();
	          System.out.println();
	          System.out.println("Class Subsystem");
	          System.out.println();
	          System.out.println("9  - Add new class");//Done
	          System.out.println("10 - Add class to schedule");//Done
	          System.out.println("11 - Delete class from schedule");//Done
	          System.out.println("12 - Add new instructor");
	         
	         
	          System.out.println("");
	          System.out.println("");
	          
	          
	          
	          System.out.println("0 - Sign out");
	          
	          
	          staffMenu = Integer.parseInt(reader.readLine());
	          
	          
	          switch(staffMenu) {
	          
	          case 1:
	        	  
	        	  MembershipSubSystem.register();
	        	  
	        	  break;
	          
	          case 0:
	        	  System.out.println("Signing out");
	          break;
	          
	          case 6:
	        	  GymEquipmentSubsystem.AddBranch();
	        	  break;
	        
  
	        	  
	          case 7:
	        	  GymEquipmentSubsystem.AddEquipment();
	        	  break;
	        	  
	          case 8:
	        	  GymEquipmentSubsystem.DeleteEquipment();
	        	  break;
	        	  
	          case 9:
	        	  ClassPtEnrollmentSubsystem.AddClass();
	        	  break;

	          case 11:
	        	  
	        	  ClassPtEnrollmentSubsystem.RemoveClassSchedule();
	        	  break;
	          
	          case 12:
	        	  
	        	  ClassPtEnrollmentSubsystem.AddInstructor();
	        	  break;
	        
	          case 10:
	        	  
	        	  ClassPtEnrollmentSubsystem.ClassSchedule();
	        	  break;
	        	 
	          default:
	        	  
	        	  System.out.println("fel");
	        	 
	        	  break;
	         
	          } 
	          
	          }
		
		 
		
	}

	
	

}

