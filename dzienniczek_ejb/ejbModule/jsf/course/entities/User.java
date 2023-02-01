package jsf.course.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	private String imie;

	private String login;

	private String nazwisko;

	private String password;

	private String rola;

	//bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy="user")
	private List<Ocena> ocenas;

	//bi-directional many-to-one association to Uczęszcza
	@OneToMany(mappedBy="user")
	private List<Uczęszcza> uczęszczas;

	//bi-directional many-to-one association to Zajęcia
	@OneToMany(mappedBy="user")
	private List<Zajęcia> zajęcias;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRola() {
		return this.rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

	public List<Ocena> getOcenas() {
		return this.ocenas;
	}

	public void setOcenas(List<Ocena> ocenas) {
		this.ocenas = ocenas;
	}

	public Ocena addOcena(Ocena ocena) {
		getOcenas().add(ocena);
		ocena.setUser(this);

		return ocena;
	}

	public Ocena removeOcena(Ocena ocena) {
		getOcenas().remove(ocena);
		ocena.setUser(null);

		return ocena;
	}

	public List<Uczęszcza> getUczęszczas() {
		return this.uczęszczas;
	}

	public void setUczęszczas(List<Uczęszcza> uczęszczas) {
		this.uczęszczas = uczęszczas;
	}

	public Uczęszcza addUczęszcza(Uczęszcza uczęszcza) {
		getUczęszczas().add(uczęszcza);
		uczęszcza.setUser(this);

		return uczęszcza;
	}

	public Uczęszcza removeUczęszcza(Uczęszcza uczęszcza) {
		getUczęszczas().remove(uczęszcza);
		uczęszcza.setUser(null);

		return uczęszcza;
	}

	public List<Zajęcia> getZajęcias() {
		return this.zajęcias;
	}

	public void setZajęcias(List<Zajęcia> zajęcias) {
		this.zajęcias = zajęcias;
	}

	public Zajęcia addZajęcia(Zajęcia zajęcia) {
		getZajęcias().add(zajęcia);
		zajęcia.setUser(this);

		return zajęcia;
	}

	public Zajęcia removeZajęcia(Zajęcia zajęcia) {
		getZajęcias().remove(zajęcia);
		zajęcia.setUser(null);

		return zajęcia;
	}

}