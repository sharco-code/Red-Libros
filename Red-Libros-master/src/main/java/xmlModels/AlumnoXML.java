package xmlModels;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "alumno")
@XmlAccessorType (XmlAccessType.FIELD)
public class AlumnoXML {
	
	@XmlAttribute(name = "NIA")
	private String nia;

	@XmlAttribute
	private String nombre;
	
	@XmlAttribute
	private String apellido1;
	
	@XmlAttribute
	private String apellido2;
	
	@XmlAttribute(name = "fecha_nac")
	private String fecha_nac;
	
	@XmlAttribute(name = "municipio_nac")
	private String municipio_nac;
	
	@XmlAttribute(name = "municipio_nac_ext")
	private String municipio_nac_ext;
	
	@XmlAttribute(name = "provincia_nac")
	private String provincia_nac;
	
	@XmlAttribute(name = "pais_nac")
	private String pais_nac;
	
	@XmlAttribute
	private String nacionalidad;
	
	@XmlAttribute
	private String sexo;
	
	@XmlAttribute(name = "tipo_doc")
	private String tipo_doc;
	
	@XmlAttribute
	private String documento;
	
	@XmlAttribute
	private String expediente;
	
	@XmlAttribute(name = "libro_escolaridad")
	private String libro_escoladirad;
	
	@XmlAttribute(name = "cod_postal")
	private String cod_postal;
	
	@XmlAttribute(name = "tipo_via")
	private String tipo_via;
	
	@XmlAttribute
	private String domicilio;
	
	@XmlAttribute
	private String numero;
	
	@XmlAttribute
	private String puerta;
	
	@XmlAttribute
	private String escalera;
	
	@XmlAttribute
	private String letra;
	
	@XmlAttribute
	private String piso;
	
	@XmlAttribute
	private String provincia;
	
	@XmlAttribute
	private String municipio;
	
	@XmlAttribute
	private String localidad;
	
	@XmlAttribute
	private String telefono1;
	
	@XmlAttribute
	private String telefono2;
	
	@XmlAttribute
	private String telefono3;
	
	@XmlAttribute
	private String email1;
	
	@XmlAttribute
	private String email2;
	
	@XmlAttribute
	private String sip;
	
	@XmlAttribute
	private String observaciones;
	
	@XmlAttribute
	private String ampa;
	
	@XmlAttribute
	private String seguro;
	
	@XmlAttribute
	private String dictamen;
	
	@XmlAttribute(name = "fecha_resolucion")
	private String fecha_resolucion;
	
	@XmlAttribute(name = "informe_psicoped")
	private String informe_psicoped;
	
	@XmlAttribute(name = "informado_posib")
	private String informado_posib;
	
	@XmlAttribute(name = "fecha_matricula")
	private String fecha_matricula;
	
	@XmlAttribute(name = "fecha_ingreso_centro")
	private String fecha_ingreso_centro;
	
	@XmlAttribute(name = "estado matricula")
	private String estado_matricula;
	
	@XmlAttribute(name = "tipo_matricula")
	private String tipo_matricula;
	
	@XmlAttribute
	private String repite;
	
	@XmlAttribute(name = "num_repeticion")
	private String num_repeticion;
	
	@XmlAttribute
	private String ensenanza;
	
	@XmlAttribute
	private String curso;
	
	@XmlAttribute
	private String grupo;
	
	@XmlAttribute
	private String turno;
	
	@XmlAttribute
	private String linea;
	
	@XmlAttribute
	private String trabaja;
	
	@XmlAttribute(name = "fuera_comunidad")
	private String fuera_comunidad;
	
	@XmlAttribute(name = "matricula_parcial")
	private String matricula_parcial;
	
	@XmlAttribute(name = "matricula_condic")
	private String matricula_condic;
	
	@XmlAttribute(name = "informe_medico")
	private String informe_medico;
	
	@XmlAttribute
	private String banco;
	
	@XmlAttribute
	private String sucursal;
	
	@XmlAttribute(name = "digito_control")
	private String digito_control;
	
	@XmlAttribute
	private String cuenta;
	
	@XmlAttribute
	private String modalidad;
	
	@XmlAttribute
	private String iban;

	public AlumnoXML() {
		super();
	}

	public String getNia() {
		return nia;
	}

