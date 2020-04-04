package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.TFG;

public class TFGDAOImplementation implements TFGDAO {

	private static  TFGDAOImplementation instance = null;
	
	private TFGDAOImplementation() {
	}

	public static TFGDAOImplementation getInstance() {
		if( null == instance ) 
			instance = new TFGDAOImplementation();
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void create(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save( tfg );
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TFG read(String email) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		TFG tfg = session.load( TFG.class, email );
		session.getTransaction().commit();
		session.close();
		return tfg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate( tfg );
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete( tfg);
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TFG> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Collection<TFG> tfg = session.createQuery( "from TFG" ).list();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("------ TFGDAO readAll() ------ size = " + tfg.size() );
		
		return tfg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TFG login(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		TFG tfg = null;
		session.beginTransaction();
		Query q = session.createQuery("select t from TFG t where t.email = :email and t.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		List<TFG> tfgs = q.getResultList();
		if (tfgs.size() > 0) {
			tfg = (TFG) (q.getResultList().get(0));
		}
		session.getTransaction().commit();
		session.close();
		
		return tfg;
	}

}
