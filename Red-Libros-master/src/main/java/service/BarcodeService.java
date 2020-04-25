package service;



import java.awt.image.BufferedImage;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;


public class BarcodeService {
	
	
	
	public  BufferedImage generateImage(String barcodeText) {
	    Code128Bean b = new Code128Bean();
	    BitmapCanvasProvider canvas = 
	      new BitmapCanvasProvider(400, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	 
	    b.generateBarcode(canvas, barcodeText);
	    return canvas.getBufferedImage();
	}

}
