package jsfcourse.ocena;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import jsf.dao.OcenaDAO;
import jsf.entities.Ocena;

@Named
@ViewScoped
public class OcenaEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_OCENA_LIST = "ocenaList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Ocena ocena = new Ocena();
	private Ocena loaded = null;

	@EJB
	OcenaDAO ocenaDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Ocena getOcena() {
		return ocena;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Ocena) flash.get("ocena");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			ocena = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (ocena.getIdocena() == null) {
				// new record
				ocenaDAO.create(ocena);
			} else {
				// existing record
				ocenaDAO.merge(ocena);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_OCENA_LIST;
	}
}
