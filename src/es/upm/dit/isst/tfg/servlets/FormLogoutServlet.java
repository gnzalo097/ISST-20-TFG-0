package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FormLogoutServlet
 */
@WebServlet("/FormLogoutServlet")
public class FormLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormLogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override  
    protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {  
    	req.getSession().removeAttribute("admin");  
    	req.getSession().removeAttribute("tfgs");  
    	req.getSession().removeAttribute("tfg");  
    	req.getSession().removeAttribute("profesor");  
    	req.getSession().removeAttribute("profesores");
    	req.getSession().invalidate();
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
