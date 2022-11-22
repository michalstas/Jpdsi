package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the zajęcia database table.
 * 
 */
@Entity
@NamedQuery(name="Zajęcia.findAll", query="SELECT z FROM Zajęcia z")
public class Zajęcia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idzajęć;

	private String data;

	private String godzina;

	//bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy="zajęcia")
	private List<Ocena> ocenas;

	//bi-directional many-to-one association to Klasa
	@ManyToOne
	private Klasa klasa;

	//bi-directional many-to-one association to Przedmiot
	@ManyToOne
	private Przedmiot przedmiot;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Zajęcia() {
	}

	public int getIdzajęć() {
		return this.idzajęć;
	}

	public void setIdzajęć(int idzajęć) {
		this.idzajęć = idzajęć;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGodzina() {
		return this.godzina;
	}

	public void setGodzina(String godzina) {
		this.godzina = godzina;
	}

	public List<Ocena> getOcenas() {
		return this.ocenas;
	}

	public void setOcenas(List<Ocena> ocenas) {
		this.ocenas = ocenas;
	}

	public Ocena addOcena(Ocena ocena) {
		getOcenas().add(ocena);
		ocena.setZajęcia(this);

		return ocena;
	}

	public Ocena removeOcena(Ocena ocena) {
		getOcenas().remove(ocena);
		ocena.setZajęcia(null);

		return ocena;
	}

	public Klasa getKlasa() {
		return this.klasa;
	}

	public void setKlasa(Klasa klasa) {
		this.klasa = klasa;
	}

	public Przedmiot getPrzedmiot() {
		return this.przedmiot;
	}

	public void setPrzedmiot(Przedmiot przedmiot) {
		this.przedmiot = przedmiot;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}