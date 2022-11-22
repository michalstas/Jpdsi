package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the uczęszcza database table.
 * 
 */
@Entity
@NamedQuery(name="Uczęszcza.findAll", query="SELECT u FROM Uczęszcza u")
public class Uczęszcza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduczęszcza;

	//bi-directional many-to-one association to Klasa
	@ManyToOne
	private Klasa klasa;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Uczęszcza() {
	}

	public int getIduczęszcza() {
		return this.iduczęszcza;
	}

	public void setIduczęszcza(int iduczęszcza) {
		this.iduczęszcza = iduczęszcza;
	}

	public Klasa getKlasa() {
		return this.klasa;
	}

	public void setKlasa(Klasa klasa) {
		this.klasa = klasa;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}