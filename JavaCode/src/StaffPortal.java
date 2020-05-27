
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import org.sqlite.SQLiteConfig;

public class StaffPortal {
	
	
	public static void StaffSignIn() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Database/fitnessAB.db";  
		  
		   final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      long pnr = 0;
	    		 
	    
		
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
	          System.out.println("1 - Register new member"); //Done     
	          System.out.println("2 - Change information about member");
	          System.out.println("3 - Change member information, Password");
	          System.out.println("4 - Activate membership");//Done
	          System.out.println("5 - Cancel a membership"); //Done
	          System.out.println("6 - Change members tier");//Done
	          System.out.println();
	          System.out.println();
	          System.out.println("Equipment management subsystem");
	          System.out.println();
	          System.out.println("7 - Add branch"); //Done
	          System.out.println("8 - Add equiptment and assign to facility"); //Done
	          System.out.println("9 - Delete equipment and show equipment");//Done
	          System.out.println();
	          System.out.println();
	          System.out.println("Class enrollment Subsystem");
	          System.out.println();
	          System.out.println("10  - Add new class");//Done
	          System.out.println("11 - Add class to schedule");//Done
	          System.out.println("12 - Delete class from schedule");//Done
	          System.out.println("13 - Add new instructor");//Done
	          System.out.println("14 - Assign instructor to a class");//Done
	          System.out.println("");
	          System.out.println("");
	          System.out.println("0 - Sign out"); //Done
	          
	          
	          staffMenu = Integer.parseInt(reader.readLine());
	          
	          
	          switch(staffMenu) {
	          
	          case 1:
	        	  
	        	  MembershipSubSystem.register();
	        	  
	        	  break;
	          case 2:
	        	  System.out.println("Enter personal number:");
	        	  pnr = Long.parseLong(reader.readLine());
	        	  MembershipSubSystem.cAccountDetails(pnr);
	        	  break;
	          case 3:
	        	  System.out.println("Enter personal number:");
	        	  pnr = Long.parseLong(reader.readLine());
	        	  MembershipSubSystem.cPasswordStaff(pnr);
	        	  break;
	        	  
	          
	          case 0:
	        	  System.out.println("Signing out");
	          break;
	          
	          case 4: 
	        	  
	        	  System.out.println("Enter personal number:");
	        	  pnr = Long.parseLong(reader.readLine());
	        	  MembershipSubSystem.aMembership(pnr);
	        	  break;
	          
	          case 5: 
	        	  
	        	  System.out.println("Enter personal number:");
	        	  pnr = Long.parseLong(reader.readLine());
	        	  MembershipSubSystem.cMembership(pnr);
	        	  break;
	          case 6:
	        	  
	        	  System.out.println("Enter personal number:");
	        	  pnr = Long.parseLong(reader.readLine());
	        	  MembershipSubSystem.cMemberShipTier(pnr);
	        	  break;
	          
	          case 7:
	        	  EquipmentManagementSubsystem.AddBranch();
	        	  break;
	        
  
	        	  
	          case 8:
	        	  EquipmentManagementSubsystem.AddEquipment();
	        	  break;
	        	  
	          case 9:
	        	  EquipmentManagementSubsystem.DeleteEquipment();
	        	  break;
	        	  
	          case 10:
	        	  ClassEnrollmentSubsystem.AddClass();
	        	  break;

	          case 11:
	        	  
	        	  ClassEnrollmentSubsystem.ClassSchedule();
	        	  break;
	        	  
	          case 12:
	        	  
	        	  ClassEnrollmentSubsystem.RemoveClassSchedule();
	        	  break;

	          case 13:
	        	  
	        	  ClassEnrollmentSubsystem.AddInstructor();
	        	  break;
	        	  
	          case 14:
	        	  ClassEnrollmentSubsystem.AssignInstructor();
	        	  break;
	        	  
	        
	         
	        	 
	          default:
	        	  
	        	  System.out.println("fel");
	        	 
	        	  break;
	         
	          } 
	          
	          }
		
		 
		
	}

	
	

}

