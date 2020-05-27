package service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Options;

public class PdfService {
	private final int  PADDING_LEFT = 0;
	private final int  PADDING_RIGHT = 0;
	private final int  PADDING_TOP = 60;
	private final int  PADDING_BOTTOM = 60;
	private final int DEFAULT_COLUMN_WIDTH = 200;
	private final float CELL_HEIGHT = 50f;
	private int columns;
	private int rows;
	private float [] pointColumnWidths;
	
	
	
	public PdfService() {
	}
	
	private void setColumnRows() {
		Options options = SettingsService.getOptions();
		if(options.getColumnas() == null) {
			this.columns = 2;
		}else {
			this.columns = Integer.parseInt(options.getColumnas());
		}
		if(options.getFilas() == null) {
			this.rows = 2;
		}else {
			this.rows = Integer.parseInt(options.getFilas());
		}
		
		setColumnsWidth();
	}

	private void setColumnsWidth() {
		this.pointColumnWidths = new float[this.columns];
		
		for (int i = 0; i < this.pointColumnWidths.length; i++) {
			this.pointColumnWidths[i] = DEFAULT_COLUMN_WIDTH;
		}
	}

	public void createPDF(List<Image> codigosBarra,String ruta,int columnaEmpieza,int filaEmpieza) throws FileNotFoundException, DocumentException {
		setColumnRows();
		Document document = new Document(PageSize.A4, PADDING_LEFT, PADDING_RIGHT, PADDING_TOP, PADDING_BOTTOM);
		PdfWriter.getInstance(document, new FileOutputStream(ruta+"//barcodes.pdf"));
	
		document.open();
		
		
		
		PdfPTable table = new PdfPTable(this.pointColumnWidths);
		rellenarHastaEmpieza(columnaEmpieza, filaEmpieza, table);
		
		for (Image image : codigosBarra) {
			if(table.getRows().size() >= this.rows) {
				document.add(table);
				document.newPage();
				table = new PdfPTable(this.pointColumnWidths);
			}
			addCell(table,image);
		} 
		
		int huecosLibres = ((this.columns*this.rows)/ this.columns)+1;
		rellenarHuecos(table, huecosLibres);
		document.add(table);
		

		document.close();
		
	}

	private void rellenarHastaEmpieza(int columnaEmpieza, int filaEmpieza, PdfPTable table) {
		while(table.getRows().size() < (filaEmpieza-1)) {
			rellenarVacio(table);
		}
		for (int i = 0; i < (columnaEmpieza-1); i++) {
			rellenarVacio(table);
		}
	}

	private void rellenarHuecos(PdfPTable table, int huecosLibres) {
		PdfPCell cell = new PdfPCell();
		for (int i = 0; i < huecosLibres; i++) {
		    cell.setBorder(0);
		    cell.setFixedHeight(CELL_HEIGHT);
			table.addCell(cell);
		}
	}
	
	void rellenarVacio(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBorder(0);
	    cell.setFixedHeight(CELL_HEIGHT);
		table.addCell(cell);
	}
	
	private void addCell(PdfPTable table,Image codigo) {
		
		PdfPCell cell = new PdfPCell(codigo);
        cell.setBorder(0);
        cell.setFixedHeight(CELL_HEIGHT);
        
	    table.addCell(cell);
	    
	}

}
