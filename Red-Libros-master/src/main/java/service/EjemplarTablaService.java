package service;

import java.util.ArrayList;
import java.util.List;

import model.EjemplarTabla;
import pojo.Ejemplare;

public class EjemplarTablaService {
	
	
	public List<EjemplarTabla> converToEjemplarTabla(List<Ejemplare> listaEjemplare){
		
		List<EjemplarTabla> listaEjemplarTabla = new ArrayList<>();
		
		for (Ejemplare ejemplar : listaEjemplare) {
			EjemplarTabla ejemplarTabla = new EjemplarTabla();
			ejemplarTabla.setCodigo(ejemplar.getCodigo());
			ejemplarTabla.setId(ejemplar.getId());
			ejemplarTabla.setEstado(estadoTabla(ejemplar));
			ejemplarTabla.setPrestado((pretadoTabla(ejemplar)));
			listaEjemplarTabla.add(ejemplarTabla);
		}
		
		return listaEjemplarTabla;
		
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
	
	private String pretadoTabla(Ejemplare ejemplar) {
		switch (ejemplar.getPrestado()) {
		case 0:
			return "No prestado";
		case 1:
			return "Prestado";
		default:
			return "No prestado";
		}
	}
	
	public Byte convertToEstadoLibro(String estado) {
		switch (estado) {
		case "Perfecto":
			return 0;
		case "Regular":
			return 1;
		case "Mal":
			return 2;
		default:
			return 0;
		}
		
		
	}
	
	public Byte convertToPrestadoLibro(String prestado) {
		switch (prestado) {
		case "No prestado":
			return 0;
		case "Prestado":
			return 1;
		default:
			return 0;
		}
	}
	
	

}
