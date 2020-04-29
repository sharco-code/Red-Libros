package service;



import java.awt.image.BufferedImage;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;


public class BarcodeService {
	
	public  BufferedImage generateImage(String barcodeText) {
	    Code128Bean b = new Code128Bean();
	    BitmapCanvasProvider canvas = 
	      new BitmapCanvasProvider(400, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	 
	    b.generateBarcode(canvas, barcodeText);
	    return canvas.getBufferedImage();
	}

	public Image convertToFxImage(BufferedImage image) {
	    WritableImage wr = null;
	    if (image != null) {
	        wr = new WritableImage(image.getWidth(), image.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                pw.setArgb(x, y, image.getRGB(x, y));
	            }
	        }
	    }

	    return new ImageView(wr).getImage();
	}
}
