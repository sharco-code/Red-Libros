package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import dao.EjemplarDAO;
import dao.HistorialDAO;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Historial;

public class DevolucionesService {
	private HistorialDAO historialDAO;
	private EjemplarDAO ejemplarDAO;
	private DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Calendar calendar = Calendar.getInstance();
	
	public DevolucionesService() {
		historialDAO = new HistorialDAO();
		ejemplarDAO = new EjemplarDAO();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void devolverLibro(Ejemplare ejemplar,Alumno alumno, int estado) throws Exception {
		Date dateobj = new Date();
		calendar.setTime(dateobj);
		Historial historial = null;
		for(Historial historialAlumno:alumno.getHistorials()) {
			if(historialAlumno.getFechaFinal() != null) continue;
			if(historialAlumno.getEjemplare().getCodigo().equals(ejemplar.getCodigo())) {
				historial = historialAlumno;
				break;
			}
		}
		
		if(ejemplar == null)throw new Exception("No se encontro el ejemplar");
		if(historial == null)throw new Exception("No se encontro el historial");
		
		try {
			historial.setFechaFinal(new Date(this.df.format(dateobj)));
			
			historial.setEstadoInicial(ejemplar.getEstado());
			historial.setEstadoFinal(estado);
			
			this.historialDAO.merge(historial);
			ejemplar.setPrestado(new Integer(0).byteValue());
			ejemplar.setEstado(estado);
			this.ejemplarDAO.merge(ejemplar);
			
		} catch (Exception e) {
			throw new Exception("Error de conexion con el servidor");
		}
	}

	public Ejemplare scanEjemplar(String text) throws Exception {
		Ejemplare ejemplar = ejemplarDAO.findById(text);
		if(ejemplar == null)throw new Exception("No se encontro el ejemplar");
		if(ejemplar.getPrestado() == 0) throw new Exception("El ejemplar no esta prestado");
		return ejemplar;
	}

	@SuppressWarnings("deprecation")
	public void devolverLibroScaneado(Ejemplare ejemplar, Alumno alumno) throws Exception {
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
			throw e;
		}
		
	}
}
