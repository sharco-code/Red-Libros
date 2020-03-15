package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the grupos database table.
 * 
 */
@Entity
@Table(name="grupos")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String aula;

	private short capacidad;

	private String codigo;

	private String ensenanza;

	private String linea;

	private String modalidad;

	private String nombre;

	private String oficial;

	private String turno;

	@Column(name="tutor_ppal")
	private String tutorPpal;

	@Column(name="tutor_sec")
	private String tutorSec;

	//bi-directional many-to-one association to Alumno
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="grupoBean")
	private List<Alumno> alumnos;

	public Grupo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAula() {
		return this.aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public short getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(short capacidad) {
		this.capacidad = capacidad;
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

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getOficial() {
		return this.oficial;
	}

	public void setOficial(String oficial) {
		this.oficial = oficial;
	}

	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getTutorPpal() {
		return this.tutorPpal;
	}

	public void setTutorPpal(String tutorPpal) {
		this.tutorPpal = tutorPpal;
	}

	public String getTutorSec() {
		return this.tutorSec;
	}

	public void setTutorSec(String tutorSec) {
		this.tutorSec = tutorSec;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setGrupoBean(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setGrupoBean(null);

		return alumno;
	}

}