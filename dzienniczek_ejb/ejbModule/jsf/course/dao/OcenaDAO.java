package jsf.course.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jsf.course.entities.Ocena;
import jsf.course.entities.User;
import jsf.course.entities.Zajęcia;
import jsf.course.dao.OcenaDAO;


@Stateless
public class OcenaDAO {
	@PersistenceContext
	EntityManager em;
	
	public void insert(Ocena ocena) {
		em.persist(ocena);
	}
	
	public Ocena update(Ocena ocena) {
		return em.merge(ocena);
	}

	public void delete(Ocena ocena) {
		em.remove(em.merge(ocena));
	}
	
	public Ocena get(Object id) {
		return em.find(Ocena.class, id);
	}
	

	
	public List<Ocena> getFullList(int iduser, String przedmiot) {
		
		String where = "where c.user.iduser = :iduser ";
		if (przedmiot != "") {
			where += "and c.zajęcia.przedmiot.nazwaPrzedmiotu like :przedmiot ";
		}		
		List<Ocena> lista = null;
		Query query = em.createQuery("SELECT c FROM Ocena c " + where + " ORDER BY c.zajęcia.przedmiot.nazwaPrzedmiotu, c.dataWystawienia ");
		query.setParameter("iduser", iduser);	
		if (przedmiot != "") {
			query.setParameter("przedmiot", przedmiot+"%");
		}
		
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public List<Ocena> getLista(User user, Zajęcia zajęcia) {

		List<Ocena> lista = null;
		Query query = em.createQuery("SELECT c FROM Ocena c where c.zajęcia = :zajecia and c.user = :user");
		query.setParameter("zajecia", zajęcia);
		query.setParameter("user", user);
//		Query query = em.createQuery("SELECT c FROM Ocena c ORDER BY c.zajęcia.przedmiot.nazwaPrzedmiotu, c.dataWystawienia ");
		
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	
	public Ocena getOcena(Object user) {
		return em.find(Ocena.class, user);
	}
		
	}
