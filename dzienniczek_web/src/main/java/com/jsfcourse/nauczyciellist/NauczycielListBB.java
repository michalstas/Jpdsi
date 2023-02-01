package com.jsfcourse.nauczyciellist;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.inject.Inject;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import jsf.course.dao.ZajęciaDAO;
import jsf.course.entities.Zajęcia;


@Named
@RequestScoped
public class NauczycielListBB {
	private static final String PAGE_PERSON = "/pages/UczenList.xhtml?faces-redirect=true";
	@EJB
	ZajęciaDAO zajęciaDAO;
	@Inject
	ExternalContext extcontext;
	
	private Zajęcia zajęcia;
	
	FacesContext ctx = FacesContext.getCurrentInstance();
    HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false) ;
    Integer user = (Integer) session.getAttribute("id");
    
    

//public Zajęcia getZajęcia() {
//
//    
//	Zajęcia zajęcia = zajęciaDAO.getSth(user);
//   
//    
//    if (ocena == null) {
//        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                "Brak wyników", null));
//    }
//    return ocena;
//}
	public String uczenList(Zajęcia zajęcia){
		//1. Pass object through session
		HttpSession session = (HttpSession) extcontext.getSession(true);
		session.setAttribute("zajęcia1", zajęcia);
		return PAGE_PERSON;
	}
	
public List<Zajęcia> getList(){
	String data = "poniedzialek";
	List<Zajęcia> list = null;
	list = zajęciaDAO.getFullList(user,data);
	return list;
}
public List<Zajęcia> getList2(){
	String data = "wtorek";
	List<Zajęcia> list = null;
	list = zajęciaDAO.getFullList(user,data);
	return list;
}
public List<Zajęcia> getList3(){
	String data = "sroda";
	List<Zajęcia> list = null;
	list = zajęciaDAO.getFullList(user,data);
	return list;
}
public List<Zajęcia> getList4(){
	String data = "czwartek";
	List<Zajęcia> list = null;
	list = zajęciaDAO.getFullList(user,data);
	return list;
}
public List<Zajęcia> getList5(){
	String data = "piatek";
	List<Zajęcia> list = null;
	list = zajęciaDAO.getFullList(user,data);
	return list;
}


	
	
}
