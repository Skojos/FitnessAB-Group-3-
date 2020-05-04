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

public class RegisterCustomer {

	public static void register() throws IOException {
		
			final String DB_URL = "jdbc:sqlite://Users/jonasskoog/Documents/GitHub/FitnessAB-Group-3-/Code/DatabaseDesign/fitnessab.db";  
		
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
          
          System.out.println("Enter StreetAddress: "); 
          String street = reader.readLine();
          
          
          System.out.println("Enter zip code: "); 
          int zipCode = Integer.parseInt(reader.readLine());
          
          System.out.println("Enter district: "); 
          String district = reader.readLine();
          
          
          System.out.println("Enter email: "); 
          String email = reader.readLine();
          
          System.out.println("Enter phonenumber: "); 
          int phone = Integer.parseInt(reader.readLine());
          
          System.out.println("Enter password: "); 
          String password = reader.readLine();
          
          System.out.println("Choose tier. 1,2,3 "); 
          int tier = Integer.parseInt(reader.readLine());
          
          
          System.out.println("Choose payment method. 1,2"); 
          int paymentMethod = Integer.parseInt(reader.readLine());

          
	      
          try {
              
              sql = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?,?)";
              
             
              
              pstmt = conn.prepareStatement(sql);                
              
                 
              pstmt.setInt(1, pNr);
              pstmt.setString(2, fName);
              pstmt.setString(3, lName);
              pstmt.setString(4, street);
              pstmt.setInt(5, zipCode);
              pstmt.setString(6, district);
              pstmt.setString(7, email);
              pstmt.setString(8, password);
              pstmt.setInt(9, phone);
              pstmt.setInt(10, paymentMethod);
              
              
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
        	 System.out.println(dtf.format(localDate)); //2016/11/16
        	 
        	 String date = dtf.format(localDate);
        	 int status = 1;
        	 
             sql = "INSERT INTO Membership VALUES (?,?,?,?,?)";
             
            
             
             pstmt = conn.prepareStatement(sql);                
             
                
             pstmt.setInt(1, pNr);
             pstmt.setString(2, date);
             pstmt.setString(3, null);
             pstmt.setInt(4, tier);
             pstmt.setInt(5, status);
             
       
             
             
             pstmt.executeUpdate();

             
             
  
		
         } catch(SQLException e){
	        
	        System.out.print("\033[H\033[2J"); 
	        System.out.println(" ");
	        System.out.println(e.toString());
       
    
    }
          
          	
         


	
	}
	
	
	
}
