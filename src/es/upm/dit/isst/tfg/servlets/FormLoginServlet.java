package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.Collection;

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
 * Servlet implementation class FormLoginServlet
 */
@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ADMIN_EMAIL = "";
	private final String ADMIN_PASSWORD = "";	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		System.out.println("email: " + email);
		System.out.println("password: " + password);
		
		Collection<Professor> profesores = ProfessorDAOImplementation.getInstance().readAll();
		Collection<TFG> tfgs = TFGDAOImplementation.getInstance().readAll();
		
		//System.out.println("tfgs[empty: " + tfgs.isEmpty() + " / size: " + tfgs.size() + "]");
        //System.out.println("profesores[empty: " + profesores.isEmpty() + " / size: " + profesores.size()+ "]");
		
		TFG tfg = TFGDAOImplementation.getInstance().login(email, password);
		Professor professor =ProfessorDAOImplementation.getInstance().login(email, password);
		
		
        
		
        if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
        	System.out.println("-------[LOGIN ADMIN]-------");
        	req.getSession().setAttribute("admin", true);
        	req.getSession().setAttribute("profesores", profesores);
        	req.getSession().setAttribute("tfgs", tfgs);
        	getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
        	
        } else if ( null != tfg ) {
        	System.out.println("-------[LOGIN TFG]-------");
        	System.out.println("tfg [email: " + tfg.getEmail() + "]");
        	
        	req.getSession().setAttribute("tfg", tfg);
        	req.getSession().setAttribute("email", email);
        	
        	getServletContext().getRequestDispatcher("/TFG.jsp").forward(req,resp);

        } else if ( null != professor ) {
        	System.out.println("-------[LOGIN PROFESSOR]-------");
        	System.out.println("professor [email: " + professor.getEmail() + "]");
        	
        	req.getSession().setAttribute("profesor", ProfessorDAOImplementation.getInstance().read(professor.getEmail()));
        	req.getSession().setAttribute("advisedTFGs", ProfessorDAOImplementation.getInstance().read(professor.getEmail()).getAdvisedTFGs());
     
        	getServletContext().getRequestDispatcher("/Profesor.jsp").forward(req,resp);
        	

        } else	{
        	System.out.println("-------[SALIDA]-------");
        	getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
