package com.jsfcourse.ocenalist;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import jsf.course.dao.OcenaDAO;
import jsf.course.entities.Ocena;


@Named
@RequestScoped
public class OcenaListBB {

	@EJB
	OcenaDAO ocenaDAO;

	
	private Ocena ocena;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer user = (Integer) session.getAttribute("id");
    
    private String przedmiot;
    
	public String getPrzedmiot() {
		return this.przedmiot;
	}

	public void setPrzedmiot(String przedmiot) {
		this.przedmiot = przedmiot;
	}
    



public List<Ocena> getList(){
	List<Ocena> list = null;
	String x = "";
	
	if (przedmiot != null && przedmiot.length() > 0){
		x = przedmiot;
	}

	list = ocenaDAO.getFullList(user,x);
	
	return list;
}

	
	
}
