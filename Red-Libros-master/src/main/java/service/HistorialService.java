package service;

import java.util.ArrayList;
import java.util.List;

import dao.EjemplarDAO;
import dao.HistorialDAO;
import model.EjemplarHistorial;
import model.HistorialTabla;
import pojo.Ejemplare;
import pojo.Historial;

public class HistorialService {
	
	private EjemplarDAO ejemplarDAO;
	
	public HistorialService() {
		this.ejemplarDAO = new EjemplarDAO();
	}

	public List<EjemplarHistorial> getEjemplares() {
		// TODO Auto-generated method stub
		List<EjemplarHistorial> listaEjemplarHistorial = new ArrayList<>();
		EjemplarHistorial ejemplarHistorial;
		for(Ejemplare ejemplar:this.ejemplarDAO.getAllWithLibros()) {
			ejemplarHistorial = new EjemplarHistorial();
			ejemplarHistorial.setCodigoEjemplar(ejemplar.getCodigo());
			ejemplarHistorial.setNombreLibro(ejemplar.getLibro().getNombre());
			ejemplarHistorial.setEjemplar(ejemplar);
			ejemplarHistorial.setEstado(estadoTabla(ejemplar));
			ejemplarHistorial.setPrestado(prestadoTabla(ejemplar));
			listaEjemplarHistorial.add(ejemplarHistorial);
		}
		return listaEjemplarHistorial;
	}
	private String estadoTabla(Ejemplare ejemplar) {
		switch (ejemplar.getEstado()) {
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
	
	private String prestadoTabla(Ejemplare ejemplar) {
		switch (ejemplar.getPrestado()) {
		case 0:
			return "No prestado";
		case 1:
			return "Prestado";
		default:
			return "No prestado";
		}
	}
	
	private String estadoIntToString(Integer estado) {
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

	public List<HistorialTabla> getHistorial(Ejemplare ejemplar) {
		// TODO Auto-generated method stub
		List<HistorialTabla> listaHistorialTabla = new ArrayList<>();
		for(Historial historial:ejemplar.getHistorials()) {
			HistorialTabla historialTabla = new HistorialTabla();
			historialTabla.setCurso(""+historial.getCursoEscolar());
			historialTabla.setNia(historial.getAlumno().getNia());
			
			System.out.println("10"+historial.getEstadoFinal());
			System.out.println("20"+estadoIntToString(historial.getEstadoFinal()));
			historialTabla.setEstado_final(estadoIntToString(historial.getEstadoFinal()));
			
			historialTabla.setEstado_inicial(estadoIntToString(historial.getEstadoInicial()));
			
			if(historial.getAlumno().getApellido2() == null) {
				historialTabla.setNombreCompleto(historial.getAlumno().getNombre()+" "+historial.getAlumno().getApellido1());
			}else {
				historialTabla.setNombreCompleto(historial.getAlumno().getNombre()+" "+historial.getAlumno().getApellido1()+" "+historial.getAlumno().getApellido2());
			}
			if(historial.getFechaFinal() == null) {
				historialTabla.setFechaDevolucion("En posesion");
			}else {
				historialTabla.setFechaDevolucion(historial.getFechaFinal().toString());
			}
			listaHistorialTabla.add(historialTabla);
		}
		
		return listaHistorialTabla;
	}

}
