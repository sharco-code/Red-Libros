package service;

import java.util.ArrayList;
import java.util.List;

import model.StockTabla;
import pojo.Libro;

public class StockTablaService {
	
	public List<StockTabla> converToStockTabla(List<Libro> listaLibro){
		
		List<StockTabla> listaStockTabla = new ArrayList<>();
		
		for (Libro libro : listaLibro) {
			if(libro.getObsoleto() != 0) continue;
			StockTabla libroTabla = new StockTabla();
			libroTabla.setCodigo(libro.getCodigo());
			libroTabla.setId(libro.getId());
			libroTabla.setCurso(libro.getContenido().getCursoBean().getNombreCas());
			libroTabla.setIsbn(libro.getIsbn());
			libroTabla.setUnidades(libro.getUnidades());
			libroTabla.setPrecio(libro.getPrecio());
			libroTabla.setObsoleto(libro.getObsoleto());
			libroTabla.setNombre(libro.getNombre());
			listaStockTabla.add(libroTabla);
		}
		
		return listaStockTabla;
		
	}

}
