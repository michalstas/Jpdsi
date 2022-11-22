package jsf.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the występuje database table.
 * 
 */
@Entity
@NamedQuery(name="Występuje.findAll", query="SELECT w FROM Występuje w")
public class Występuje implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idwystępuje;

	//bi-directional many-to-one association to Klasa
	@ManyToOne
	private Klasa klasa;

	//bi-directional many-to-one association to Przedmiot
	@ManyToOne
	private Przedmiot przedmiot;

	public Występuje() {
	}

	public int getIdwystępuje() {
		return this.idwystępuje;
	}

	public void setIdwystępuje(int idwystępuje) {
		this.idwystępuje = idwystępuje;
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

}