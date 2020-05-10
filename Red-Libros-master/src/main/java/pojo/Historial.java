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
	private Integer id;

	@Column(name="curso_escolar")
	private Integer cursoEscolar;

	@Column(name="estado_final")
	private Integer estadoFinal;

	@Column(name="estado_inicial")
	private Integer estadoInicial;

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

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCursoEscolar() {
		return this.cursoEscolar;
	}

	public void setCursoEscolar(Integer cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
	}

	public Integer getEstadoFinal() {
		return this.estadoFinal;
	}

	public void setEstadoFinal(Integer estadoFinal) {
		this.estadoFinal = estadoFinal;
	}

	public Integer getEstadoInicial() {
		return this.estadoInicial;
	}

	public void setEstadoInicial(Integer estadoInicial) {
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

	@Override
	public String toString() {
		return "Historial [id=" + id + ", cursoEscolar=" + cursoEscolar + ", estadoFinal=" + estadoFinal
				+ ", estadoInicial=" + estadoInicial + ", fechaFinal=" + fechaFinal + ", fechaInicial=" + fechaInicial
				+ ", observaciones=" + observaciones + ", alumno=" + alumno + ", ejemplare=" + ejemplare + "]";
	}
	
	

}