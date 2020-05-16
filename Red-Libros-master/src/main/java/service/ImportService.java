package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;

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
		
		try { 
			
	        CentroXML que =   readXML(ruta);
	        for(CursoXML cursoXML:que.getCursos()) {
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
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }  
		
	}
	
	public void importarContenido(String ruta) throws Exception {
		try {  
			   
	        CentroXML que=   readXML(ruta);
	        for(ContenidoXML contenidoXML:que.getContenidos()) {
	        	if(contenidoDAO.getByCodigo(contenidoXML.getCodigo()) != null) continue;
	        	Contenido contenido = new Contenido();
	        	contenido.setCodigo(contenidoXML.getCodigo());
	        	contenido.setEnsenanza(contenidoXML.getEnsenanza());
	        	contenido.setNombreCas(contenidoXML.getNombre_cas());
	        	contenido.setNombreVal(contenidoXML.getNombre_val());
	        	contenido.setCursoBean(cursoDAO.findById(contenidoXML.getCurso()));
	        	
	        	contenidoDAO.merge(contenido);
	        }
	   
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }
	}
	
	public void importarGrupo(String ruta) throws Exception {
		try {  
			   
	         
	        CentroXML que=   readXML(ruta);
	        for(GrupoXML grupoXML :que.getGrupos()) {
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
	   
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }
	}
	
	public void importarAlumno(String ruta) throws Exception {
		try {  
			   
	        CentroXML que=   readXML(ruta);
	        for(AlumnoXML alumnoXML:que.getAlumnos()) {
	        	Alumno alumno = new Alumno();
	        	alumno.setId(alumnoXML.getNia());
	        	alumno.setNia(alumnoXML.getNia());
	        	alumno.setNombre(alumnoXML.getNombre());
	        	alumno.setApellido1(alumnoXML.getApellido1());
	        	alumno.setApellido2(alumnoXML.getApellido2());
	        	alumno.setGrupoBean(grupoDAO.findById(alumnoXML.getGrupo()));
	        	alumno.setCursoBean(cursoDAO.findById(alumnoXML.getCurso()));
	        	alumno.setExpediente(alumnoXML.getExpediente());
	        	alumnoDAO.merge(alumno);
	        }
	        
	   
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }
	}
	
	public void importarMatricula(String ruta) throws Exception {
		try {  
			   
	        CentroXML que=   readXML(ruta);
	        for(MatriculaXML matriculaXML:que.getMatriculas()) {
	        	if(matriculaDAO.exist(matriculaXML.getAlumno(), matriculaXML.getCurso(), matriculaXML.getContenido()))continue;
	        	Matricula matricula = new Matricula();
	        	matricula.setCursoEscolar(Integer.parseInt(que.getCurso()));
	        	matricula.setCursoPendiente(matriculaXML.getCurso_pendiente());
	        	matricula.setEnsenanza(matriculaXML.getEnsenanza());
	        	if(matriculaXML.getFec_ini_acis() != null && !matriculaXML.getFec_ini_acis().isEmpty() && !matriculaXML.getFec_ini_acis().isBlank()) {
	        		matricula.setFecIniAcis(new Date(matriculaXML.getFec_ini_acis()));
	        	}else {
	        		matricula.setFecIniAcis(new Date());
	        	}
	        	if(matriculaXML.getFec_fin_acis() != null && !matriculaXML.getFec_fin_acis().isEmpty() && !matriculaXML.getFec_fin_acis().isBlank()) {
	        		matricula.setFecFinAcis(new Date(matriculaXML.getFec_fin_acis()));
	        	}else {
	        		matricula.setFecFinAcis(new Date());
	        	}
	        	
	        	matricula.setIdioma(matriculaXML.getIdioma());
	        	matricula.setTipoBasico(matriculaXML.getTipo_basico());
	        	matricula.setTipoPredom(matriculaXML.getTipo_predom());
	        	matricula.setAcis(matriculaXML.getAcis());
	        	matricula.setCurRefAcis(matriculaXML.getCur_ref_acis());
	        	
	        	matricula.setAlumno(alumnoDAO.findById(matriculaXML.getAlumno()));
	        	matricula.setCursoBean(cursoDAO.findById(matriculaXML.getCurso()));
	        	matricula.setContenidoBean(contenidoDAO.getByCodigo(matriculaXML.getContenido()));
	        	
	        	matriculaDAO.merge(matricula);
	        	
	        }
	        
	   
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }
	}
	
	
	private CentroXML readXML(String ruta) throws Exception {
		File origen = new File(ruta);
		if(!origen.canRead()) throw new Exception("No se puede encontrar el fichero");
		File file = new File(ruta); 
        JAXBContext jaxbContext = JAXBContext.newInstance(CentroXML.class); 
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller(); 
        return (CentroXML) jaxbUnmarshaller.unmarshal(file);
	}
	
	

}
