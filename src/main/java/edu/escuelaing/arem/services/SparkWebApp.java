package edu.escuelaing.arem.services;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

import java.util.ArrayList;
import java.util.List;

public class SparkWebApp {
    /**
     * This main method uses SparkWeb static methods and lambda functions to create
     * a simple Hello World web app. It maps the lambda function to the /hello
     * relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/survey", (req, res) -> inputDataPage(req, res));
        get("/users", (req, res) -> getUsers(req, res));
    }


    private static String inputDataPage(Request req, Response res) {
        String name = req.queryParams("fullName");
        String email = req.queryParams("email");
        String description = req.queryParams("description");
        
        String pageContent = header
        		+ "<body>";
        if (name != null && email != null && description != null) {
            User user = new User(name, email, description);
            try {
				UserServices.save(user);
				pageContent = pageContent
				+ "<h3>your information has been successfully saved</h3>"
				+ "<a href='/users'>Click to see all users.</a>";
			} catch (ApplicationException e) {
				pageContent = pageContent
				+ "<h3>Your information could not be saved.</h3>"
				+ "<a href='/survey'>Please try again.</a>";
			}
        } else {
            pageContent = pageContent
            		+ "		<ul class=\'nav\'>\r\n"
                    + "        <li class=\'nav-item\'>\r\n"
                    + "            <a class=\'nav-link\' href=\'survey\'>Survey</a>\r\n"
                    + "        </li>\r\n"
                    + "        <li class=\'nav-item\'>\r\n"
                    + "            <a class=\'nav-link\' href=\'users\'>Users</a>\r\n"
                    + "        </li>"
                    + "		</ul>"
                    +	"<div class='container'>"
            		+ 	"<h2>Survey</h2> </br>"
                    + 	"<form action=\"/survey\">" + "  Please. Fill out the survey:<br>"
                    + 		"<div class='form-group'>\r\n"
                    +		"	<label for='email'>Email address</label>\r\n"
                    +       "	<input name='email' type=\"email\" class='form-control' id='email' aria-describedby='emailHelp' placeholder='Enter email'>\r\n" 
                    + 		"</div>\r\n"
                    + 		"<div class='form-group'>\r\n"
                    +		"	<label for='fullName'>Full Name</label>\r\n"
                    +       "	<input name='fullName' type=\"text\" class='form-control' id='fullName' placeholder='Enter full name'>\r\n" 
                    + 		"</div>\r\n"
                    +		"<div class='form-group'>\r\n"
                    +		"	<label for='description'>Describe yourself. Say your opinion about something:</label>\r\n"
                    +       "	<input name='description' type=\"text\" class='form-control' id='description' placeholder='Enter whatever you want.'>\r\n" 
                    + 		"</div>\r\n"
                    + 	"  <button type=\'submit\' class=\'btn btn-primary\'>Submit</button>"
                    +	"</form>";
        }
        pageContent = pageContent
        + 	"</body>"
        + "</html>";
        return pageContent;
    }

    private static String getUsers(Request req, Response res) {
        String email = req.queryParams("email");
        String pageContent = header
        + "<body>"
        + "	<ul class=\'nav\'>\r\n"
        + "        <li class=\'nav-item\'>\r\n"
        + "            <a class=\'nav-link\' href=\'survey\'>Survey</a>\r\n"
        + "        </li>\r\n"
        + "        <li class=\'nav-item\'>\r\n"
        + "            <a class=\'nav-link\' href=\'users\'>Users</a>\r\n"
        + "        </li>"
        + "		</ul>"
        +	"<div class='container'>";
        if (email != null) {
            User user;
            try {
                user = UserServices.getUserByEmail(email);
                if(user.getEmail()==null) {
                	pageContent = pageContent
                    + "<h2> USER NOT FOUND </h2>"
                    + "<a href='/users'> Click here to go back.</a>";
                }else {
                	pageContent = pageContent
                    + "<h2>"+user.getName()+"</h2>"
                    + "<p>Email: "+user.getEmail()+"</p>"
                    + "<p>Description: "+user.getDescription()+"</p>"
                    + "<a href='/users'> Click here to go back.</a>";
                }
            } catch (ApplicationException e) {
                e.printStackTrace();
            }
            
            
        }else{
            pageContent = pageContent
            + "<h2>Get information from user</h2>"
            + "<form action=\"/users\">"
            + "  <p>Email: </p><input type=\"text\" name=\"email\" value=\"\">"
            + "  <input type=\"submit\" value=\"Submit\">"
            + "</form>";
            pageContent = pageContent
            + "</br></br>"
            + "<h3>All users</h3>"
            + "<table class='table'>"
            + 	"<thead>"
            + 		"<tr>"
            + 			"<th scope='col'>#</th>"
            + 			"<th scope='col'>Full Name</th>"
            + 			"<th scope='col'>Email</th>"
            + 			"<th scope='col'>Description</th>"
            + 		"</tr>"
            + 	"</thead>"
            + "<tbody>";
            
            List<User> userList = new ArrayList<User>();
            try {
    			userList = UserServices.getUsers();
    		} catch (ApplicationException e) {
    		}
            for (int i=0; i<userList.size(); i++) {
    			pageContent = pageContent
    			+ "<tr>"
    		    + 	"<th scope='row'>"+(i+1)+"</th>"
    		    + 	"<td>"+userList.get(i).getName()+"</td>"
    		    + 	"<td>"+userList.get(i).getEmail()+"</td>"
    		    + 	"<td>"+userList.get(i).getDescription()+"</td>"
    		    + "</tr>";
    		}
            pageContent = pageContent
            +  "</tbody>";
        }

        
        pageContent = pageContent
        + "</div>"
        + "</body>"
        + "</html>";
        return pageContent;
    }
    
    private static String header = "<!DOCTYPE html>\r\n" + 
			"<html lang=\"en\">\r\n" + 
			"<head>\r\n" + 
			"  <title>Architectural patterns</title>\r\n" + 
			"  <meta charset=\"utf-8\">\r\n" + 
			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
			"  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\r\n" + 
			"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
			"  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\r\n" + 
			"  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\r\n" + 
			"</head>";
    
    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}