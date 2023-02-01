package jsf.course.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the przedmiot database table.
 * 
 */
@Entity
@NamedQuery(name="Przedmiot.findAll", query="SELECT p FROM Przedmiot p")
public class Przedmiot implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idprzedmiot;

	@Column(name="nazwa_przedmiotu")
	private String nazwaPrzedmiotu;

	//bi-directional many-to-one association to Występuje
	@OneToMany(mappedBy="przedmiot")
	private List<Występuje> występujes;

	//bi-directional many-to-one association to Zajęcia
	@OneToMany(mappedBy="przedmiot")
	private List<Zajęcia> zajęcias;

	public Przedmiot() {
	}

	public int getIdprzedmiot() {
		return this.idprzedmiot;
	}

	public void setIdprzedmiot(int idprzedmiot) {
		this.idprzedmiot = idprzedmiot;
	}

	public String getNazwaPrzedmiotu() {
		return this.nazwaPrzedmiotu;
	}

	public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
		this.nazwaPrzedmiotu = nazwaPrzedmiotu;
	}

	public List<Występuje> getWystępujes() {
		return this.występujes;
	}

	public void setWystępujes(List<Występuje> występujes) {
		this.występujes = występujes;
	}

	public Występuje addWystępuje(Występuje występuje) {
		getWystępujes().add(występuje);
		występuje.setPrzedmiot(this);

		return występuje;
	}

	public Występuje removeWystępuje(Występuje występuje) {
		getWystępujes().remove(występuje);
		występuje.setPrzedmiot(null);

		return występuje;
	}

	public List<Zajęcia> getZajęcias() {
		return this.zajęcias;
	}

	public void setZajęcias(List<Zajęcia> zajęcias) {
		this.zajęcias = zajęcias;
	}

	public Zajęcia addZajęcia(Zajęcia zajęcia) {
		getZajęcias().add(zajęcia);
		zajęcia.setPrzedmiot(this);

		return zajęcia;
	}

	public Zajęcia removeZajęcia(Zajęcia zajęcia) {
		getZajęcias().remove(zajęcia);
		zajęcia.setPrzedmiot(null);

		return zajęcia;
	}

}