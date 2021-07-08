package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

//generic:
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
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

	 protected String myParam = null;

	 //take param from web.xml:
	 public void init(ServletConfig servletConfig) throws ServletException{
		    this.myParam = servletConfig.getInitParameter("myParam");
		  }
	 
	 //using generic not http for example:
	 public void service(ServletRequest request, ServletResponse response)
		        throws ServletException, IOException {

		    response.getWriter().write("<html><body>myParam = " +
		            this.myParam + "</body></html>");
		  }
	 
	 
	 
	 
	 
	// when extends HttpServlet:
	
	 /* The HttpServlet class reads the HTTP request, and determines
	 *  if the request is an HTTP GET, POST, PUT, DELETE, HEAD etc.
	 *  and calls one the corresponding method.*/
	
	/*list of the methods you can override:
	 * {request processing methods}
	 * doGet(), doPost(), doHead()
doPut(), doDelete(), doOptions()
doTrace()
	 * all take 2 parameters: req and res
	 * */
	
	
	
	/*To respond to e.g. HTTP GET requests only,
	you will extend the HttpServlet class, 
	and override the doGet() method only*/
	protected void doGet( HttpServletRequest request,
			HttpServletResponse response)
            throws ServletException, IOException {
		
		// #REQUESTS :
		
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
        
        //When a user enters your site for the first time,
        //the user is given a unique ID to identify his session by.
        //This ID is typically stored in a cookie or in a request parameter.
       
        //Store values in the session object:    
        session.setAttribute("userName", "theUserName");
        //Values stored in the session object 
        //are stored in the memory of the servlet container.
        //To read the value again:
        String userName = (String) session.getAttribute("userName");
        //Session attributes are just available to a single user.
        
        
        //if a user's requests are divided evenly between the two servers
        //sometimes session values may be missing
        //so, Do not use session attributes.
        //Use a session database where session attributes are written&read from
        //or Use sticky session, 
        //where a user is always sent to the same server
        //throughout the whole session.

        	/*********/

        //ServletContext:meta information about the web application
        //access context parameters set in the [web.xml] file
        //forward the request to other servlets,
        //store application wide parameters in the ServletContext too
        ServletContext context = request.getSession().getServletContext();            
        context.setAttribute("someValue", "aValue");
        //access:
        Object attribute = context.getAttribute("someValue");
        
        
        //context parameters in web.xml:
        //which can be read from all servlets in the application:
        String myContextParam = request.getSession().getServletContext()
        		.getInitParameter("myParam");
        
        
        /*
         * ServletContext attributes are available to all servlets in your application,
         * and between requests and sessions. That means that the attributes are
         * available to all visitors of the web application.[Unlike sessions]
         * 
         * The ServletContext attributes are still stored in the memory of the servlet container.
         * So, same problems exists as does with the session attributes, in server clusters.
         * 
         * */
        
        
        //RESPONSES:
        
        //#1 Headers:
        //Headers must be set before any data is written to the response
        response.setHeader("Header-Name", "Header Value");
        

        //#2 Content-Type: {response header}
        //tells the browser the type of content you are sending back to it
        // HTML is text/html
        //plain text is text/plain
        response.setHeader("Content-Type", "text/html");
        
        //#3 Writing HTML:
        PrintWriter writer = response.getWriter();
        writer.write("<html><body>GET/POST response</body></html>");

        
        //#4 Writing Text:
        response.setHeader("Content-Type", "text/plain");
//        PrintWriter writer = response.getWriter();
        writer.write("This is just plain text");

        //#5 Content-Length:
        // tells the browser how many bytes your servlet is sending back
        //you need to set the content length header contentLength
        //if u are sending binary data back (not text)
        //ex: image, a PDF file or a Flash file... 
        response.setHeader("Content-Length", "31642");

        
        //#6 Writing Binary Data
        // First, set the Content-Type header
        //ex: PNG image is image/png, search for "mime types"
        //to write binary data back to the browser 
        //you cannot use the Writer obtained from response.getWriter()
        //Writer's are intended for text
        OutputStream outputStream = response.getOutputStream();
//        outputStream.write(...);
        
        //#7 Redirecting to a Different URL
        //redirect the browser to a different URL from your servlet
        //You cannot send any data back to the browser when redirecting
//        response.sendRedirect("http://jenkov.com");

        
//		response.getWriter().write("<html><body>GET response</body></html>");
	    doPost(request, response);
		}
	

	protected void doPost( HttpServletRequest request,
            HttpServletResponse response)
throws ServletException, IOException {

//response.getWriter().write("GET/POST response");

		
//RequestDispatcher:
		
		/*
		 * 
		 * The RequestDispatcher class enables your servlet 
		 * to "call" another servlet from inside another servlet. 
		 * The other servlet is called as if an HTTP request 
		 * was sent to it by a browser.
		 * 
		*/
		

		/*
		 * First: Obtains a RequestDispatcher:
		 * The code obtains a RequestDispatcher targeted at 
		 * whatever Servlet (or JSP) that is mapped to the URL /anotherUrl.simple.
		 * */
		
		RequestDispatcher requestDispatcher =
			    request.getRequestDispatcher("/anotherURL.simple");
			

		//Second: Call the RequestDispatcher: 
		//we can call the RequestDispatcher using either its include() or forward():
		requestDispatcher.forward(request, response);
//		requestDispatcher.include(request, response);
 		//Now, servlet container activates 
		//whatever Servlet is mapped to the URL of RequestDispatcher
		// The activated servlet has access to the same request as the servlet calling it, and will write to the same response as your current servlet. 
		//That way we can merge the output of servlets into a single repsonse.
	
	
	//forward() and include()
	/*
	 * 
	 * #1: Forward():
	 * intended for use in forwarding the request
	 * meaning after the response of the calling servlet has been committed
	 * You cannot merge response output using this method.
	 * 
	 * #2: Include():
	 * merges the response written by the calling servlet, and the activated servlet. 
	 * This way you can achieve "server side includes" using the include().[better for me]
	 * 
	 * 
	 * */
	
	
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