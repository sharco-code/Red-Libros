package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the matricula database table.
 * 
 */
@Entity
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String acis;

	@Column(name="cur_ref_acis")
	private String curRefAcis;

	@Column(name="curso_escolar")
	private int cursoEscolar;

	@Column(name="curso_pendiente")
	private String cursoPendiente;

	private String ensenanza;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_fin_acis")
	private Date fecFinAcis;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_ini_acis")
	private Date fecIniAcis;

	private String idioma;

	@Column(name="tipo_basico")
	private String tipoBasico;

	@Column(name="tipo_predom")
	private String tipoPredom;

	//bi-directional many-to-one association to Contenido
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="contenido")
	private Contenido contenidoBean;

	//bi-directional many-to-one association to Curso
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="curso")
	private Curso cursoBean;

	//bi-directional many-to-one association to Alumno
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_alumno")
	private Alumno alumno;

	public Matricula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcis() {
		return this.acis;
	}

	public void setAcis(String acis) {
		this.acis = acis;
	}

	public String getCurRefAcis() {
		return this.curRefAcis;
	}

	public void setCurRefAcis(String curRefAcis) {
		this.curRefAcis = curRefAcis;
	}

	public int getCursoEscolar() {
		return this.cursoEscolar;
	}

	public void setCursoEscolar(int cursoEscolar) {
		this.cursoEscolar = cursoEscolar;
	}

	public String getCursoPendiente() {
		return this.cursoPendiente;
	}

	public void setCursoPendiente(String cursoPendiente) {
		this.cursoPendiente = cursoPendiente;
	}

	public String getEnsenanza() {
		return this.ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public Date getFecFinAcis() {
		return this.fecFinAcis;
	}

	public void setFecFinAcis(Date fecFinAcis) {
		this.fecFinAcis = fecFinAcis;
	}

	public Date getFecIniAcis() {
		return this.fecIniAcis;
	}

	public void setFecIniAcis(Date fecIniAcis) {
		this.fecIniAcis = fecIniAcis;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTipoBasico() {
		return this.tipoBasico;
	}

	public void setTipoBasico(String tipoBasico) {
		this.tipoBasico = tipoBasico;
	}

	public String getTipoPredom() {
		return this.tipoPredom;
	}

	public void setTipoPredom(String tipoPredom) {
		this.tipoPredom = tipoPredom;
	}

	public Contenido getContenidoBean() {
		return this.contenidoBean;
	}

	public void setContenidoBean(Contenido contenidoBean) {
		this.contenidoBean = contenidoBean;
	}

	public Curso getCursoBean() {
		return this.cursoBean;
	}

	public void setCursoBean(Curso cursoBean) {
		this.cursoBean = cursoBean;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	
	
	

}