package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import dao.EjemplarDAO;
import dao.HistorialDAO;
import model.EntregaTabla;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;


public class EntregasService {
	private HistorialDAO historialDAO;
	private EjemplarDAO ejemplarDAO;
	private DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();
	
	
	public EntregasService() {
		historialDAO = new HistorialDAO();
		ejemplarDAO = new EjemplarDAO();
	}
	
	public List<Libro> getLibros(Matricula matricula) {
		List<Libro> listaLibros = new ArrayList<>();
		List<Historial> listaHistorial = historialDAO.getAllByAlumno(matricula.getAlumno().getId());
		for(Libro libro: matricula.getContenidoBean().getLibros()) {
			if((libro.getObsoleto() == 1 || inHistorial(listaHistorial, libro))){
				continue;
			}
			listaLibros.add(libro);
		}
		
		return listaLibros;
		
	}

	private boolean inHistorial(List<Historial> listaHistorial, Libro libro) {
		for(Historial historial:listaHistorial) {
			if(historial.getEjemplare().getLibro().getCodigo().equals(libro.getCodigo()) && historial.getFechaFinal() == null) {
				
				return true;
			}
		}
		return false;
	}

	public List<EntregaTabla> getHistorialTabla(String id) {
		List<Historial> listaHistorial = new ArrayList<>();
		List<EntregaTabla> historialTabla = new ArrayList<>();
		
		listaHistorial = historialDAO.getAllByAlumno(id);
		
		for(Historial historial:listaHistorial) {
			if(historial.getFechaFinal() != null)continue;
			EntregaTabla entregaTabla = new EntregaTabla();
			entregaTabla.setAsignatura(historial.getEjemplare().getLibro().getContenido().getNombreCas());
			entregaTabla.setCurso(""+historial.getCursoEscolar());
			entregaTabla.setEstadoInicial(estadoTabla(historial.getEstadoInicial()));
			entregaTabla.setIdEjemplar(historial.getEjemplare().getCodigo());
			entregaTabla.setFecha_inicial(""+historial.getFechaInicial());
			
			historialTabla.add(entregaTabla);
			
		}
		
		
		
		return historialTabla;
	}
	
	private String estadoTabla(int estado) {
		switch (estado) {
		case 0:
			return "Perfecto";
		case 1:
			return "Regular";
		case 2:
			return "Mal";
		default:
			return "Perfecto";
		}
	}

	@SuppressWarnings("deprecation")
	public void entregarLibro(Libro selectedLibro,Alumno alumno) throws Exception {
		Date dateobj = new Date();
		calendar.setTime(dateobj);
		Ejemplare ejemplar = null;
		List<Ejemplare> listaEjemplare = this.ejemplarDAO.getAllByIdLibro(selectedLibro.getId());
		for(Ejemplare ejemplare: listaEjemplare) {
			if(ejemplare.getPrestado() == 0) {
				ejemplar = ejemplare;
				break;
			}
		}
		if(ejemplar == null)throw new Exception("No hay ejemplares disponibles");
		
		try {
			Historial historial = new Historial();
			historial.setAlumno(alumno);
			historial.setFechaInicial(new Date(this.df.format(dateobj)));
			historial.setEjemplare(ejemplar);
			historial.setCursoEscolar(alumno.getCursoBean().getCursoEscolar());
			historial.setEstadoInicial(ejemplar.getEstado());
			historial.setObservaciones("");
			Integer lastId = this.historialDAO.getLastId();
			if(lastId == null) {
				lastId = 0;
			}
			historial.setId(lastId+1);
			
			this.historialDAO.attachDirty(historial);
			ejemplar.setPrestado(new Integer(1).byteValue());
			this.ejemplarDAO.attachDirty(ejemplar);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	public Ejemplare scanEjemplar(String text) throws Exception {
		Ejemplare ejemplar = ejemplarDAO.findById(text);
		if(ejemplar == null)throw new Exception("No se encontro el ejemplar");
		if(ejemplar.getPrestado() == 1) throw new Exception("El ejemplar ya está prestado");
		return ejemplar;
	}

	@SuppressWarnings("deprecation")
	public void entregarLibroScaneado(Ejemplare ejemplar, Alumno alumno) throws Exception {
		Date dateobj = new Date();
		calendar.setTime(dateobj);
		
		try {
			
			Historial historial = new Historial();
			historial.setAlumno(alumno);
			historial.setFechaInicial(new Date(this.df.format(dateobj)));
			historial.setEjemplare(ejemplar);
			historial.setCursoEscolar(calendar.get(Calendar.YEAR));
			historial.setEstadoInicial(ejemplar.getEstado());
			historial.setObservaciones("");
			historial.setId(this.historialDAO.getLastId()+1);
			
			this.historialDAO.attachDirty(historial);
			ejemplar.setPrestado(new Integer(1).byteValue());
			this.ejemplarDAO.attachDirty(ejemplar);
			
		} catch (Exception e) {
			throw e;
		}
		
	}

	@SuppressWarnings("deprecation")
	public boolean currentMatricula(Matricula matricula) {
		Date currentDate = new Date();
		Date principioCurso = new Date("09/01/"+matricula.getCursoEscolar());
		Date finalCurso = new Date("08/31/"+(matricula.getCursoEscolar()+1));
		return (currentDate.after(principioCurso) && currentDate.before(finalCurso));
	}
	


	

}
