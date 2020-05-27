package service;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import model.Options;
import service.Label;

public class PdfService {
	private int column_width = (int) Utilities.millimetersToPoints(50);
	private float cell_height = Utilities.millimetersToPoints(50);
	private float cell_padding = 0;
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
			this.rows = 5;
		}else {
			this.rows = Integer.parseInt(options.getFilas());
		}
		
		setColumnsWidth();
	}

	private void setColumnsWidth() {
		this.pointColumnWidths = new float[this.columns];
		
		for (int i = 0; i < this.pointColumnWidths.length; i++) {
			this.pointColumnWidths[i] = column_width;
		}
	}

	public void createPDF(List<Image> codigosBarra,String ruta,int columnaEmpieza,int filaEmpieza) throws FileNotFoundException, DocumentException {
		setColumnRows();
		Document document = new Document(PageSize.A4, 0, 0, 15, 0);
		PdfWriter.getInstance(document, new FileOutputStream(ruta+"//barcodes.pdf"));
		
		document.open();
		
		setWidthHeight();
		
		PdfPTable table = new PdfPTable(this.pointColumnWidths);
		table.setKeepTogether(true);
		table.setWidthPercentage(100);
		
		rellenarHastaEmpieza(columnaEmpieza, filaEmpieza, table);
		for (Image image : codigosBarra) {
			if(table.getRows().size() >= this.rows) {
				document.add(table);
				document.newPage();
				table = new PdfPTable(this.pointColumnWidths);
				table.setKeepTogether(true);
				table.setWidthPercentage(100);
			}
			addCell(table,image);
		} 
		
		int huecosLibres = ((this.columns*this.rows)/ this.columns)+1;
		rellenarHuecos(table, huecosLibres);
		
		document.add(table);
		

		document.close();
		
	}

	private void setWidthHeight() {
		if(this.columns == 2 && this.rows == 5) {
			this.column_width = (int) Utilities.millimetersToPoints(Label.twoFiveWidth);
			this.cell_height = Utilities.millimetersToPoints(Label.twoFiveHeight);
			this.cell_padding = Label.twoFivePadding;
		}
	}
	
	

	private void rellenarHastaEmpieza(int columnaEmpieza, int filaEmpieza, PdfPTable table) {
		while(table.getRows().size() < (filaEmpieza-1)) {
			rellenarVacio(table);
		}
		for (int i = 0; i < (columnaEmpieza-1); i++) {
			rellenarVacio(table);
		}
	}
	
	void rellenarVacio(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
        cell.setBorder(0);
        cell.setPadding(cell_padding);
        cell.setFixedHeight(cell_height);
		table.addCell(cell);
	}
	
	private void rellenarHuecos(PdfPTable table, int huecosLibres) {
		PdfPCell cell = new PdfPCell();
		for (int i = 0; i < huecosLibres; i++) {
		    cell.setBorder(0);
			table.addCell(cell);
		}
	}
	
	private void addCell(PdfPTable table,Image codigo) {
		PdfPCell cell = new PdfPCell(codigo);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
		cell.setPadding(cell_padding);
        cell.setBorder(0);
        cell.setFixedHeight(cell_height);
	    table.addCell(cell);
	    
	}

}
