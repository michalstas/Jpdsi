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

import jsf.course.dao.KlasaDAO;
import jsf.course.entities.Klasa;

@Stateless
public class KlasaDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(Klasa klasa) {
		em.persist(klasa);
	}
	
	public Klasa update(Klasa klasa) {
		return em.merge(klasa);
	}

	public void delete(Klasa klasa) {
		em.remove(em.merge(klasa));
	}
	
	public Klasa get(Object id) {
		return em.find(Klasa.class, id);
	}
	
//	public String getKlasaFromDatabase(String login, String password) {
//		int wynik;
//		Query query = em.createQuery("SELECT u.iduser FROM User u WHERE u.login LIKE :login AND u.password LIKE :password");
//		query.setParameter("login", login);
//		query.setParameter("password", password);
//		wynik = (int)query.getResultList().get(0);
//		Query query2 = em.createQuery("SELECT u.grupa FROM Uczęszcza u WHERE u.uczęsczas.user.iduser LIKE :usr");
//		query2.setParameter("usr", wynik);
//		
//		return wynik;
//	}
}
