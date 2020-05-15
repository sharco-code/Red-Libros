package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the contenido database table.
 * 
 */
@Entity
@NamedQuery(name="Contenido.findAll", query="SELECT c FROM Contenido c")
public class Contenido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String codigo;

	private String ensenanza;

	@Column(name="nombre_cas")
	private String nombreCas;

	@Column(name="nombre_val")
	private String nombreVal;

	//bi-directional many-to-one association to Curso
	@ManyToOne( fetch=FetchType.EAGER)
	@JoinColumn(name="curso")
	private Curso cursoBean;

	//bi-directional many-to-one association to Matricula
	@OneToMany( fetch=FetchType.LAZY,mappedBy="contenidoBean")
	private List<Matricula> matriculas;
	
	@OneToMany( fetch=FetchType.EAGER,mappedBy="contenido")
	private List<Libro> libros;

	public Contenido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEnsenanza() {
		return this.ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getNombreCas() {
		return this.nombreCas;
	}

	public void setNombreCas(String nombreCas) {
		this.nombreCas = nombreCas;
	}

	public String getNombreVal() {
		return this.nombreVal;
	}

	public void setNombreVal(String nombreVal) {
		this.nombreVal = nombreVal;
	}

	public Curso getCursoBean() {
		return this.cursoBean;
	}

	public void setCursoBean(Curso cursoBean) {
		this.cursoBean = cursoBean;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setContenidoBean(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setContenidoBean(null);

		return matricula;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	

}