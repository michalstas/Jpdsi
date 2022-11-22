package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
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

	private String adres;

	private String imie;

	@Temporal(TemporalType.DATE)
	@Column(name="kiedy_edytowano")
	private Date kiedyEdytowano;

	private String login;

	private String nazwisko;

	private String password;

	//bi-directional many-to-one association to Ocena
	@OneToMany(mappedBy="user")
	private List<Ocena> ocenas;

	//bi-directional many-to-one association to Posiada
	@OneToMany(mappedBy="user")
	private List<Posiada> posiadas;

	//bi-directional many-to-one association to Uczęszcza
	@OneToMany(mappedBy="user")
	private List<Uczęszcza> uczęszczas;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="kto_edytował")
	private User user;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="user")
	private List<User> users;

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

	public String getAdres() {
		return this.adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public Date getKiedyEdytowano() {
		return this.kiedyEdytowano;
	}

	public void setKiedyEdytowano(Date kiedyEdytowano) {
		this.kiedyEdytowano = kiedyEdytowano;
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

	public List<Posiada> getPosiadas() {
		return this.posiadas;
	}

	public void setPosiadas(List<Posiada> posiadas) {
		this.posiadas = posiadas;
	}

	public Posiada addPosiada(Posiada posiada) {
		getPosiadas().add(posiada);
		posiada.setUser(this);

		return posiada;
	}

	public Posiada removePosiada(Posiada posiada) {
		getPosiadas().remove(posiada);
		posiada.setUser(null);

		return posiada;
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

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUser(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUser(null);

		return user;
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