package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

public class ProfessorDAOImplementation implements ProfessorDAO {

	private static  ProfessorDAOImplementation instance = null;

	private ProfessorDAOImplementation() {
	}

	public static ProfessorDAOImplementation getInstance() {
		if( null == instance ) 
			instance = new ProfessorDAOImplementation();
		return instance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void create(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save( professor );
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Professor read(String email) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Professor professor = session.load( Professor.class, email );
		session.getTransaction().commit();
		session.close();
		return professor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate( professor );
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Professor professor) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete( professor);
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Professor> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<Professor> professor = session.createQuery( "from Professor" ).list();
		session.getTransaction().commit();
		session.close();
		return professor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Professor login(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		Professor professor = null;
		session.beginTransaction();
		Query q = session.createQuery("select t from Professor t where t.email = :email and t.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		List<TFG> professors = q.getResultList();
		if (professors.size() > 0) {
			professor = (Professor) (q.getResultList().get(0));
		}
		session.getTransaction().commit();
		session.close();
		return professor;
	}

}
