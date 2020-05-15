package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;

import pojo.Contenido;
import pojo.Curso;
import pojo.Grupo;
import xmlModels.CentroXML;
import xmlModels.ContenidoXML;
import xmlModels.CursoXML;
import xmlModels.GrupoXML;

public class ImportService {
	
	
	public void ImportarCurso(String ruta) throws Exception {
		
		try { 
			List<Curso> listaCursos = new ArrayList<>();
			
	        CentroXML que =   readXML(ruta);
	        for(CursoXML cursoXML:que.getCursos()) {
	        	Curso curso = new Curso();
	        	curso.setAbreviatura(cursoXML.getAbreviatura());
	        	curso.setCodigo(cursoXML.getCodigo());
	        	curso.setEnsenanza(cursoXML.getEnsenanza());
	        	curso.setIdPadre(cursoXML.getPadre());
	        	curso.setNombreCas(cursoXML.getNombre_cas());
	        	curso.setNombreVal(cursoXML.getNombre_val());
	        	listaCursos.add(curso);
	        }
	   		
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }  
		
	}
	
	public void ImportarContenido(String ruta) throws Exception {
		try {  
			   
	        CentroXML que=   readXML(ruta);
	        List<Contenido> listaContenido = new ArrayList<>();
	        for(ContenidoXML contenidoXML:que.getContenidos()) {
	        	Contenido contenido = new Contenido();
	        	contenido.setCodigo(contenidoXML.getCodigo());
	        	contenido.setEnsenanza(contenidoXML.getEnsenanza());
	        	contenido.setNombreCas(contenidoXML.getNombre_cas());
	        	contenido.setNombreVal(contenidoXML.getNombre_val());
	        	listaContenido.add(contenido);
	        }
	   
	      } catch (JAXBException e) {  
	        e.printStackTrace();  
	      }
	}
	
	public void ImportarGrupo(String ruta) throws Exception {
		try {  
			   
	         
	        CentroXML que=   readXML(ruta);
	        List<Grupo> listaGrupo = new ArrayList<>();
	        for(GrupoXML grupoXML :que.getGrupos()) {
	        	Grupo grupo = new Grupo();
	        	grupo.setAula(grupoXML.getAula());
	        	grupo.setCapacidad((short) Integer.parseInt(grupoXML.getCapacidad()));
	        	grupo.setCodigo(grupoXML.getCodigo());
	        	grupo.setEnsenanza(grupoXML.getEnsenanza());
	        	grupo.setLinea(grupoXML.getLinea());
	        	grupo.setModalidad(grupoXML.getModalidad());
	        	grupo.setOficial(grupoXML.getOficial());
	        	grupo.setNombre(grupoXML.getNombre());
	        	grupo.setTutorPpal(grupoXML.getTutor_ppal());
	        	grupo.setTutorSec(grupoXML.getTutor_sec());
	        	grupo.setTurno(grupoXML.getTurno());
	        	
	        
	        	listaGrupo.add(grupo);
	        	
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
