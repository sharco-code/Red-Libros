package app;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.itextpdf.text.Image;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import service.BarcodeService;
import service.PdfService;

public class prueba extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("JavaFX App");

	        DirectoryChooser directoryChooser = new DirectoryChooser();
	        directoryChooser.setInitialDirectory(new File("src"));

	        Button button = new Button("Select Directory");
	        button.setOnAction(e -> {
	            File selectedDirectory = directoryChooser.showDialog(primaryStage);
	            
	            ejecutar(selectedDirectory.getAbsolutePath());
	        });


	        VBox vBox = new VBox(button);
	        Scene scene = new Scene(vBox, 960, 600);

	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	

	private static void ejecutar(String ruta) {
		PdfService pdfService = new PdfService();
		BarcodeService barcodeService = new BarcodeService();
		
		List<Image> codigos = new ArrayList<>();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(barcodeService.generateImage("prueba"), "png", baos);
			for (int i = 0; i < 17; i++) {
				codigos.add(Image.getInstance(baos.toByteArray()));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		try {
			pdfService.createPDF(codigos,ruta);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
