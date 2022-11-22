package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idrola;

	private String nazwa;

	//bi-directional many-to-one association to Posiada
	@OneToMany(mappedBy="role")
	private List<Posiada> posiadas;

	public Role() {
	}

	public int getIdrola() {
		return this.idrola;
	}

	public void setIdrola(int idrola) {
		this.idrola = idrola;
	}

	public String getNazwa() {
		return this.nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public List<Posiada> getPosiadas() {
		return this.posiadas;
	}

	public void setPosiadas(List<Posiada> posiadas) {
		this.posiadas = posiadas;
	}

	public Posiada addPosiada(Posiada posiada) {
		getPosiadas().add(posiada);
		posiada.setRole(this);

		return posiada;
	}

	public Posiada removePosiada(Posiada posiada) {
		getPosiadas().remove(posiada);
		posiada.setRole(null);

		return posiada;
	}

}