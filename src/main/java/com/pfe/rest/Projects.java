package com.pfe.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.pfe.Entity.Project;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("projects")
public class Projects {
	@PUT
    @Path("/add")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String create (@HeaderParam("name") String name,@HeaderParam("description") String description, @HeaderParam("type") String type, @HeaderParam("surface") String surface,@HeaderParam("user") String user) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement;
	        PreparedStatement preparedStatement;
			ResultSet resultSet ;
				 statement="insert into PROJECTS (NAME_PROJECT,DESCRIPTION_PROJECT,TYPE_PROJECT,SURFACE_PROJECT,USER_ID)" + " values(?,?,?,?,?)";
				 
				 int type1=Integer.parseInt(type);
				 float surface1=Float.parseFloat(surface);
				 
				 preparedStatement = conn.prepareStatement(statement);

				 preparedStatement.setString(1,name);
				 preparedStatement.setString(2,description);
				 preparedStatement.setInt(3,type1);
				 preparedStatement.setFloat(4,surface1);
				 preparedStatement.setString(5,user);
				 
				 resultSet = preparedStatement.executeQuery();
				 return "succes";
			
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
	@GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@HeaderParam("CIN") String cin) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM PROJECTS WHERE USER_ID="+cin+"ORDER BY ID_PROJECT DESC";
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				ArrayList<Project> projects = new ArrayList<Project>();
				do {
			        projects.add(new Project(resultSet.getString("ID_PROJECT"),resultSet.getString("NAME_PROJECT"),resultSet.getString("DESCRIPTION_PROJECT")));
			      } while (resultSet.next());
				

			    return Response.ok(projects, MediaType.APPLICATION_JSON).build();
           
            }else {
            	return Response.status(Response.Status.NOT_FOUND).entity("server").build();
            }
			
			
    	}
    
    	
    	catch( Exception e ) {
    		return Response.status(Response.Status.NOT_FOUND).entity("server").build();
    	}

    }
    @GET
    @Path("/getGI")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGeneralInfo(@HeaderParam("ID") String ID) {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM PROJECTS WHERE ID_PROJECT="+ID;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
					Project project=new Project(resultSet.getString("ID_PROJECT"),resultSet.getString("NAME_PROJECT"),resultSet.getString("DESCRIPTION_PROJECT"),resultSet.getInt("TYPE_PROJECT"),resultSet.getFloat("SURFACE_PROJECT"));
					return Response.ok(project, MediaType.APPLICATION_JSON).build();   
            }
			else {
				return Response.status(Response.Status.NOT_FOUND).entity("server").build();
			}
			
    	}
    
    	
    	catch( Exception e ) {
    		return Response.status(Response.Status.NOT_FOUND).entity("server").build();
    	}

    }
    @PUT
    @Path("/updateGI")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String update (@HeaderParam("ID") String ID,@HeaderParam("name") String name,@HeaderParam("desc") String desc,@HeaderParam("surface") String surface) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="SELECT * FROM PROJECTS WHERE ID_PROJECT="+ID;
	        PreparedStatement preparedStatement = conn.prepareStatement(statement);
			
			ResultSet resultSet = preparedStatement.executeQuery();


			if(resultSet.next()) {

				 statement="UPDATE PROJECTS SET NAME_PROJECT=? , DESCRIPTION_PROJECT=?, SURFACE_PROJECT=? where ID_PROJECT="+ID;
				 preparedStatement = conn.prepareStatement(statement);
				 float surface1=Float.parseFloat(surface);
				 preparedStatement.setString(1,name);
				 preparedStatement.setString(2,desc);
				 preparedStatement.setFloat(3,surface1);
				 resultSet = preparedStatement.executeQuery();
				return "success";
				
			}else {
				 
				 return "project doesnt exist";
			}
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    @DELETE
    @Path("/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String delete (@HeaderParam("ID") String ID) 
    {try {
		Class.forName("oracle.jdbc.driver.OracleDriver");  

		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
		String statement="SELECT * FROM PROJECTS WHERE ID_PROJECT="+ID;
        PreparedStatement preparedStatement = conn.prepareStatement(statement);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			statement="delete from projects where ID_PROJECT="+ID;
			preparedStatement = conn.prepareStatement(statement);
			
			resultSet = preparedStatement.executeQuery();
		    return "1";
       
        }
		else 
		{
			return "2";
		}
		
	}

	
	catch( Exception e ) {
		return e.getMessage().toString();
	}}
    @PUT
    @Path("/create")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    public String create (@HeaderParam("image") String Image) 
    {
    	try {
    		Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
			String statement="insert into TEST (image)" + "values(?)";
	        PreparedStatement preparedStatement = conn.prepareStatement(statement); 

	        preparedStatement.setString(1,Image);
			ResultSet resultSet = preparedStatement.executeQuery();
				 return "succes";
			
    		}
    	catch( Exception e ) {
    		return e.getMessage().toString();
    	}
		
    }
    }