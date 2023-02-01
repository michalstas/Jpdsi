package com.jsfcourse.uczenlist;

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

import jsf.course.dao.ZajęciaDAO;
import jsf.course.dao.UserDAO;
import jsf.course.entities.User;
import jsf.course.entities.Zajęcia;

@Named
@ViewScoped
public class UczenListBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_PERSON_LIST = "/pages/UczenList.xhtml?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String PAGE_PERSON = "/pages/OcenaUczenList.xhtml?faces-redirect=true";
	private Zajęcia zajęcia = new Zajęcia();
	private Zajęcia loaded = null;

	@EJB
	ZajęciaDAO zajęciaDAO;
	@EJB
	UserDAO userDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;
	@Inject
	ExternalContext extcontext;
	public Zajęcia getZajęcia() {
		return zajęcia;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		 HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		 loaded = (Zajęcia) session.getAttribute("zajęcia1");
		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			zajęcia = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public List<User> getList(){
		List<User> list = null;

		list = userDAO.getFullList(zajęcia);
		
		return list;
	}
	
	public String ocenaUczenList(User user){
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("user1", user);
		return PAGE_PERSON;
	}
}
