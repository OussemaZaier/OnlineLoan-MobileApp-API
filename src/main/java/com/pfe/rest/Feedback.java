package com.pfe.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("feedback")
public class Feedback {
@PUT
    @Path("/create")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String create (@HeaderParam("desc") String desc, @HeaderParam("type") String type,@HeaderParam("ID_USER") String cin) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="insert into Feedback (DESCRIPTION_HELP,TYPE_HELP,ID_USER)" + " values(?,?,?)";
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);

			 int cin1=Integer.parseInt(cin);
			 preparedStatement.setString(1,desc);
			 preparedStatement.setString(2,type);
			 preparedStatement.setInt(3,cin1);
			 
			 ResultSet resultSet = preparedStatement.executeQuery();
			 
			return "succes";
			
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
}