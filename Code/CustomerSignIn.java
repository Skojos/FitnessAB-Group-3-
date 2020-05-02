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
           }
         
     }
      

      System.out.println("Sign in sucessfull");
      
      System.out.println("Welcome " + user);
