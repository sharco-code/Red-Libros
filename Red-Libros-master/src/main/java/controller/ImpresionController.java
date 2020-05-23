package controller;

import java.awt.Desktop;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import model.Options;
import pojo.Libro;
import service.BarcodeService;
import service.PdfService;
import service.SettingsService;
import view.Toast;

public class ImpresionController implements Initializable {
	@FXML
    private VBox xVBoxMAIN;

	@FXML
    private ChoiceBox<String> xChoiceBoxColumna;

    @FXML
    private ChoiceBox<String> xChoiceBoxFila;

    @FXML
    private CheckBox xCheckBoxPagina;

    @FXML
    private HBox xButtonImprimir;

    @FXML
    private HBox xButtonCancelar;

    
    private Libro libro;
    private String ruta;
    private String[] listaCodigos;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
    	Options options;
    	options = SettingsService.getOptions();
    	int columnas  = Integer.parseInt(options.getColumnas());
    	int filas = Integer.parseInt(options.getFilas());
    	
    	xChoiceBoxColumna.setDisable(true);
    	xChoiceBoxColumna.getItems().clear();
    	for (int i = 1; i <= columnas; i++) {
    		xChoiceBoxColumna.getItems().add(""+i);
		}
    	xChoiceBoxColumna.getSelectionModel().selectFirst();
    	
    	xChoiceBoxFila.setDisable(true);
    	xChoiceBoxFila.getItems().clear();
    	for (int i = 1; i <= filas; i++) {
    		xChoiceBoxFila.getItems().add(""+i);
		}
    	xChoiceBoxFila.getSelectionModel().selectFirst();
    	
    	xCheckBoxPagina.setOnAction(e ->{
    		cambiarEstado();
    	});
		
	}
    
    @FXML
    void CancelarCLICKED(MouseEvent event) {
    	volver();
    }

    @FXML
    void ImprimirCLICKED(MouseEvent event) {
    	seleccionarCarpeta();
    	imprimir();
    	volver();
    }

	private void seleccionarCarpeta() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(new File("src"));

		File selectedDirectory = directoryChooser.showDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		this.ruta = selectedDirectory.getAbsolutePath();
	}
    
    void cambiarEstado(){
    	xChoiceBoxColumna.setDisable(!xChoiceBoxColumna.isDisable());
    	xChoiceBoxFila.setDisable(!xChoiceBoxFila.isDisable());
    	
    }
    
    void imprimir() {
		if(listaCodigos.length <= 0) return;
		PdfService pdfService = new PdfService();
		BarcodeService barcodeService = new BarcodeService();

		List<com.itextpdf.text.Image> codigos = new ArrayList<>();
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			for (int i = 0; i < listaCodigos.length; i++) {
				ImageIO.write(barcodeService.generateImage(listaCodigos[i]), "png", baos);
				codigos.add(com.itextpdf.text.Image.getInstance(baos.toByteArray()));
				baos.reset();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if(this.xCheckBoxPagina.isSelected()) {
				pdfService.createPDF(codigos, ruta,0,0);
			}else {
				pdfService.createPDF(codigos, ruta,Integer.parseInt(this.xChoiceBoxColumna.getValue()),Integer.parseInt(this.xChoiceBoxFila.getValue()));
			}
			
			
			showToast("PDF generado corectamente");
			File pdfFile = new File(ruta + "\\barcodes.pdf");
			if (pdfFile.exists()) {

				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(pdfFile);
				} else {
					System.out.println("Awt Desktop is not supported!");
				}

			} else {
				System.out.println("File is not exists!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			showToast("No se ha podido generar el PDF");
		}
	}
    
    void  volver() {
    	Parent root = null;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

			LibroDetalleController libroDetalleController = new LibroDetalleController();
			loader.setController(libroDetalleController);
			
			root = loader.load();
			libroDetalleController.setLibro(this.libro);

			this.xVBoxMAIN.getChildren().clear();
			this.xVBoxMAIN.getChildren().add(root);

			libroDetalleController.disableComboBoxes();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    
    

	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; // 3.5 seconds
		int fadeInTime = 150; // 0.5 seconds
		int fadeOutTime = 300; // 0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
    
    private void showToastRED(String toastMsg) {
		int toastMsgTime = 1000; // 3.5 seconds
		int fadeInTime = 150; // 0.5 seconds
		int fadeOutTime = 300; // 0.5 seconds
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
    
    public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public String[] getListaCodigos() {
		return listaCodigos;
	}

	public void setListaCodigos(String[] listaCodigos) {
		this.listaCodigos = listaCodigos;
	}

	
    
    

}
