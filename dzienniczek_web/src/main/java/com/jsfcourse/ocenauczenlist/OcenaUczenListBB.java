package com.jsfcourse.ocenauczenlist;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;



import jsf.course.dao.OcenaDAO;
import jsf.course.dao.UserDAO;
import jsf.course.dao.ZajęciaDAO;
import jsf.course.entities.User;
import jsf.course.entities.Ocena;
import jsf.course.entities.Zajęcia;
@Named
@ViewScoped
public class OcenaUczenListBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "/pages/UczenList.xhtml?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_PERSON = "/pages/OcenaUczenList.xhtml?faces-redirect=true";
	private static final String PAGE_PERSON_EDIT = "/pages/OcenaEdit.xhtml?faces-redirect=true";
	private User user = new User();
	private User loaded = null;
	private Zajęcia zajęcia = new Zajęcia();
	private Zajęcia loaded2 = null;

	@EJB
	OcenaDAO ocenaDAO;
	@EJB
	UserDAO userDAO;
	@EJB
	ZajęciaDAO zajęciaDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	@Inject
	ExternalContext extcontext;
	public User getUser() {
		return user;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		 HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		 loaded = (User) session.getAttribute("user1");
		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			user = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}
		// 1. load person passed through session
		 loaded2 = (Zajęcia) session.getAttribute("zajęcia1");
		// cleaning: attribute received => delete it from session
		if (loaded2 != null) {
			zajęcia = loaded2;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public List<Ocena> getList(){
		List<Ocena> list = null;

		list = ocenaDAO.getLista(user,zajęcia);
		
		return list;
	}
	
	public String newOcena(){
		Ocena ocena = new Ocena();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		ocena.setUser(loaded);
		ocena.setZajęcia(loaded2);
		session.setAttribute("ocena", ocena);
		return PAGE_PERSON_EDIT;
	}

	public String editOcena(Ocena ocena){
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("ocena", ocena);
		return PAGE_PERSON_EDIT;
	}

	public String deleteOcena(Ocena ocena){
		ocenaDAO.delete(ocena);
		return PAGE_STAY_AT_THE_SAME;
	}
	
}
