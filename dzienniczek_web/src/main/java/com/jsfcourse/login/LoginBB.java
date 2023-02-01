package com.jsfcourse.login;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import jsf.course.dao.UserDAO;
import jsf.course.entities.User;
import jsf.course.dao.KlasaDAO;
import jsf.course.entities.Klasa;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_LOGIN = "/pages/LoginView.xhtml?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	private static final String MAIN_PAGE = "/pages/MainView.xhtml";
	private static final String MAIN_PAGE2 = "/pages/index.xhtml";

	private String login;
	private String password;
	private String iduser;

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
	
	
	@EJB
	UserDAO userDAO;
	@EJB
	KlasaDAO klasaDAO;
	

	public String doLogin(){
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(login, password);
		//Klasa klasa = klasaDAO.getKlasaFromDatabase(login, password);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
		

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
		client.setDetails(user);
		
		//client.setKlasa(klasa);

		client.getRoles().add(user.getRola());
		
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
	      HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
	      session.setAttribute("id",user.getIduser());
	      session.setAttribute("login",user.getLogin());
	      
		// and enter the system (now SecurityFilter will pass the request)
		return MAIN_PAGE;
	}
	
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.invalidate();
		return MAIN_PAGE2;
	}
}