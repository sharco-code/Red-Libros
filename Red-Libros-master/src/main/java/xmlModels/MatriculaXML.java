package xmlModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contenido_alumno")
@XmlAccessorType (XmlAccessType.FIELD)
public class MatriculaXML {
	
	@XmlAttribute
	private String alumno;
	
	@XmlAttribute
	private String ensenanza;
	
	@XmlAttribute
	private String curso;
	
	@XmlAttribute
	private String contenido;
	
	@XmlAttribute
	private String idioma;
	
	@XmlAttribute(name = "tipo_basico")
	private String tipo_basico;
	
	@XmlAttribute(name = "tipo_predom")
	private String tipo_predom;
	
	@XmlAttribute
	private String acis;
	
	@XmlAttribute(name = "fec_ini_acis")
	private String fec_ini_acis;
	
	@XmlAttribute(name = "fec_fin_acis")
	private String fec_fin_acis;
	
	@XmlAttribute(name = "cur_ref_acis")
	private String cur_ref_acis;
	
	@XmlAttribute(name = "curso_pendiente")
	private String curso_pendiente;

	public MatriculaXML() {
		super();
	}

	public String getAlumno() {
		return alumno;
	}

	public void setAlumno(String alumno) {
		this.alumno = alumno;
	}

	public String getEnsenanza() {
		return ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getTipo_basico() {
		return tipo_basico;
	}

	public void setTipo_basico(String tipo_basico) {
		this.tipo_basico = tipo_basico;
	}

	public String getTipo_predom() {
		return tipo_predom;
	}

	public void setTipo_predom(String tipo_predom) {
		this.tipo_predom = tipo_predom;
	}

	public String getAcis() {
		return acis;
	}

	public void setAcis(String acis) {
		this.acis = acis;
	}

	public String getFec_ini_acis() {
		return fec_ini_acis;
	}

	public void setFec_ini_acis(String fec_ini_acis) {
		this.fec_ini_acis = fec_ini_acis;
	}

	public String getFec_fin_acis() {
		return fec_fin_acis;
	}

	public void setFec_fin_acis(String fec_fin_acis) {
		this.fec_fin_acis = fec_fin_acis;
	}

	public String getCur_ref_acis() {
		return cur_ref_acis;
	}

	public void setCur_ref_acis(String cur_ref_acis) {
		this.cur_ref_acis = cur_ref_acis;
	}

	public String getCurso_pendiente() {
		return curso_pendiente;
	}

	public void setCurso_pendiente(String curso_pendiente) {
		this.curso_pendiente = curso_pendiente;
	}
	
	
	

}
