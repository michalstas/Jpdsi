package jsf.course.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the klasa database table.
 * 
 */
@Entity
@NamedQuery(name="Klasa.findAll", query="SELECT k FROM Klasa k")
public class Klasa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idklasy;

	private String grupa;

	private String nrklasy;

	//bi-directional many-to-one association to Uczęszcza
	@OneToMany(mappedBy="klasa")
	private List<Uczęszcza> uczęszczas;

	//bi-directional many-to-one association to Występuje
	@OneToMany(mappedBy="klasa")
	private List<Występuje> występujes;

	//bi-directional many-to-one association to Zajęcia
	@OneToMany(mappedBy="klasa")
	private List<Zajęcia> zajęcias;

	public Klasa() {
	}

	public int getIdklasy() {
		return this.idklasy;
	}

	public void setIdklasy(int idklasy) {
		this.idklasy = idklasy;
	}

	public String getGrupa() {
		return this.grupa;
	}

	public void setGrupa(String grupa) {
		this.grupa = grupa;
	}

	public String getNrklasy() {
		return this.nrklasy;
	}

	public void setNrklasy(String nrklasy) {
		this.nrklasy = nrklasy;
	}

	public List<Uczęszcza> getUczęszczas() {
		return this.uczęszczas;
	}

	public void setUczęszczas(List<Uczęszcza> uczęszczas) {
		this.uczęszczas = uczęszczas;
	}

	public Uczęszcza addUczęszcza(Uczęszcza uczęszcza) {
		getUczęszczas().add(uczęszcza);
		uczęszcza.setKlasa(this);

		return uczęszcza;
	}

	public Uczęszcza removeUczęszcza(Uczęszcza uczęszcza) {
		getUczęszczas().remove(uczęszcza);
		uczęszcza.setKlasa(null);

		return uczęszcza;
	}

	public List<Występuje> getWystępujes() {
		return this.występujes;
	}

	public void setWystępujes(List<Występuje> występujes) {
		this.występujes = występujes;
	}

	public Występuje addWystępuje(Występuje występuje) {
		getWystępujes().add(występuje);
		występuje.setKlasa(this);

		return występuje;
	}

	public Występuje removeWystępuje(Występuje występuje) {
		getWystępujes().remove(występuje);
		występuje.setKlasa(null);

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
		zajęcia.setKlasa(this);

		return zajęcia;
	}

	public Zajęcia removeZajęcia(Zajęcia zajęcia) {
		getZajęcias().remove(zajęcia);
		zajęcia.setKlasa(null);

		return zajęcia;
	}

}