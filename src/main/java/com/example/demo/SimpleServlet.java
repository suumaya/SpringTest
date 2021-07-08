package com.example.demo;

import java.io.IOException;
import java.io.InputStream;

//generic:
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//http:
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*The easiest way to implement this interface 
is to extend either the class GenericServlet or HttpServlet
javax.servlet.http.HttpServlet class is a slightly 
more advanced base class than the GenericServlet*/
public class SimpleServlet extends HttpServlet {

	// when extends HttpServlet:
	
	 /* The HttpServlet class reads the HTTP request, and determines
	 *  if the request is an HTTP GET, POST, PUT, DELETE, HEAD etc.
	 *  and calls one the corresponding method.*/
	
	/*To respond to e.g. HTTP GET requests only,
	you will extend the HttpServlet class, 
	and override the doGet() method only*/
	protected void doGet( HttpServletRequest request,
			HttpServletResponse response)
            throws ServletException, IOException {
		//#1 HTTP GET request: in the query string
		//#2 HTTP POST request: in the body part of the http request
		// both same code
		String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");
        
        // headers like browser and supported file types:
        //#1 HTTP GET request: the Content-Length header not used = null
      	//#2 HTTP POST request: num of bytes sent in the HTTP request body
        String contentLength = request.getHeader("Content-Length");    
        
        //InputStream in POST request:
        //request parameters and [other potential data]
        // is sent to the server in the HTTP request body
        // other data like a file or a SOAP request (web service request)
        //to obtain an InputStream pointing to the HTTP request body:
        InputStream requestBodyInput = request.getInputStream();    
        //** NOTE: 
        //call this method before calling any getParameter() method
        //because calling the getParameter() method on an HTTP POST request 
        // will cause the servlet engine to parse the HTTP request body for parameters 
        //Once parsed, you cannot access the body as a raw stream of bytes anymore
        //
        //What you do with the data read from the InputStream is up to you. 
        //The servlet engine does not help you parse or interpret that data.
        //You just get it raw.
        
        //Seassion: hold information about a given user, between requests
        //session object during one request
        //make it available to read during any subsequent requests
        // within the same session time scope
        HttpSession session = request.getSession();

        //ServletContext:meta information about the web application
        //access context parameters set in the web.xml file
        //forward the request to other servlets,
        //store application wide parameters in the ServletContext too
        ServletContext context = request.getSession().getServletContext();            

        
//		response.getWriter().write("<html><body>GET response</body></html>");
	    doPost(request, response);
		}
	
	/*list of the methods you can override:
	 * doGet(), doPost(), doHead()
doPut(), doDelete(), doOptions()
doTrace()
	 * 
	 * */
	
	protected void doPost( HttpServletRequest request,
            HttpServletResponse response)
throws ServletException, IOException {

response.getWriter().write("GET/POST response");
}
	
	
	// when extends GenericService:
//	public void service(ServletRequest request, ServletResponse response)
//	        throws ServletException, IOException {
//
//	  String yesOrNoParam = request.getParameter("param");
//
//	  if("yes".equals(yesOrNoParam) ){
//
//	      response.getWriter().write(
//	        "<html><body>You said yes!</body></html>");
//	  }
//
//	  if("no".equals(yesOrNoParam) ){
//	    
//	      response.getWriter().write(
//	        "<html><body>You said no!</body></html>");
//	  }
	}
	
	
	
}