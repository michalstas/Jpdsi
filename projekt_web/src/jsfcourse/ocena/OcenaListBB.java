package jsfcourse.ocena;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import jsf.dao.OcenaDAO;
import jsf.entities.Ocena;

@Named
@RequestScoped
public class OcenaListBB {
	private static final String PAGE_OCENA_EDIT = "ocenaEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String czyja_ocena;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	OcenaDAO ocenaDAO;
		
	public String getczyja_ocena() {
		return czyja_ocena;
	}

	public void setczyja_ocena(String czyja_ocena) {
		this.czyja_ocena = czyja_ocena;
	}

	public List<Ocena> getFullList(){
		return ocenaDAO.getFullList();
	}

	public List<Ocena> getList(){
		List<Ocena> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (czyja_ocena != null && czyja_ocena.length() > 0){
			searchParams.put("czyja_ocena", czyja_ocena);
		}
		
		//2. Get list
		list = ocenaDAO.getList(searchParams);
		
		return list;
	}

	public String newOcena(){
		Ocena ocena = new Ocena();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("ocena", ocena);
		
		return PAGE_OCENA_EDIT;
	}

	public String editOcena(Ocena ocena){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("ocena", ocena);
		
		return PAGE_OCENA_EDIT;
	}

	public String deleteOcena(Ocena ocena){
		ocenaDAO.remove(ocena);
		return PAGE_STAY_AT_THE_SAME;
	}
}
