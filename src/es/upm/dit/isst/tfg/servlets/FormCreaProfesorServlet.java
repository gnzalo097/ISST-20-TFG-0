package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;

/**
 * Servlet implementation class FormCreaProfesorServlet
 */
@WebServlet("/FormCreaProfesorServlet")
public class FormCreaProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormCreaProfesorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	@Override  
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  throws ServletException, IOException {  
		
		//COGE PARAMETROS DEL FORMULARIO
		String email = req.getParameter("email");  
		String password = req.getParameter("password");  
		String name = req.getParameter("name");
		
		//ASIGNA PARAMETROS AL NUEVO PROFESOR
		Professor professor = new Professor();  
		professor.setEmail(email);  
		professor.setPassword(password);  
		professor.setName(name);  

		//METE PROFESOR EN BBDD
		ProfessorDAOImplementation.getInstance().create(professor);  
		List<Professor> lp = new ArrayList<Professor>();  
		lp.addAll((List<Professor>) req.getSession().getAttribute("profesores"));  
		lp.add (professor);  
		req.getSession().setAttribute("profesores", lp);
		
		//MANDA DATOS A LA VISTA - TODA LA LISTA DE PROFESORES
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);  
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
