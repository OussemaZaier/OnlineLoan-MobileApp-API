package com.pfe.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pfe.Entity.User;

import jakarta.annotation.security.PermitAll;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@HeaderParam("CIN") String cin) {

    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+cin;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
			
				User user=new User();
				
				user.setCIN(resultSet.getString("CIN"));
			    user.setName(resultSet.getString("USERNAME"));
			    user.setPhoneNumber(resultSet.getString("TEL"));

			    return Response.ok(user, MediaType.APPLICATION_JSON).build();
           
            }
			else 
			{
				return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for CIN: " + cin).build();
			}
			
    	}
    
    	
    	catch( Exception e ) {
    		return Response.status(Response.Status.NOT_FOUND).entity("server").build();
    	}
    }
    
    @POST
    @Path("/exist")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String userExist (@HeaderParam("CIN") String cin) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+cin;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {

			    return "1";
           
            }
			else 
			{
				return "2";
			}
			
    	}
    
    	
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
    }
    
    @POST
    @Path("/authenticate")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public String authenticate (@HeaderParam("CIN") String cin, @HeaderParam("Password") String pwd) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+cin;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
			
            String pwd1 = resultSet.getString("PASSWORD");
			
            	if(pwd1.equals(pwd)) 
					{
						return "1";
					}
            	else 
    			{
    				return "2";
    			}
            }
			else 
			{
				return "2";
			}
			
    	}
    
    	
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    @PUT
    @Path("/create")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String create (@HeaderParam("CIN") String cin,@HeaderParam("TEL") String tel, @HeaderParam("Password") String pwd, @HeaderParam("Username") String Username) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+cin;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				return "user exist";
				
			}else {
				 statement="insert into USERS (cin,tel,password,username)" + " values(?,?,?,?)";
				 
				 preparedStatement = conn.prepareStatement(statement);
				 int tel1=Integer.parseInt(tel);
				 preparedStatement.setString(1,cin);
				 preparedStatement.setInt(2,tel1);
				 preparedStatement.setString(3,pwd);
				 preparedStatement.setString(4,Username);
				 
				 resultSet = preparedStatement.executeQuery();
				 return "succes";
			}
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    
    @PUT
    @Path("/update")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String update (@HeaderParam("UserCIN") String userCIN,@HeaderParam("CIN") String cin,@HeaderParam("TEL") String tel, @HeaderParam("Username") String Username) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+userCIN;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				statement="UPDATE USERS SET cin=? , tel=? , username=? where CIN="+userCIN;
				 
				 preparedStatement = conn.prepareStatement(statement);
				 int tel1=Integer.parseInt(tel);
				 preparedStatement.setString(1,cin);
				 preparedStatement.setInt(2,tel1);
				 preparedStatement.setString(3,Username);
				 
				 resultSet = preparedStatement.executeQuery();
				return "success";
				
			}else {
				 
				 return "user doesnt exist";
			}
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    @PUT
    @Path("/updatePWD")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePWD (@HeaderParam("UserCIN") String userCIN, @HeaderParam("oldPWD") String oldPWD, @HeaderParam("newPWD") String newPWD) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM USERS WHERE CIN="+userCIN;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				String ch=resultSet.getString("PASSWORD");
				if(ch.equals(oldPWD)) {
					statement="UPDATE USERS SET PASSWORD=? where CIN="+userCIN;
					 
					 preparedStatement = conn.prepareStatement(statement);
					 preparedStatement.setString(1,newPWD);
					 
					 resultSet = preparedStatement.executeQuery();
					return "success";
				}
				else {
					return "wrong";
				}
				
			}else {
				 
				 return "user doesnt exist";
			}
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    @POST
    @Path("/sendOTP")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public String sendOTP (@HeaderParam("phone") String phone, @HeaderParam("code") String code) 
    {
        
    	try {
    		SHA.send(phone,code);
    		return "succed";
    	}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    
}
