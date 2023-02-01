package jsf.course.dao;

import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jsf.course.dao.UserDAO;
import jsf.course.entities.User;
import jsf.course.entities.Zajęcia;

@Stateless
public class UserDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(User user) {
		em.persist(user);
	}
	
	public User update(User user) {
		return em.merge(user);
	}

	public void delete(User user) {
		em.remove(em.merge(user));
	}
	
	public User get(Object id) {
		return em.find(User.class, id);
	}
	
	public User getUserFromDatabase(String login, String password) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.login LIKE :login AND u.password LIKE :password");
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			return (User) query.getResultList().get(0);
		} catch (Exception e) {	}
		
		return null;
	}
	public User getUserFromDatabase2(String login) {
		Query query = em.createQuery("SELECT u FROM User u WHERE u.login LIKE :login");
		query.setParameter("login", login);
		try {
			return (User) query.getResultList().get(0);
		} catch (Exception e) {	}
		
		return null;
	}
	
	
	public List<User> getList(Map<String, Object> searchParams) {
		List<User> listt = null;

		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.nazwisko asc, u.imie";

		String nazwisko = (String) searchParams.get("nazwisko");
		if (nazwisko != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.nazwisko like :nazwisko ";
		}
	
		Query query = em.createQuery(select + from + where + orderby);

		if (nazwisko != null) {
			query.setParameter("nazwisko", nazwisko+"%");
		}

		try {
			listt = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listt;
	}
	
	public User getClientInfo(Object user) {
		return em.find(User.class, user);
	}
	
	public List<User> getFullList(Zajęcia zajęcia) {
		List<User> lista = null;
//		Query query = em.createQuery("SELECT c FROM User c where c.zajęcias = :zajęcia");
//		query.setParameter("zajęcia", zajęcia);
		Query query = em.createQuery("SELECT c FROM User c");

		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}
