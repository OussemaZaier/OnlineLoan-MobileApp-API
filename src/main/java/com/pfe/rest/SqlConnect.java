package com.pfe.rest;


import java.sql.*;
public class SqlConnect {

	public static void main(String[] args) {
		//SqlConnect c= new SqlConnect();
		//System.out.print(c.Connect());
		}
	public  String Connect () {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  

			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:TEST","SYSTEM","12345");
	             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM USERS");
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt("ID");
            int cin = resultSet.getInt("CIN");
            //String password = resultSet.getString("PWD");
            //System.out.println(id);
            //System.out.println(cin);
            //System.out.println(password);
            
            return( id + " " + cin)   ;
    
		    }catch (SQLException e) {
                //System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
                return e.getSQLState() + e.getMessage();
            } catch (Exception e) {
                return e.getMessage();
            }
	}
}
//SET GLOBAL time_zone = '+3:00';


