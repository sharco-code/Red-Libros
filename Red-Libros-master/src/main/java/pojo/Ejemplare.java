package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ejemplares database table.
 * 
 */
@Entity
@Table(name="ejemplares")
@NamedQuery(name="Ejemplare.findAll", query="SELECT e FROM Ejemplare e")
public class Ejemplare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String codigo;

	private int estado;

	private byte prestado;

	//bi-directional many-to-one association to Libro
	@ManyToOne
	@JoinColumn(name="id_libro")
	private Libro libro;

	//bi-directional many-to-one association to Historial
	@OneToMany(mappedBy="ejemplare")
	private List<Historial> historials;

	public Ejemplare() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getEstado() {
		return this.estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public byte getPrestado() {
		return this.prestado;
	}

	public void setPrestado(byte prestado) {
		this.prestado = prestado;
	}

	public Libro getLibro() {
		return this.libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Historial> getHistorials() {
		return this.historials;
	}

	public void setHistorials(List<Historial> historials) {
		this.historials = historials;
	}

	public Historial addHistorial(Historial historial) {
		getHistorials().add(historial);
		historial.setEjemplare(this);

		return historial;
	}

	public Historial removeHistorial(Historial historial) {
		getHistorials().remove(historial);
		historial.setEjemplare(null);

		return historial;
	}

}