package jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jsf.entities.Ocena;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class OcenaDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Ocena ocena) {
		em.persist(ocena);
	}

	public Ocena merge(Ocena ocena) {
		return em.merge(ocena);
	}

	public void remove(Ocena ocena) {
		em.remove(em.merge(ocena));
	}

	public Ocena find(Object id) {
		return em.find(Ocena.class, id);
	}

	public List<Ocena> getFullList() {
		List<Ocena> list = null;

		Query query = em.createQuery("select p from Ocena p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}


}
