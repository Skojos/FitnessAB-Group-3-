import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
import org.sqlite.SQLiteConfig;



public class MembershipSubSystem {
	
	

	public static void register() throws IOException {
		
			final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		
			final String DRIVER = "org.sqlite.JDBC";   

		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
		
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
	      
	      System.out.println("Enter p-number: "); 
          long pNr = Long.parseLong(reader.readLine());
          
          System.out.println("Enter Firstname"); 
          String fName = reader.readLine();
          
          System.out.println("Enter Lastname: "); 
          String lName = reader.readLine();
          
          System.out.println("Enter phonenumber: "); 
          int phone = Integer.parseInt(reader.readLine());

          System.out.println("Enter email: "); 
          String email = reader.readLine();
          
        
          
          System.out.println("Enter password: "); 
          String password = reader.readLine();
          
          System.out.println("Choose tier. 1.Bronze 2.Silver 3.Gold 4.Two weeks trial. "); 
          int tier = Integer.parseInt(reader.readLine());
          
          int paymentMethod = 0; 
          
          if (tier == 1 || tier == 2 || tier == 3) {
          
          System.out.println("Choose payment method. 1.Creditcard 2.Invoice"); 
          paymentMethod = Integer.parseInt(reader.readLine());
          
          
          } 
          
	      
          try {
        	  
              
              sql = "INSERT INTO Members VALUES (?,?,?,?,?,?,?)";
              
             
              
              pstmt = conn.prepareStatement(sql);                
              
                 
              pstmt.setLong(1, pNr);
              pstmt.setString(2, fName);
              pstmt.setString(3, lName);
              pstmt.setInt(4, phone);
              pstmt.setString(5, email);
              pstmt.setString(6, password);
              pstmt.setInt(7, paymentMethod);
              
              
              pstmt.executeUpdate();

		
          } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
	        return;
        
     
          }	
          
          
         if (paymentMethod == 1) {
        	  
        	  System.out.println("Enter firstname on card: "); 
              String cFname = reader.readLine();
              
              System.out.println("Enter lastname on card: "); 
              String cLname = reader.readLine();
              
              System.out.println("Enter card number"); 
              String cardNumber = reader.readLine();
              
              System.out.println("Enter valid date: "); 
              String date = reader.readLine();
              
              System.out.println("Enter cvc-code: "); 
              int cvc = Integer.parseInt(reader.readLine());
              
              try {
                  
                  sql = "INSERT INTO CreditCardInformation VALUES (?,?,?,?,?,?)";

                  pstmt = conn.prepareStatement(sql);                
                  
                     
                  pstmt.setLong(1, pNr);
                  pstmt.setString(2, cFname);
                  pstmt.setString(3, cLname);
                  pstmt.setString(4, cardNumber);
                  pstmt.setString(5, date);
                  pstmt.setInt(6, cvc);
            
          
                  pstmt.executeUpdate();
    		
              } catch(SQLException e){
    	        
    	        System.out.print("\033[H\033[2J"); 
    	        System.out.println(" ");
    	        System.out.println(e.toString());
    	        
            
         
         }
               
          }
         
