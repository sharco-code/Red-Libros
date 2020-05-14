package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import dao.EjemplarDAO;
import dao.HistorialDAO;
import dao.LibroDAO;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Historial;
import pojo.Libro;

public class DevolucionesService {
	private HistorialDAO historialDAO;
	private EjemplarDAO ejemplarDAO;
	private LibroDAO libroDAO;
	private DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();
	
	public DevolucionesService() {
		historialDAO = new HistorialDAO();
		ejemplarDAO = new EjemplarDAO();
		libroDAO = new LibroDAO();
	}
	
	
	
	public void devolverLibro(Libro selectedLibro,Alumno alumno) throws Exception {
		// TODO Auto-generated method stub
		Date dateobj = new Date();
		calendar.setTime(dateobj);
		Ejemplare ejemplar = null;
		Historial historial = null;
		for(Historial historialAlumno:alumno.getHistorials()) {
			if(historialAlumno.getEjemplare().getLibro().getCodigo().equals(selectedLibro.getCodigo())) {
				historial = historialAlumno;
				ejemplar = historialAlumno.getEjemplare();
				break;
			}
		}
		
		if(ejemplar == null)throw new Exception("No se encontro el ejemplar");
		if(historial == null)throw new Exception("No se encontro el historial");
		
		try {
			historial.setFechaFinal(new Date(this.df.format(dateobj)));
			
			
			this.historialDAO.merge(historial);
			ejemplar.setPrestado(new Integer(0).byteValue());
			this.ejemplarDAO.merge(ejemplar);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception("Error de conexion con el servidor");
		}
		
		
		
		
	}



	public Ejemplare scanEjemplar(String text) throws Exception {
		// TODO Auto-generated method stub
		Ejemplare ejemplar = ejemplarDAO.findById(text);
		if(ejemplar == null)throw new Exception("No se encontro el ejemplar");
		if(ejemplar.getPrestado() == 0) throw new Exception("El ejemplar no esta prestado");
		return ejemplar;
	}



	public void devolverLibroScaneado(Ejemplare ejemplar, Alumno alumno) throws Exception {
		// TODO Auto-generated method stub
		Date dateobj = new Date();
		calendar.setTime(dateobj);
		Historial historial = null;
		for(Historial historialAlumno:alumno.getHistorials()) {
			if(historialAlumno.getEjemplare().getLibro().getCodigo().equals(ejemplar.getLibro().getCodigo())) {
				historial = historialAlumno;
				break;
			}
		}
		
		if(historial == null)throw new Exception("No se encontro el historial");
		
		try {
			historial.setFechaFinal(new Date(this.df.format(dateobj)));
			
			this.historialDAO.merge(historial);
			ejemplar.setPrestado(new Integer(0).byteValue());
			this.ejemplarDAO.merge(ejemplar);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
		
	}
}
