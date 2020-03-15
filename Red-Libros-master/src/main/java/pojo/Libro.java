package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the libros database table.
 * 
 */
@Entity
@Table(name="libros")
@NamedQuery(name="Libro.findAll", query="SELECT l FROM Libro l")
public class Libro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String codigo;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_contenido")
	private Contenido contenido;

	private String isbn;

	private String nombre;

	private byte obsoleto;

	private double precio;

	private int unidades;

	//bi-directional many-to-one association to Ejemplare
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="libro")
	private List<Ejemplare> ejemplares;

	public Libro() {
	}

	public String getId() {
		return this.id;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
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


	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getObsoleto() {
		return this.obsoleto;
	}

	public void setObsoleto(byte obsoleto) {
		this.obsoleto = obsoleto;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public List<Ejemplare> getEjemplares() {
		return this.ejemplares;
	}

	public void setEjemplares(List<Ejemplare> ejemplares) {
		this.ejemplares = ejemplares;
	}

	public Ejemplare addEjemplare(Ejemplare ejemplare) {
		getEjemplares().add(ejemplare);
		ejemplare.setLibro(this);

		return ejemplare;
	}

	public Ejemplare removeEjemplare(Ejemplare ejemplare) {
		getEjemplares().remove(ejemplare);
		ejemplare.setLibro(null);

		return ejemplare;
	}

}