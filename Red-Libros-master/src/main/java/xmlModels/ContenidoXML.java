package xmlModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "contenido")
@XmlAccessorType (XmlAccessType.FIELD)
public class ContenidoXML {

	@XmlAttribute
	private String ensenanza;
	
	@XmlAttribute
	private String codigo;
	
	@XmlAttribute
	private String curso;
	
	@XmlAttribute(name = "nombre_cas")
	private String nombre_cas;
	
	@XmlAttribute(name = "nombre_val")
	private String nombre_val;

	public String getEnsenanza() {
		return ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombre_cas() {
		return nombre_cas;
	}

	public void setNombre_cas(String nombre_cas) {
		this.nombre_cas = nombre_cas;
	}

	public String getNombre_val() {
		return nombre_val;
	}

	public void setNombre_val(String nombre_val) {
		this.nombre_val = nombre_val;
	}

	@Override
	public String toString() {
		return "ContenidoXML [ensenanza=" + ensenanza + ", codigo=" + codigo + ", curso=" + curso + ", nombre_cas="
				+ nombre_cas + ", nombre_val=" + nombre_val + "]";
	}
	
	
}
