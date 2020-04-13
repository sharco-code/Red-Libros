package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cursos database table.
 * 
 */
@Entity
@Table(name="cursos")
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String abreviatura;

	private String codigo;

	@Column(name="curso_escolar")
	private int cursoEscolar;

	private String ensenanza;

	private String idPadre;

	@Column(name="nombre_cas")
	private String nombreCas;

	@Column(name="nombre_val")
	private String nombreVal;

	//bi-directional many-to-one association to Alumno
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="cursoBean")
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to Contenido
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="cursoBean")
	private List<Contenido> contenidos;

	//bi-directional many-to-one association to Matricula
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="cursoBean")
	private List<Matricula> matriculas;

	public Curso() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCursoEscolar() {
		return this.cursoEscolar;
	}

	public void setCursoEscolar(int cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
	}

	public String getEnsenanza() {
		return this.ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
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

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setCursoBean(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setCursoBean(null);

		return alumno;
	}

	public List<Contenido> getContenidos() {
		return this.contenidos;
	}

	public void setContenidos(List<Contenido> contenidos) {
		this.contenidos = contenidos;
	}

	public Contenido addContenido(Contenido contenido) {
		getContenidos().add(contenido);
		contenido.setCursoBean(this);

		return contenido;
	}

	public Contenido removeContenido(Contenido contenido) {
		getContenidos().remove(contenido);
		contenido.setCursoBean(null);

		return contenido;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setCursoBean(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setCursoBean(null);

		return matricula;
	}

}