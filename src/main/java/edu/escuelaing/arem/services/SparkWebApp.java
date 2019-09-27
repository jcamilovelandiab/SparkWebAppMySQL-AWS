package edu.escuelaing.arem.services;

import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class SparkWebApp {
    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/Beta", (req, res) -> inputDataPage(req, res));
    }

    private static String inputDataPage(Request req, Response res) {
        if(req.queryParams("value")!=null){
            Integer number=Integer.parseInt(req.queryParams("value"));
            return "The square of the given number is: "+ MathServices.square(number) ;
        }else{
            String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Square</h2>"
                + "<form action=\"/Beta\">"
                + "  Please. Insert the number:<br>"
                + "  <input type=\"text\" name=\"value\" value=\"\">"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "</body>"
                + "</html>";
            return pageContent;
        }
    }
    
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