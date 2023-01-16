package jsf.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

//import com.jsf.entities.Person;

import jsf.entities.Ocena;


@Stateless
public class OcenaDAO {
	
	private final static String UNIT_NAME = "jsfcourse-otPU";

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
	
	public List<Ocena> getList(Map<String, Object> searchParams) {
		List<Ocena> list = null;

		// 1. Build query string with parameters
		String select = "select p ";
		String from = "from Ocena p ";
		String where = "";
		String orderby = "order by p.czyja_ocena asc, p.idocena";

		// search for surname
		String czyja_ocena = (String) searchParams.get("czyja_ocena");
		if (czyja_ocena != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "p.czyja_ocena like :czyja_ocena ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (czyja_ocena != null) {
			query.setParameter("czyja_ocena", czyja_ocena+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
