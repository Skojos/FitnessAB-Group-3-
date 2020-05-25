import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class EquipmentManagementSubsystem {

	
	public static void AddBranch() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	    
		
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
		
		
        System.out.println("Enter Branch name: "); 
        String branchName = reader.readLine();
        
        
        
       
        
        try {
            
            sql = "INSERT INTO Branch (Name) VALUES (?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setString(1, branchName);
           
           
      
    
            pstmt.executeUpdate();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
	}
	
	
	public static void AddEquipment() throws  IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	    
		
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
		
		
        System.out.println("Enter Location ID: "); 
        int locationID = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter Branch ID: "); 
        int branchID = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter name "); 
        String name = reader.readLine();

        
        
        
       
        
        try {
            
            sql = "INSERT INTO Equipment (LocationID,BranchID,Name) VALUES (?,?,?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setInt(1, locationID);
            pstmt.setInt(2, branchID);
            pstmt.setString(3, name);
           
           
      
    
            pstmt.executeUpdate();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
	}
	
	
	public static void DeleteEquipment() throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	      int id = 0;
	      
	      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
	     
	    
		
	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); 
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      
	      System.out.println("Enter location: ");
   	   	  int locationId = Integer.parseInt(reader.readLine());
   	   	  
   	   	  System.out.println("Enter branch. Press 0 to skip");
	   	  int branch = Integer.parseInt(reader.readLine());
	      
	      
	      try {
	    	  
	    	  
	    	  if (branch == 0) {
	    		  
	             
	    		  sql = "SELECT Equipment.EquipmentID, Equipment.Name AS Equipment, Facility.Name AS Facility, Branch.Name AS Branch FROM Equipment JOIN Facility USING ('LocationID') JOIN Branch USING ('BranchID') where LocationID = ?";
	    		  
	              pstmt = conn.prepareStatement(sql); 
	              pstmt.setInt(1, locationId);
	    		  
	    	  } else {
	    		  
	    		  sql = "SELECT Equipment.EquipmentID, Equipment.Name AS Equipment, Facility.Name AS Facility, Branch.Name AS Branch FROM Equipment JOIN Facility USING ('LocationID') JOIN Branch USING ('BranchID') where LocationID = ? and BranchID = ?";
	    		  
	    		  pstmt = conn.prepareStatement(sql);     
	              pstmt.setInt(1, locationId);
	              pstmt.setInt(2, branch);
	    	  }
              
        	
              
              
              
              
             
              
              ResultSet rs = pstmt.executeQuery();
           
              ResultSetMetaData rsmd = rs.getMetaData();
 
           
              System.out.println("");
              
              int numberOfColumns = rsmd.getColumnCount();
              
           
              for (int i = 1; i <= numberOfColumns; i++) {
                 
                 String columnName = rsmd.getColumnName(i);
                 System.out.printf("%-21s", columnName);
                 
              }
                   
           
              System.out.println(" ");
              
              
              
              while(rs.next()){
              
                 for (int i = 1; i <= numberOfColumns; i++) {
                    if (i > 1) System.out.print(" ");
                    
                    
                    String columnValue = rs.getString(i);
                    System.out.printf("%-20s", columnValue);
                    
                 }
                 System.out.println("");
                  
              
              }
              
            
           
           
           }
           catch(SQLException se) {
           //Hanterar fel för JDBC
        	   System.out.println( se.toString() );
              System.out.println("No class found");
              
           }
          
	  
	     
	      
	      System.out.println("Choose Equipment ID to delete: ");
	    	   id = Integer.parseInt(reader.readLine());
	     
	      
	
	      
	      try {
	    	  sql = "DELETE FROM Equipment WHERE EquipmentID = ?";
	    	  
	    	  pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1,id);
              
              pstmt.executeUpdate();
	    	  
              System.out.println("Equipment deleted");
              
	      } catch(SQLException e) {
	    	  
	    	  System.out.println( e.toString() );
	      }
		
	}
	
	
	
}
