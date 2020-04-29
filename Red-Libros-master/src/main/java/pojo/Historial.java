package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historial database table.
 * 
 */
@Entity
@NamedQuery(name="Historial.findAll", query="SELECT h FROM Historial h")
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="curso_escolar")
	private int cursoEscolar;

	@Column(name="estado_final")
	private int estadoFinal;

	@Column(name="estado_inicial")
	private int estadoInicial;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_final")
	private Date fechaFinal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicial")
	private Date fechaInicial;

	private String observaciones;

	//bi-directional many-to-one association to Alumno
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

	//bi-directional many-to-one association to Ejemplare
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_ejemplar")
	private Ejemplare ejemplare;

	public Historial() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCursoEscolar() {
		return this.cursoEscolar;
	}

	public void setCursoEscolar(int cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
	}

	public int getEstadoFinal() {
		return this.estadoFinal;
	}

	public void setEstadoFinal(int estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public int getEstadoInicial() {
		return this.estadoInicial;
	}

	public void setEstadoInicial(int estadoInicial) {
		this.estadoInicial = estadoInicial;
	}

	public Date getFechaFinal() {
		return this.fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Date getFechaInicial() {
		return this.fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Ejemplare getEjemplare() {
		return this.ejemplare;
	}

	public void setEjemplare(Ejemplare ejemplare) {
		this.ejemplare = ejemplare;
	}

}