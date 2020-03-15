package pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the alumnos database table.
 * 
 */
@Entity
@Table(name="alumnos")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String ampa;

	private String apellido1;

	private String apellido2;

	private String banco;

	@Column(name="cod_postal")
	private String codPostal;

	private String cuenta;

	private String dictamen;

	@Column(name="digito_control")
	private String digitoControl;

	private String documento;

	private String domicilio;

	private String email1;

	private String email2;

	private String ensenanza;

	private String escalera;

	@Column(name="estado_matricula")
	private String estadoMatricula;

	private String expediente;

	@Column(name="fecha_ingreso_centro")
	private String fechaIngresoCentro;

	@Column(name="fecha_matricula")
	private String fechaMatricula;

	@Column(name="fecha_nac")
	private String fechaNac;

	@Column(name="fecha_resolucion")
	private String fechaResolucion;

	@Column(name="fuera_comunidad")
	private String fueraComunidad;

	private String iban;

	@Column(name="informado_posib")
	private String informadoPosib;

	@Column(name="informe_medico")
	private String informeMedico;

	@Column(name="informe_psicoped")
	private String informePsicoped;

	private String letra;

	@Column(name="libro_escolaridad")
	private String libroEscolaridad;

	private String linea;

	private String localidad;

	@Column(name="matricula_condic")
	private String matriculaCondic;

	@Column(name="matricula_parcial")
	private String matriculaParcial;

	private String modalidad;

	private String municipio;

	@Column(name="municipio_nac")
	private String municipioNac;

	@Column(name="municipio_nac_ext")
	private String municipioNacExt;

	private String nacionalidad;

	private String nia;

	private String nombre;

	@Column(name="num_repeticion")
	private String numRepeticion;

	private String numero;

	private String observaciones;

	@Column(name="pais_nac")
	private String paisNac;

	private String piso;

	private String provincia;

	@Column(name="provincia_nac")
	private String provinciaNac;

	private String puerta;

	private String repite;

	private String seguro;

	private String sexo;

	private String sip;

	private String sucursal;

	private String telefono1;

	private String telefono2;

	private String telefono3;

	@Column(name="tipo_doc")
	private String tipoDoc;

	@Column(name="tipo_matricula")
	private String tipoMatricula;

	@Column(name="tipo_via")
	private String tipoVia;

	private String trabaja;

	private String turno;

	//bi-directional many-to-one association to Curso
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="curso")
	private Curso cursoBean;

	//bi-directional many-to-one association to Grupo
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="grupo")
	private Grupo grupoBean;

	//bi-directional many-to-one association to Historial
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="alumno")
	private List<Historial> historials;

	//bi-directional many-to-one association to Matricula
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY,mappedBy="alumno")
	private List<Matricula> matriculas;

	public Alumno() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmpa() {
		return this.ampa;
	}

	public void setAmpa(String ampa) {
		this.ampa = ampa;
	}

	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getBanco() {
		return this.banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getCodPostal() {
		return this.codPostal;
	}

	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}

	public String getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDictamen() {
		return this.dictamen;
	}

	public void setDictamen(String dictamen) {
		this.dictamen = dictamen;
	}

	public String getDigitoControl() {
		return this.digitoControl;
	}

	public void setDigitoControl(String digitoControl) {
		this.digitoControl = digitoControl;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEnsenanza() {
		return this.ensenanza;
	}

	public void setEnsenanza(String ensenanza) {
		this.ensenanza = ensenanza;
	}

	public String getEscalera() {
		return this.escalera;
	}

	public void setEscalera(String escalera) {
		this.escalera = escalera;
	}

	public String getEstadoMatricula() {
		return this.estadoMatricula;
	}

	public void setEstadoMatricula(String estadoMatricula) {
		this.estadoMatricula = estadoMatricula;
	}

	public String getExpediente() {
		return this.expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getFechaIngresoCentro() {
		return this.fechaIngresoCentro;
	}

	public void setFechaIngresoCentro(String fechaIngresoCentro) {
		this.fechaIngresoCentro = fechaIngresoCentro;
	}

	public String getFechaMatricula() {
		return this.fechaMatricula;
	}

	public void setFechaMatricula(String fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}

	public String getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFechaResolucion() {
		return this.fechaResolucion;
	}

	public void setFechaResolucion(String fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}

	public String getFueraComunidad() {
		return this.fueraComunidad;
	}

	public void setFueraComunidad(String fueraComunidad) {
		this.fueraComunidad = fueraComunidad;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getInformadoPosib() {
		return this.informadoPosib;
	}

	public void setInformadoPosib(String informadoPosib) {
		this.informadoPosib = informadoPosib;
	}

	public String getInformeMedico() {
		return this.informeMedico;
	}

	public void setInformeMedico(String informeMedico) {
		this.informeMedico = informeMedico;
	}

	public String getInformePsicoped() {
		return this.informePsicoped;
	}

	public void setInformePsicoped(String informePsicoped) {
		this.informePsicoped = informePsicoped;
	}

	public String getLetra() {
		return this.letra;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public String getLibroEscolaridad() {
		return this.libroEscolaridad;
	}

	public void setLibroEscolaridad(String libroEscolaridad) {
		this.libroEscolaridad = libroEscolaridad;
	}

	public String getLinea() {
		return this.linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMatriculaCondic() {
		return this.matriculaCondic;
	}

	public void setMatriculaCondic(String matriculaCondic) {
		this.matriculaCondic = matriculaCondic;
	}

	public String getMatriculaParcial() {
		return this.matriculaParcial;
	}

	public void setMatriculaParcial(String matriculaParcial) {
		this.matriculaParcial = matriculaParcial;
	}

	public String getModalidad() {
		return this.modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMunicipioNac() {
		return this.municipioNac;
	}

	public void setMunicipioNac(String municipioNac) {
		this.municipioNac = municipioNac;
	}

	public String getMunicipioNacExt() {
		return this.municipioNacExt;
	}

	public void setMunicipioNacExt(String municipioNacExt) {
		this.municipioNacExt = municipioNacExt;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNia() {
		return this.nia;
	}

	public void setNia(String nia) {
		this.nia = nia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumRepeticion() {
		return this.numRepeticion;
	}

	public void setNumRepeticion(String numRepeticion) {
		this.numRepeticion = numRepeticion;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getPaisNac() {
		return this.paisNac;
	}

	public void setPaisNac(String paisNac) {
		this.paisNac = paisNac;
	}

	public String getPiso() {
		return this.piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getProvinciaNac() {
		return this.provinciaNac;
	}

	public void setProvinciaNac(String provinciaNac) {
		this.provinciaNac = provinciaNac;
	}

	public String getPuerta() {
		return this.puerta;
	}

	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}

	public String getRepite() {
		return this.repite;
	}

	public void setRepite(String repite) {
		this.repite = repite;
	}

	public String getSeguro() {
		return this.seguro;
	}

	public void setSeguro(String seguro) {
		this.seguro = seguro;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSip() {
		return this.sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getSucursal() {
		return this.sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getTelefono3() {
		return this.telefono3;
	}

	public void setTelefono3(String telefono3) {
		this.telefono3 = telefono3;
	}

	public String getTipoDoc() {
		return this.tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getTipoMatricula() {
		return this.tipoMatricula;
	}

	public void setTipoMatricula(String tipoMatricula) {
		this.tipoMatricula = tipoMatricula;
	}

	public String getTipoVia() {
		return this.tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getTrabaja() {
		return this.trabaja;
	}

	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}

	public String getTurno() {
		return this.turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Curso getCursoBean() {
		return this.cursoBean;
	}

	public void setCursoBean(Curso cursoBean) {
		this.cursoBean = cursoBean;
	}

	public Grupo getGrupoBean() {
		return this.grupoBean;
	}

	public void setGrupoBean(Grupo grupoBean) {
		this.grupoBean = grupoBean;
	}

	public List<Historial> getHistorials() {
		return this.historials;
	}

	public void setHistorials(List<Historial> historials) {
		this.historials = historials;
	}

	public Historial addHistorial(Historial historial) {
		getHistorials().add(historial);
		historial.setAlumno(this);

		return historial;
	}

	public Historial removeHistorial(Historial historial) {
		getHistorials().remove(historial);
		historial.setAlumno(null);

		return historial;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setAlumno(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setAlumno(null);

		return matricula;
	}

}