         try {
             
        	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        	 LocalDate localDate = LocalDate.now();
        	
        	 
        	 String date = dtf.format(localDate);
        	 String endDate = null;
        	 String status = "Active";
        	 
        	 if (tier == 4) {
        		 LocalDate trialEnd = localDate.plusDays(14);
        		 endDate = dtf.format(trialEnd);
        		 
        	 }

             sql = "INSERT INTO MembershipStatus VALUES (?,?,?,?,?)";

             
             pstmt = conn.prepareStatement(sql);                
             
                
             pstmt.setLong(1, pNr);
             pstmt.setInt(2, tier);
             pstmt.setString(3, date);
             pstmt.setString(4, endDate);
             pstmt.setString(5, status);
             pstmt.executeUpdate();


         } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
       
    
    }
          
          	
         


	
	}
	
	
	public static void cPhone(int pNr) throws IOException {
		
		
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC"; 
		
		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      int pnr = pNr;
		
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
             
	    	
	    	  
	    	  System.out.println("Enter new value:");
	    	  int value = Integer.parseInt(reader.readLine());             
	    	
             
             
             sql = "UPDATE Members SET PhoneNumber = ? WHERE Pnr = ?";
          		 
             
             pstmt = conn.prepareStatement(sql); 
             pstmt.setInt(1, value);
             pstmt.setInt(2, pnr);
             
             
             
          
             pstmt.executeUpdate();
             
             System.out.println("Information updated");
             
             
             
          conn.close();
          
          }
          catch(SQLException e) {
          
       	   System.out.println( e.toString() );
             
          }
		
		
		
		
	}
	


	public static void cEmail(int pNr) throws IOException {

		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC"; 
		
		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      int pnr = pNr;
		
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
             
	    	
	    	  
	    	  System.out.println("Enter new value:");
	    	  String value = reader.readLine();            
	    	
             
             
             sql = "UPDATE Members SET Email = ? WHERE Pnr = ?";
          		 
             
             pstmt = conn.prepareStatement(sql); 
             pstmt.setString(1, value);
             pstmt.setInt(2, pnr);
             
             
             
          
             pstmt.executeUpdate();
             
             System.out.println("Information updated");
             
             
             
          conn.close();
          
          }
          catch(SQLException e) {
          
       	   System.out.println( e.toString() );
             
          }
	}
	
	
	

	public static void pMembership() throws IOException { //Pause
		
	}
	
	public static void aMembership(int pNr) throws IOException  { //Activate
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC"; 
		
		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      
	      int pnr = pNr;
	      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
     	 LocalDate localDate = LocalDate.now();
     	
     	 
     	 String date = dtf.format(localDate);
		
	    
		
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

             sql = "UPDATE MembershipStatus SET StartDate = ?, Status = 'Active', EndDate = null WHERE Pnr = ?";
          		 
             
             pstmt = conn.prepareStatement(sql); 
             pstmt.setString(1, date);
             pstmt.setInt(2, pnr);
             
             
             
          
             pstmt.executeUpdate();
             
             System.out.println("Membership active");
             
             
             
          conn.close();
          
          }
          catch(SQLException e) {
          
       	   System.out.println( e.toString() );
             
          }
		
	}
	
	public static void cMembership(int pNr) throws IOException { //Cancel
		
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC"; 
		
		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      int pnr = pNr;
		
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
             
	    	
	    	  
	    	  System.out.println("Enter end date: ");
	    	  String value = reader.readLine();            
	    	
             
             
             sql = "UPDATE MembershipStatus SET EndDate = ?, Status = 'Canceled' WHERE Pnr = ?";
          		 
             
             pstmt = conn.prepareStatement(sql); 
             pstmt.setString(1, value);
             pstmt.setInt(2, pnr);
             
             
             
          
             pstmt.executeUpdate();
             
             System.out.println("Information updated");
             
             
             
          conn.close();
          
          }
          catch(SQLException e) {
          
       	   System.out.println( e.toString() );
             
          }
	      
	      
		
	}

	
	public static void cMemberShipTier(int pNr) throws IOException {
		
		final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessAB.db";  
		final String DRIVER = "org.sqlite.JDBC"; 
		
		
		  Connection conn = null;
	      PreparedStatement pstmt = null;
	      String sql = "";
	      int pnr = pNr;
		
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
             
	    	
	    	  
	    	  System.out.println("Choose tier. 1,2,3");
	    	  int tierId = Integer.parseInt(reader.readLine());             
	    	
             
             
             sql = "UPDATE MembershipStatus SET TierID = ? WHERE Pnr = ?";
          		 
             
             pstmt = conn.prepareStatement(sql); 
             
             pstmt.setInt(1, tierId);
             pstmt.setInt(2, pnr);
             
             
             
          
             pstmt.executeUpdate();
             
             System.out.println("Information updated");
             
             
             
          conn.close();
          
          }
          catch(SQLException e) {
          
       	   System.out.println( e.toString() );
             
          }
		
	}

}

