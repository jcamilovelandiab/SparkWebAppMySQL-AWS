/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arem.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

/**
 *
 * @author Juan Camilo Velandia Botello
 */
public class UserServices {

    public static void save(User user) throws ApplicationException {
        Connection connection = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://architecturalpatterns.crr6pg3w0mcr.us-east-1.rds.amazonaws.com:3306/architecturalpatterns", "admin", "admin123");
            Statement stmt = connection.createStatement();
            String query = "insert users(email, name, description) values('"+user.getEmail()+"','"+user.getName()+"','"+user.getDescription()+"')";
            stmt.executeUpdate(query);
            connection.close();
        } catch (Exception e) {
        	e.printStackTrace();
            throw new ApplicationException(e.getMessage());
        } finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ApplicationException(e.getMessage());
			}
		}
    }

    public static User getUserByEmail(String email) throws ApplicationException {
        User user = new User();
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://architecturalpatterns.crr6pg3w0mcr.us-east-1.rds.amazonaws.com:3306/architecturalpatterns", "admin", "admin123");
            Statement stmt = connection.createStatement();
            String query = "select email, name, description from users where email = '"+email+"'";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setDescription(rs.getString("description"));
            }
            connection.close();
            return user;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        } finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ApplicationException(e.getMessage());
			}
		}
            
    }

    public static List<User> getUsers() throws ApplicationException{
    	List<User> userList = new ArrayList<User>();
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://architecturalpatterns.crr6pg3w0mcr.us-east-1.rds.amazonaws.com:3306/architecturalpatterns", "admin", "admin123");
            Statement stmt = connection.createStatement();
            String query = "select email, name, description from users";
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
            	User user = new User();
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
                user.setDescription(rs.getString("description"));
                userList.add(user);
            }
            connection.close();
            return userList;
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage());
        } finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new ApplicationException(e.getMessage());
			}
		}
    }

}