package br.edu.udc.sistemas.poo2_20161S.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.udc.sistemas.poo2_20161S.controller.Controller;

@WebServlet("/dispatcher")
public class Dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Dispatcher() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		    String action = request.getParameter("newAction");
			String entityName = request.getParameter("entityName");
			if ((action != null) && (!action.trim().equals("")) &&
				(entityName != null) && (!entityName.trim().equals(""))) {
			
				Class <?> controllerClass = Class.forName("br.edu.udc.sistemas.poo2_20161S.controller.Controller" + entityName);
				Controller controller = (Controller) controllerClass.newInstance();
				controller.execute(request,action);
				
				RequestDispatcher page = request.getRequestDispatcher((String) request.getAttribute("nextPage"));  
                page.forward(request, response);
	        }
		} catch(Exception e) {
			e.printStackTrace();
	        request.setAttribute("exception",e);
	        RequestDispatcher page = request.getRequestDispatcher("error.jsp");  
	        page.forward(request, response);
		}

	}

}
