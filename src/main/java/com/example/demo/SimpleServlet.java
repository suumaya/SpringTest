package com.example.demo;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

//The easiest way to implement this interface 
//is to extend either the class GenericServlet or HttpServlet

public class SimpleServlet extends GenericServlet {


	public void service(ServletRequest request, ServletResponse response)
	        throws ServletException, IOException {

	  String yesOrNoParam = request.getParameter("param");

	  if("yes".equals(yesOrNoParam) ){

	      response.getWriter().write(
	        "<html><body>You said yes!</body></html>");
	  }

	  if("no".equals(yesOrNoParam) ){
	    
	      response.getWriter().write(
	        "<html><body>You said no!</body></html>");
	  }
	}
	
	
	
}