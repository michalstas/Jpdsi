package jsfcourse.ocena;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import jsf.dao.OcenaDAO;
import jsf.entities.Ocena;

@Named
@ViewScoped
public class OcenaEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_OCENA_LIST = "ocenaList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Ocena ocena = new Ocena();
	private Ocena loaded = null;

	@Inject
	FacesContext context;

	@EJB
	OcenaDAO ocenaDAO;

	public Ocena getOcena() {
		return ocena;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && ocena.getIdocena() != null) {
				loaded = ocenaDAO.find(ocena.getIdocena());
			}
			if (loaded != null) {
				ocena = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
				// if (!context.isPostback()) { // possible redirect
				// context.getExternalContext().redirect("personList.xhtml");
				// context.responseComplete();
				// }
			}
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
