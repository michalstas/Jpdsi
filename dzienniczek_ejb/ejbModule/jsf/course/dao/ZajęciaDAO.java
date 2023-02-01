package jsf.course.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import jsf.course.entities.Ocena;
import jsf.course.entities.Zajęcia;
import jsf.course.entities.User;
import jsf.course.dao.ZajęciaDAO;


@Stateless
public class ZajęciaDAO {
	@PersistenceContext
	EntityManager em;
	
	public Zajęcia get(Object id) {
		return em.find(Zajęcia.class, id);
	}
	
	
	public List<Ocena> getLista() {
		List<Ocena> lista = null;
		Query query = em.createQuery("SELECT s FROM Zajęcia s");
		
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Zajęcia> getFullList(int iduser, String data) {
		List<Zajęcia> lista = null;
		Query query = em.createQuery("SELECT c FROM Zajęcia c where c.user.iduser = :iduser and c.data like :data ORDER BY c.godzina");
		query.setParameter("iduser", iduser);
		query.setParameter("data", data+"%");	
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public List<Zajęcia> getList(String data) {
		List<Zajęcia> lista = null;
		Query query = em.createQuery("SELECT c FROM Zajęcia c where c.data like :data ORDER BY c.godzina");
		query.setParameter("data", data+"%");	
		try {
			lista = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
		
	}
