package xmlModels;

import java.util.List;

import javax.xml.bind.annotation.*;

import pojo.Curso;

@XmlRootElement(name = "centro")
@XmlAccessorType (XmlAccessType.FIELD)
public class CentroXML {
	
	@XmlElementWrapper(name = "cursos")
    @XmlElement(name = "curso")
	private List<CursoXML> cursos;
	
	@XmlElementWrapper(name = "contenidos")
    @XmlElement(name = "contenido")
	private List<ContenidoXML> contenidos;
	
	@XmlElementWrapper(name = "grupos")
    @XmlElement(name = "grupo")
	private List<GrupoXML> grupos;
	
	@XmlElementWrapper(name = "alumnos")
    @XmlElement(name = "alumno")
	private List<AlumnoXML> alumnos;
	
	@XmlElementWrapper(name = "contenidos_alumno")
    @XmlElement(name = "contenido_alumno")
	private List<MatriculaXML> matriculas;
    
    @XmlAttribute
    private String codigo;
    
    @XmlAttribute
    private String fechaExportacion;
    
    @XmlAttribute
    private String curso;

	 
	 
	public CentroXML() {
		super();
	}

	public List<CursoXML> getCursos() {
		return cursos;
	}

	public void setCursos(List<CursoXML> cursos) {
		this.cursos = cursos;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getFechaExportacion() {
		return fechaExportacion;
	}

	public void setFechaExportacion(String fechaExportacion) {
		this.fechaExportacion = fechaExportacion;
	}

	public List<ContenidoXML> getContenidos() {
		return contenidos;
	}

	public void setContenidos(List<ContenidoXML> contenidos) {
		this.contenidos = contenidos;
	}

	public List<GrupoXML> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<GrupoXML> grupos) {
		this.grupos = grupos;
	}
	
	

	public List<AlumnoXML> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoXML> alumnos) {
		this.alumnos = alumnos;
	}


	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	

	public List<MatriculaXML> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<MatriculaXML> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public String toString() {
		return "CentroXML [cursos=" + cursos + ", contenidos=" + contenidos + ", grupos=" + grupos + ", alumnos="
				+ alumnos + ", matriculas=" + matriculas + ", codigo=" + codigo + ", fechaExportacion="
				+ fechaExportacion + ", curso=" + curso + "]";
	}

	

	
	
	
	
	
	
	
	
	 
	 
}
