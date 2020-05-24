package service;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import dao.AlumnoDAO;
import dao.ContenidoDAO;
import dao.CursoDAO;
import dao.GrupoDAO;
import dao.MatriculaDAO;
import pojo.Alumno;
import pojo.Contenido;
import pojo.Curso;
import pojo.Grupo;
import pojo.Matricula;
import xmlModels.AlumnoXML;
import xmlModels.CentroXML;
import xmlModels.ContenidoXML;
import xmlModels.CursoXML;
import xmlModels.GrupoXML;
import xmlModels.MatriculaXML;

public class ImportService {

	private CursoDAO cursoDAO = new CursoDAO();
	private AlumnoDAO alumnoDAO = new AlumnoDAO();
	private ContenidoDAO contenidoDAO = new ContenidoDAO();
	private GrupoDAO grupoDAO = new GrupoDAO();
	private MatriculaDAO matriculaDAO = new MatriculaDAO();

	public void importarCurso(String ruta) throws Exception {

			CentroXML que = readXML(ruta);
			
			List<CursoXML> cursosXML = que.getCursos();
			if(cursosXML == null) {
				throw new Exception("Error en la importación de cursos\nComprueba que el archivo seleccionado es el correcto");
			}
			for (CursoXML cursoXML : cursosXML) {
				Curso curso = new Curso();
				curso.setAbreviatura(cursoXML.getAbreviatura());
				curso.setId(cursoXML.getCodigo());
				curso.setCodigo(cursoXML.getCodigo());	
				curso.setEnsenanza(cursoXML.getEnsenanza());
				curso.setIdPadre(cursoXML.getPadre());
				curso.setNombreCas(cursoXML.getNombre_cas());
				curso.setNombreVal(cursoXML.getNombre_val());
				curso.setCursoEscolar(Integer.parseInt(que.getCurso()));

				cursoDAO.merge(curso);
			}
	}

	public void importarContenido(String ruta) throws Exception {

			CentroXML que = readXML(ruta);
			
			List<ContenidoXML> contenidosXML = que.getContenidos();
			if(contenidosXML == null) {
				throw new Exception("Error en la importación de contenidos\nComprueba que el archivo seleccionado es el correcto");
			}
			
			for (ContenidoXML contenidoXML : contenidosXML) {
				if (contenidoDAO.getByCodigo(contenidoXML.getCodigo()) != null)
					continue;
				Contenido contenido = new Contenido();
				contenido.setCodigo(contenidoXML.getCodigo());
				contenido.setEnsenanza(contenidoXML.getEnsenanza());
				contenido.setNombreCas(contenidoXML.getNombre_cas());
				contenido.setNombreVal(contenidoXML.getNombre_val());
				
				Curso curso = cursoDAO.findById(contenidoXML.getCurso());
				if(curso == null) {
					throw new Exception("Error en la importación de contenido\nAsegurese de haber importado los cursos");
				}
				contenido.setCursoBean(curso);

				contenidoDAO.merge(contenido);
			}

	}

	public void importarGrupo(String ruta) throws Exception {


			CentroXML que = readXML(ruta);
			
			List<GrupoXML> gruposXML = que.getGrupos();
			if(gruposXML == null) {
				throw new Exception("Error en la importación de grupos\nComprueba que el archivo seleccionado es el correcto");
			}
			
			
			for (GrupoXML grupoXML : gruposXML) {
				Grupo grupo = new Grupo();
				grupo.setAula(grupoXML.getAula());
				grupo.setCapacidad((short) Integer.parseInt(grupoXML.getCapacidad()));
				grupo.setId(grupoXML.getCodigo());
				grupo.setCodigo(grupoXML.getCodigo());
				grupo.setEnsenanza(grupoXML.getEnsenanza());
				grupo.setLinea(grupoXML.getLinea());
				grupo.setModalidad(grupoXML.getModalidad());
				grupo.setOficial(grupoXML.getOficial());
				grupo.setNombre(grupoXML.getNombre());
				grupo.setTutorPpal(grupoXML.getTutor_ppal());
				grupo.setTutorSec(grupoXML.getTutor_sec());
				grupo.setTurno(grupoXML.getTurno());

				grupoDAO.merge(grupo);

			}

	}

