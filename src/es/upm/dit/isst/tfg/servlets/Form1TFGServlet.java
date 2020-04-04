package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.dao.TFGDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

/**
 * Servlet implementation class Form1TFGServlet
 */
@WebServlet("/Form1TFGServlet")
public class Form1TFGServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Form1TFGServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
    	
    	String email = req.getParameter("email"); 
    	String password = req.getParameter("password");  
    	String name = req.getParameter("name");
    	String title = req.getParameter("titulo");  
    	String advisorEmail = req.getParameter("profesor");  
    	
    	Professor advisor = ProfessorDAOImplementation.getInstance().read(advisorEmail);  
    	
    	if( null != advisor ){  
    		TFG tfg = new TFG();  
    		tfg.setEmail(email);  
    		tfg.setPassword(password);  
    		tfg.setName(name);  
    		tfg.setTitle(title);  
    		tfg.setAdvisor(advisor);
    		tfg.setStatus(1);
    		
    		TFGDAOImplementation.getInstance().create(tfg);  
    		req.getSession().setAttribute("tfg", tfg);  
    	}   
    	getServletContext().getRequestDispatcher("/index.html").forward(req,resp);  
    }  

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
