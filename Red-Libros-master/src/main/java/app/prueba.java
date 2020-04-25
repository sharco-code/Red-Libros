package app;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.itextpdf.text.Image;

import service.BarcodeService;
import service.PdfService;

public class prueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PdfService pdfService = new PdfService();
		BarcodeService barcodeService = new BarcodeService();
		
		List<Image> codigos = new ArrayList<>();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(barcodeService.generateImage("prueba"), "png", baos);
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
			codigos.add(Image.getInstance(baos.toByteArray()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		try {
			pdfService.createPDF(codigos);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
