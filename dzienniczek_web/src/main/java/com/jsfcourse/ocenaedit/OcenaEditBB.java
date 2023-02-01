package com.jsfcourse.ocenaedit;

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

import jsf.course.dao.OcenaDAO;
import jsf.course.entities.Ocena;

@Named
@ViewScoped
public class OcenaEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "/pages/OcenaUczenList.xhtml?faces-redirect=true";
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
		 HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		 loaded = (Ocena) session.getAttribute("ocena");
		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			ocena = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public String saveData() {
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (ocena.getIdocena() == 0) {
				// new record
				ocenaDAO.insert(ocena);
			} else {
				// existing record
				ocenaDAO.update(ocena);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_PERSON_LIST;
	}
}
