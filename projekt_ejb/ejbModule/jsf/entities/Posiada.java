package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the posiada database table.
 * 
 */
@Entity
@NamedQuery(name="Posiada.findAll", query="SELECT p FROM Posiada p")
public class Posiada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idposiada;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="rola")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Posiada() {
	}

	public int getIdposiada() {
		return this.idposiada;
	}

	public void setIdposiada(int idposiada) {
		this.idposiada = idposiada;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}