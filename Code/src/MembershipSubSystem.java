import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
          int pNr = Integer.parseInt(reader.readLine());
          
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
          
          System.out.println("Choose tier. 1,2,3 "); 
          int tier = Integer.parseInt(reader.readLine());
          
          
          System.out.println("Choose payment method. 1,2"); 
          int paymentMethod = Integer.parseInt(reader.readLine());

          
	      
          try {
        	  
              
              sql = "INSERT INTO Members VALUES (?,?,?,?,?,?,?)";
              
             
              
              pstmt = conn.prepareStatement(sql);                
              
                 
              pstmt.setInt(1, pNr);
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
                  
                     
                  pstmt.setInt(1, pNr);
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

             sql = "INSERT INTO MembershipStatus VALUES (?,?,?,?)";

             
             pstmt = conn.prepareStatement(sql);                
             
                
             pstmt.setInt(1, pNr);
             pstmt.setInt(2, tier);
             pstmt.setString(3, date);
             pstmt.setString(4, null);
             pstmt.executeUpdate();


         } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
       
    
    }
          
          	
         


	
	}
	
	
	
}
