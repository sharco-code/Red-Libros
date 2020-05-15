package xmlModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "grupo")
@XmlAccessorType (XmlAccessType.FIELD)
public class GrupoXML {
	
	@XmlAttribute
	private String codigo;
	
	@XmlAttribute
	private String nombre;
	
	@XmlAttribute
	private String ensenanza;
	
	@XmlAttribute
	private String linea;
	
	@XmlAttribute
	private String turno;
	
	@XmlAttribute
	private String modalidad;
	
	@XmlAttribute
	private String aula;
	
	@XmlAttribute
	private String capacidad;
	
	@XmlAttribute(name = "tutor_ppal")
	private String tutor_ppal;
	
	@XmlAttribute(name = "tutor_sec")
	private String tutor_sec;
	
	@XmlAttribute
	private String oficial;

	public GrupoXML() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEnsenanza() {
		return ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}

	public String getTutor_ppal() {
		return tutor_ppal;
	}

	public void setTutor_ppal(String tutor_ppal) {
		this.tutor_ppal = tutor_ppal;
	}

	public String getTutor_sec() {
		return tutor_sec;
	}

	public void setTutor_sec(String tutor_sec) {
		this.tutor_sec = tutor_sec;
	}

	public String getOficial() {
		return oficial;
	}

	public void setOficial(String oficial) {
		this.oficial = oficial;
	}

	@Override
	public String toString() {
		return "GrupoXML [codigo=" + codigo + ", nombre=" + nombre + ", ensenanza=" + ensenanza + ", linea=" + linea
				+ ", turno=" + turno + ", modalidad=" + modalidad + ", aula=" + aula + ", capacidad=" + capacidad
				+ ", tutor_ppal=" + tutor_ppal + ", tutor_sec=" + tutor_sec + ", oficial=" + oficial + "]";
	}
	
	
	
	

}
