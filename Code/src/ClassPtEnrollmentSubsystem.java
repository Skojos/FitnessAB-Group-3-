import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.sqlite.SQLiteConfig;

public class ClassPtEnrollmentSubsystem {

	
	
	public static void AddInstructor() throws IOException {
		
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
		
		System.out.println("Enter instructor ID: "); 
        int instructor = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter first name: "); 
        String Fname = reader.readLine();
        
        System.out.println("Enter last name"); 
        String Lname = reader.readLine();
        
       
        
        try {
            
            sql = "INSERT INTO Instructor VALUES (?,?,?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setInt(1, instructor);
            pstmt.setString(2, Fname);
            pstmt.setString(3, Lname);
           
      
    
            pstmt.executeUpdate();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
		
	}
	
	
	public static void AddClass() throws IOException {
		
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
		
		
        System.out.println("Enter Class name: "); 
        String className = reader.readLine();
        
        
        
       
        
        try {
            
            sql = "INSERT INTO Class (Name) VALUES (?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setString(1, className);
           
           
      
    
            pstmt.executeUpdate();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
		
	}
	
	public static void ClassSchedule() throws IOException {

		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	      int seats = 0;
		
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
		
		
        
        System.out.println("Enter class ID: "); 
        int classId = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter room ID"); 
        int roomId = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter instructor id");
        int instructorId = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter date: "); 
        String date = reader.readLine();
        
        System.out.println("Enter time: "); 
        String time = reader.readLine();
        
        
        try {
            
            sql = "Select * from Room where RoomID = ?";

            pstmt = conn.prepareStatement(sql);                
           
               
            pstmt.setInt(1, roomId);
            
            ResultSet rs = pstmt.executeQuery();
            
            seats  = rs.getInt(3);
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
        }
        
        
       
        
        try {
            
            sql = "INSERT INTO ClassSchedule (ClassID,RoomID,InstructorID,Date,StartTime,Seats) VALUES (?,?,?,?,?,?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setInt(1, classId);
            pstmt.setInt(2, roomId);
            pstmt.setInt(3, instructorId);
            pstmt.setString(4, date);
            pstmt.setString(5, time);
            pstmt.setInt(6, seats);
           
            
            
           
      
    
            pstmt.executeUpdate();
            
            conn.close();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
		
	}
	
	public static void RemoveClassSchedule() throws IOException {
		
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
		
	      System.out.println("Choose Class Schedule ID to delete: ");
   	   		id = Integer.parseInt(reader.readLine());
		
		try {
	    	  sql = "DELETE FROM ClassSchedule WHERE ClassScheduleID = ?";
	    	  
	    	  pstmt = conn.prepareStatement(sql); 
            
            pstmt.setInt(1,id);
            
            pstmt.executeUpdate();
	    	  
            System.out.println("Class deleted");
            conn.close();
            
	      } catch(SQLException e) {
	    	  
	    	  System.out.println( e.toString() );
	      }
	}
	
	public static void AddPt() throws IOException { 
		
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
		
		System.out.println("Enter Pt ID: "); 
        int id = Integer.parseInt(reader.readLine());
        
        System.out.println("Enter first name: "); 
        String Fname = reader.readLine();
        
        System.out.println("Enter last name"); 
        String Lname = reader.readLine();
        
       
        
        try {
            
            sql = "INSERT INTO PersonalTrainers VALUES (?,?,?)";

            pstmt = conn.prepareStatement(sql);                
            
               
            pstmt.setInt(1, id);
            pstmt.setString(2, Fname);
            pstmt.setString(3, Lname);
           
      
    
            pstmt.executeUpdate();
		
        } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        
      
   
   }
		
		
	}
	
	public static void BookClass(int pNr) throws IOException{
		
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	      int pnr = pNr;
	      int id = 0;
	      int capacity = 0;
	     
	    
		
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
	      
	      
	      try {
              
	    	  System.out.println("Choose class. 1,2,3");
	    	  int classID = Integer.parseInt(reader.readLine());
	    	  
	    	  System.out.println("Choose facility. 1,2,3,4");
	    	  int location = Integer.parseInt(reader.readLine());
              
              
              
              sql =  "SELECT classSchedule.ClassScheduleID,Class.name AS Class, Room.RoomID, Facility.Name AS Facility,"
              		+ "Instructor.Fnamn AS Instructor, ClassSchedule.Date,ClassSchedule.StartTime FROM ClassSchedule, Class, Room, Facility, Instructor "
              		+ "where ClassSchedule.ClassID = ? and ClassSchedule.ClassID = Class.ClassID and ClassSchedule.RoomID = Room.RoomID AND"
              		+ " Room.LocationID = Facility.LocationID AND ClassSchedule.InstructorID = Instructor.InstructorID and Facility.LocationID = ? and ClassSchedule.Seats >= 1";

            		  
            		 
              		 
              
              
              pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, classID);
              pstmt.setInt(2, location);
              
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
              
              
              
              System.out.println("Choose Class Schedule ID to book: ");
  	    	   id = Integer.parseInt(reader.readLine());
              
             
  	  
           conn.close();
           
           }
           catch(SQLException se) {
           //Hanterar fel för JDBC
              
              
           }
           catch(Exception e) {
           //Hanterar fel för Class.forName
              e.printStackTrace();
           }
	      
	      

	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); 
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      
	      
	      
	      
	      try {
              
	    	  
	    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	        LocalDate localDate = LocalDate.now();
	        	
	        	 
	    	String date = dtf.format(localDate);
              
              
              
              sql = "INSERT INTO ClassEnrollment(Pnr,ClassScheduleID,Date) VALUES (?,?,?)";
           		 
              
              pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, pnr);
              pstmt.setInt(2, id);
              pstmt.setString(3, date);
              
              
           
              pstmt.executeUpdate();
              
              System.out.println("Class Booked");
              
              
              
           conn.close();
           
           }
           catch(SQLException e) {
           
        	   System.out.println( e.toString() );
              
           }
	      
	      
	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); 
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      
	     
	      
	      try {
	    	  sql = "UPDATE ClassSchedule SET Seats = Seats -1 where ClassScheduleID = ?";
	    	  
	    	  pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, id);
              
              pstmt.executeUpdate();
	    	  
              
	      } catch(SQLException e) {
	    	  
	    	  System.out.println( e.toString() );
	      }

		
		
	}
	
	public static void Boookings(int pNr) throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	      int pnr = pNr;
	     
	    
		
	      try {
	          Class.forName(DRIVER);
	          SQLiteConfig config = new SQLiteConfig();  
	          config.enforceForeignKeys(true); 
	          conn = DriverManager.getConnection(DB_URL,config.toProperties());  
	       
	          
	       } catch (Exception e) {
	          System.out.println( e.toString() );
	          System.exit(0);
	       }
	      
	     
	      
	      
	      try {
              
              sql = "SELECT Class.name AS Class, Room.RoomID, Facility.Name AS Facility,"
        		+ "Instructor.Fnamn AS Instructor, ClassSchedule.Date,ClassSchedule.StartTime FROM ClassSchedule, ClassEnrollment, Class, Room, Facility, Instructor "
        		+ "where ClassEnrollment.Pnr = ? and ClassSchedule.ClassScheduleID = ClassEnrollment.ClassScheduleID and ClassSchedule.ClassID = Class.ClassID and ClassSchedule.RoomID = Room.RoomID AND"
        		+ " Room.LocationID = Facility.LocationID AND ClassSchedule.InstructorID = Instructor.InstructorID";
        	
              
              pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, pnr);
             
              
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
              
            
           conn.close();
           
           }
           catch(SQLException se) {
          
        	   System.out.println( se.toString() );
              System.out.println("No class found");
              
           }
           catch(Exception e) {
          
              e.printStackTrace();
           }
		
		
	}
	
	public static void DeleteBooking(int pNr) throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql;
	      int pnr = pNr;
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
	      
	     
	      
	      
	      try {
              
              sql = "SELECT ClassSchedule.ClassScheduleID, Class.name AS Class, Room.RoomID, Facility.Name AS Facility,"
        		+ "Instructor.Fnamn AS Instructor, ClassSchedule.Date,ClassSchedule.StartTime FROM ClassSchedule, ClassEnrollment, Class, Room, Facility, Instructor "
        		+ "where ClassEnrollment.Pnr = ? and ClassSchedule.ClassScheduleID = ClassEnrollment.ClassScheduleID and ClassSchedule.ClassID = Class.ClassID and ClassSchedule.RoomID = Room.RoomID AND"
        		+ " Room.LocationID = Facility.LocationID AND ClassSchedule.InstructorID = Instructor.InstructorID";
        	
              
              pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, pnr);
             
              
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
          
	  
	     
	      
	      System.out.println("Choose Class Schedule ID to un-book: ");
	    	   id = Integer.parseInt(reader.readLine());
	     
	      
	      try {
	    	  sql = "UPDATE ClassSchedule SET Seats = Seats +1 where ClassScheduleID = ?";
	    	  
	    	  pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1, id);
              
              pstmt.executeUpdate();
	    	  
              
	      } catch(SQLException e) {
	    	  
	    	  System.out.println( e.toString() );
	      }
	      
	      try {
	    	  sql = "DELETE FROM ClassEnrollment WHERE Pnr = ?";
	    	  
	    	  pstmt = conn.prepareStatement(sql); 
              
              pstmt.setInt(1,pnr);
              
              pstmt.executeUpdate();
	    	  
              System.out.println("Class un-booked");
              
	      } catch(SQLException e) {
	    	  
	    	  System.out.println( e.toString() );
	      }
	      
	     
		
		
		
	}
	
	public static void BookPt() {
		
	}
	
	
}