	public void setNia(String nia) {
		this.nia = nia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getMunicipio_nac() {
		return municipio_nac;
	}

	public void setMunicipio_nac(String municipio_nac) {
		this.municipio_nac = municipio_nac;
	}

	public String getMunicipio_nac_ext() {
		return municipio_nac_ext;
	}

	public void setMunicipio_nac_ext(String municipio_nac_ext) {
		this.municipio_nac_ext = municipio_nac_ext;
	}

	public String getProvincia_nac() {
		return provincia_nac;
	}

	public void setProvincia_nac(String provincia_nac) {
		this.provincia_nac = provincia_nac;
	}

	public String getPais_nac() {
		return pais_nac;
	}

	public void setPais_nac(String pais_nac) {
		this.pais_nac = pais_nac;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo_doc() {
		return tipo_doc;
	}

	public void setTipo_doc(String tipo_doc) {
		this.tipo_doc = tipo_doc;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getLibro_escoladirad() {
		return libro_escoladirad;
	}

	public void setLibro_escoladirad(String libro_escoladirad) {
		this.libro_escoladirad = libro_escoladirad;
	}

	public String getCod_postal() {
		return cod_postal;
	}

	public void setCod_postal(String cod_postal) {
		this.cod_postal = cod_postal;
	}

	public String getTipo_via() {
		return tipo_via;
	}

	public void setTipo_via(String tipo_via) {
		this.tipo_via = tipo_via;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getPuerta() {
		return puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getEscalera() {
		return escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getLetra() {
		return letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getTelefono3() {
		return telefono3;
	}

	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getAmpa() {
		return ampa;
	}

	public void setAmpa(String ampa) {
		this.ampa = ampa;
	}

	public String getSeguro() {
		return seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public String getDictamen() {
		return dictamen;
	}

	public void setDictamen(String dictamen) {
		this.dictamen = dictamen;
	}

	public String getFecha_resolucion() {
		return fecha_resolucion;
	}

	public void setFecha_resolucion(String fecha_resolucion) {
		this.fecha_resolucion = fecha_resolucion;
	}

	public String getInforme_psicoped() {
		return informe_psicoped;
	}

	public void setInforme_psicoped(String informe_psicoped) {
		this.informe_psicoped = informe_psicoped;
	}

	public String getInformado_posib() {
		return informado_posib;
	}

	public void setInformado_posib(String informado_posib) {
		this.informado_posib = informado_posib;
	}

	public String getFecha_matricula() {
		return fecha_matricula;
	}

	public void setFecha_matricula(String fecha_matricula) {
		this.fecha_matricula = fecha_matricula;
	}

	public String getFecha_ingreso_centro() {
		return fecha_ingreso_centro;
	}

	public void setFecha_ingreso_centro(String fecha_ingreso_centro) {
		this.fecha_ingreso_centro = fecha_ingreso_centro;
	}

	public String getEstado_matricula() {
		return estado_matricula;
	}

	public void setEstado_matricula(String estado_matricula) {
		this.estado_matricula = estado_matricula;
	}

	public String getTipo_matricula() {
		return tipo_matricula;
	}

	public void setTipo_matricula(String tipo_matricula) {
		this.tipo_matricula = tipo_matricula;
	}

	public String getRepite() {
		return repite;
	}

	public void setRepite(String repite) {
		this.repite = repite;
	}

	public String getNum_repeticion() {
		return num_repeticion;
	}

	public void setNum_repeticion(String num_repeticion) {
		this.num_repeticion = num_repeticion;
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getTrabaja() {
		return trabaja;
	}

	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}

	public String getFuera_comunidad() {
		return fuera_comunidad;
	}

	public void setFuera_comunidad(String fuera_comunidad) {
		this.fuera_comunidad = fuera_comunidad;
	}

	public String getMatricula_parcial() {
		return matricula_parcial;
	}

	public void setMatricula_parcial(String matricula_parcial) {
		this.matricula_parcial = matricula_parcial;
	}

	public String getMatricula_condic() {
		return matricula_condic;
	}

	public void setMatricula_condic(String matricula_condic) {
		this.matricula_condic = matricula_condic;
	}

	public String getInforme_medico() {
		return informe_medico;
	}

	public void setInforme_medico(String informe_medico) {
		this.informe_medico = informe_medico;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getDigito_control() {
		return digito_control;
	}

	public void setDigito_control(String digito_control) {
		this.digito_control = digito_control;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	
	
	
	
	
}
