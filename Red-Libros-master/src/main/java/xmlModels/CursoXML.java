package xmlModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curso")
@XmlAccessorType (XmlAccessType.FIELD)
public class CursoXML {
	
	@XmlAttribute
	private String ensenanza;
	
	@XmlAttribute
	private String codigo;
	
	@XmlAttribute
	private String abreviatura;
	
	@XmlAttribute(name = "nombre_cas")
	private String nombre_cas;
	
	@XmlAttribute(name = "nombre_val")
	private String nombre_val;
	
	@XmlAttribute
	private String padre;
	
	
	

	public CursoXML() {
		super();
	}

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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
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

	public String getPadre() {
		return padre;
	}

	public void setPadre(String padre) {
		this.padre = padre;
	}

	@Override
	public String toString() {
		return "CursoXML [ensenanza=" + ensenanza + ", codigo=" + codigo + ", abreviatura=" + abreviatura
				+ ", nombre_cas=" + nombre_cas + ", nombre_val=" + nombre_val + ", padre=" + padre + "]";
	}

	
	
	
	
	

}