	public void importarAlumno(String ruta) throws Exception {

			CentroXML que = readXML(ruta);
			
			List<AlumnoXML> alumnosXML = que.getAlumnos();
			if(alumnosXML == null) {
				throw new Exception("Error en la importación de alumnos\nComprueba que el archivo seleccionado es el correcto");
			}
			
			for (AlumnoXML alumnoXML : alumnosXML) {
				Alumno alumno = new Alumno();
				alumno.setId(alumnoXML.getNia());
				alumno.setNia(alumnoXML.getNia());
				alumno.setNombre(alumnoXML.getNombre());
				alumno.setApellido1(alumnoXML.getApellido1());
				alumno.setApellido2(alumnoXML.getApellido2());
				
				Grupo grupo = grupoDAO.findById(alumnoXML.getGrupo());
				if(grupo == null ) {
					throw new Exception("Error en la importación de alumnos\nAsegurese de haber importado los grupos");
				}
				alumno.setGrupoBean(grupo);

				
				Curso curso = cursoDAO.findById(alumnoXML.getCurso());
				if(curso == null) {
					throw new Exception("Error en la importación de alumnos\nAsegurese de haber importado los cursos");
				}
				alumno.setCursoBean(curso);
				
				alumno.setExpediente(alumnoXML.getExpediente());
				alumnoDAO.merge(alumno);
			}

	}

	@SuppressWarnings("deprecation")
	public void importarMatricula(String ruta) throws Exception {

			CentroXML que = readXML(ruta);
			for (MatriculaXML matriculaXML : que.getMatriculas()) {
				if (matriculaDAO.exist(matriculaXML.getAlumno(), matriculaXML.getCurso(), matriculaXML.getContenido()))
					continue;
				Matricula matricula = new Matricula();
				matricula.setCursoEscolar(Integer.parseInt(que.getCurso()));
				matricula.setCursoPendiente(matriculaXML.getCurso_pendiente());
				matricula.setEnsenanza(matriculaXML.getEnsenanza());
				if (matriculaXML.getFec_ini_acis() != null && !matriculaXML.getFec_ini_acis().isEmpty()
						&& !matriculaXML.getFec_ini_acis().isBlank()) {
					matricula.setFecIniAcis(new Date(matriculaXML.getFec_ini_acis()));
				} else {
					matricula.setFecIniAcis(new Date());
				}
				if (matriculaXML.getFec_fin_acis() != null && !matriculaXML.getFec_fin_acis().isEmpty()
						&& !matriculaXML.getFec_fin_acis().isBlank()) {
					matricula.setFecFinAcis(new Date(matriculaXML.getFec_fin_acis()));
				} else {
					matricula.setFecFinAcis(new Date());
				}

				matricula.setIdioma(matriculaXML.getIdioma());
				matricula.setTipoBasico(matriculaXML.getTipo_basico());
				matricula.setTipoPredom(matriculaXML.getTipo_predom());
				matricula.setAcis(matriculaXML.getAcis());
				matricula.setCurRefAcis(matriculaXML.getCur_ref_acis());

				Alumno alumno = alumnoDAO.findById(matriculaXML.getAlumno());
				if(alumno == null) {
					throw new Exception("Error en la importación de matriculas\nAasegurese de haber importado los alumnos");
				}
				matricula.setAlumno(alumno);
				
				Curso curso =cursoDAO.findById(matriculaXML.getCurso());
				if(curso == null) {
					throw new Exception("Error en la importación de matriculas\nAsegurese de haber importado los cursos");
				}
				matricula.setCursoBean(curso);
				
				Contenido contenido = contenidoDAO.getByCodigo(matriculaXML.getContenido());
				if(contenido == null) {
					throw new Exception("Error en la importación de matriculas\nAsegurese de haber importado contenido");
				}
				matricula.setContenidoBean(contenido);

				matriculaDAO.merge(matricula);

			}

	}

	private CentroXML readXML(String ruta) throws Exception {
		File origen = new File(ruta);
		if (!origen.canRead()) {
			throw new Exception("No se puede encontrar el fichero");
		}
		File file = new File(ruta);
		JAXBContext jaxbContext = JAXBContext.newInstance(CentroXML.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		try {
			return (CentroXML) jaxbUnmarshaller.unmarshal(file);
		} catch (UnmarshalException e) {
			throw new Exception("El archivo seleccionado no es correcto\nComprueba si es el XML de importación indicado");
		}

	}

}
