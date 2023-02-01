package jsf.course.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ocena database table.
 * 
 */
@Entity
@NamedQuery(name="Ocena.findAll", query="SELECT o FROM Ocena o")
public class Ocena implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idocena;

	@Temporal(TemporalType.DATE)
	@Column(name="data_wystawienia")
	private Date dataWystawienia;

	@Column(name="wartość_oceny")
	private String wartośćOceny;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="czyja_ocena")
	private User user;

	//bi-directional many-to-one association to Zajęcia
	@ManyToOne
	@JoinColumn(name="z_zajęć")
	private Zajęcia zajęcia;

	public Ocena() {
	}

	public int getIdocena() {
		return this.idocena;
	}

	public void setIdocena(int idocena) {
		this.idocena = idocena;
	}


	public Date getDataWystawienia() {
		return this.dataWystawienia;
	}

	public void setDataWystawienia(Date dataWystawienia) {
		this.dataWystawienia = dataWystawienia;
	}

	public String getWartośćOceny() {
		return this.wartośćOceny;
	}

	public void setWartośćOceny(String wartośćOceny) {
		this.wartośćOceny = wartośćOceny;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Zajęcia getZajęcia() {
		return this.zajęcia;
	}

	public void setZajęcia(Zajęcia zajęcia) {
		this.zajęcia = zajęcia;
	}

}