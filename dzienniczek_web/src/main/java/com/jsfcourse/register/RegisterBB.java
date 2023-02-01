package com.jsfcourse.register;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.simplesecurity.RemoteClient;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import jsf.course.dao.UserDAO;
import jsf.course.entities.User;

@FacesConfig
@Named
@ViewScoped
public class RegisterBB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final String MAIN_PAGE = "/pages/MainView.xhtml";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private User user = new User();
	
	private String imie;
	private String nazwisko;
	private String login;
	private String password;
	private String iduser;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer id_user = (Integer) session.getAttribute("id");
	
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}
	
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}
	
	@Inject
	UserDAO userDAO;
	
	@Inject
	FacesContext context;
	
	@Inject
	Flash flash;
	
	public User getUser() {
		return user;
	}
	
	public void onLoad() throws IOException {
		
		user = userDAO.getClientInfo(id_user);

		
		if (user != null) {
			
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}
	
	public String saveData() {
		if (user == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
				userDAO.update(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return MAIN_PAGE;
	}

	public String doRegister() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		
		
		User user = new User();
		
		user.setImie(imie);
		user.setNazwisko(nazwisko);
		user.setLogin(login);
		user.setPassword(password);
		user.setRola("uczen");
		// 1. verify login and password - get User from "database"
		User user2 = userDAO.getUserFromDatabase2(login);
		//Klasa klasa = klasaDAO.getKlasaFromDatabase(login, password);

		// 2. if bad login or password - stay with error info
		if (user2 != null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Login Zajęty", null));
			return PAGE_STAY_AT_THE_SAME;
		}		
		userDAO.insert(user);
		
		return MAIN_PAGE;
	}
	

	
	public String updateUser() {
	User user = new User();
	
	userDAO.update(user);
		
		return MAIN_PAGE;
	}
	
}
