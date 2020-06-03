package service;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.Main;
import controller.AjustesController;
import controller.ConfirmacionController;
import controller.DevolucionesController;
import controller.DevolucionesDetalleController;
import controller.EntregasController;
import controller.EntregasDetalleController;
import controller.ErrorController;
import controller.HistorialController;
import controller.ImportacionController;
import controller.ImpresionController;
import controller.LibroDetalleController;
import controller.LibrosController;
import controller.StockController;
import dao.AlumnoDAO;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pojo.Alumno;
import pojo.Libro;
import view.Toast;

public class LoaderService {
	private static final Logger logger = Logger.getLogger(LoaderService.class.getName());
	private VBox xVBoxMAIN;
	
	
	
	public LoaderService(VBox xVBoxMAIN) {
		super();
		this.xVBoxMAIN = xVBoxMAIN;
	}



	public void loadImportacion(){
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/importacionComponent.fxml"));

			ImportacionController importacionController = new ImportacionController(this);
			loader.setController(importacionController);
			VBox vbox = (VBox) loader.load();
			loadPage(vbox);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadImportacion failed", e);
		}
		
	}
	
	public void loadShowError() {
		
    	try {
    		ErrorController errorController = new ErrorController(this);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/errorComponent.fxml"));
    		loader.setController(errorController);
    		
    		VBox vbox = (VBox) loader.load();
    		
    		loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadShowError failed", e);
			
		}
	}

	
	
	public void loadAjustes() {
    	try {
    		AjustesController ajustesController = new AjustesController(this);
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajustesComponent.fxml"));
    		loader.setController(ajustesController);
    		VBox vbox = (VBox) loader.load();
    		loadPage(vbox);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadAjustes failed", e);
		}
	}
	
	public void loadListaLibros() {
		try {
			LibrosController librosController = new LibrosController(this);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
			loader.setController(librosController);
			VBox vbox = (VBox) loader.load();

			vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
			loadPage(vbox);

			librosController.reload();
		} catch (Exception e) {
			this.loadShowError();
			logger.log(Level.SEVERE, "loadListaLibros failed", e);
		}
	}
	
	public void loadLibroDetalle(Libro libro) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

			LibroDetalleController libroDetalleController = new LibroDetalleController(this);
			loader.setController(libroDetalleController);
			VBox vbox = (VBox) loader.load();
			vbox.getStylesheets().add(getClass().getResource("/style/table_style_small.css").toExternalForm());
			libroDetalleController.setLibro(libro);

			loadPage(vbox);

			libroDetalleController.disableComboBoxes();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadLibroDetalle failed", e);
		}
		
	}
	
	
	public void loadNewLibroDetalle() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

			LibroDetalleController libroDetalleController = new LibroDetalleController(this);
			loader.setController(libroDetalleController);
			VBox vbox = (VBox) loader.load();
			libroDetalleController.setNuevoLibro();
			
			vbox.getStylesheets().add(getClass().getResource("/style/table_style_small.css").toExternalForm());
			
			loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadNewLibroDetalle failed", e);
		}
		
	}
	
	public void loadDevolucionDetalle(Alumno alumno) {
		
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/devolucionesDetalleComponent.fxml"));

			DevolucionesDetalleController devolucionesDetalleController = new DevolucionesDetalleController(this);
			loader.setController(devolucionesDetalleController);
			VBox vbox = (VBox) loader.load();
			devolucionesDetalleController.setAlumno(alumno);
			vbox.getStylesheets().add(getClass().getResource("/style/treeview_style.css").toExternalForm());
			
			loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadDevolucionDetalle failed", e);
		}
		
	}
	
	public void loadEntregaDetalles(Alumno alumno) {
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/entregasDetalleComponent.fxml"));

			EntregasDetalleController entregasDetalleController = new EntregasDetalleController(this);
			loader.setController(entregasDetalleController);
			VBox vbox = (VBox) loader.load();
			entregasDetalleController.setAlumno(alumno);
			
			vbox.getStylesheets().add(getClass().getResource("/style/table_style_small.css").toExternalForm());
			vbox.getStylesheets().add(getClass().getResource("/style/treeview_style.css").toExternalForm());

			loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadEntregaDetalles failed", e);
		}
	}
	
	public void loadConfirmacion(Libro libro) {
		try {
			ConfirmacionController confirmacionController = new ConfirmacionController(this);
			confirmacionController.setLibro(libro);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/confirmacionComponent.fxml"));
			loader.setController(confirmacionController);
			VBox vbox = (VBox) loader.load();

			loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadConfirmacion failed", e);
		}
	}
	
	public void loadImpresion(Libro libro,String[] listaCodigos) {
		try {
			ImpresionController impresionController = new ImpresionController(this);
			impresionController.setLibro(libro);
			impresionController.setListaCodigos(listaCodigos);
			impresionController.setxTextLibrosSeleccionados(listaCodigos.length);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/impresionComponent.fxml"));
			loader.setController(impresionController);
			VBox vbox = (VBox) loader.load();
			
			loadPage(vbox);

		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadImpresion failed", e);
		}
	}
	
	public void loadCreditos() {
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/creditosComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		loadPage(vbox);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "loadCreditos failed", e);
		}
	}
	
	public void loadDevoluciones() {
		try {
    		DevolucionesController devolucionesController = new DevolucionesController(this);
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/devolucionesComponent.fxml"));
    		loader.setController(devolucionesController);
    		VBox vbox = (VBox) loader.load();
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
    	
			
    		loadPage(vbox);
    		
    		devolucionesController.reload();
		} catch (Exception e) {
			this.loadShowError();
			logger.log(Level.SEVERE, "loadDevoluciones failed", e);
			
		}
	}
	
	public void loadEntregas() {
		try {
    		EntregasController entregasController = new EntregasController(this);
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/entregasComponent.fxml"));
    		loader.setController(entregasController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
			
    		loadPage(vbox);
    		
			entregasController.reload();
			
			
		} catch (Exception e) {
			this.loadShowError();
			logger.log(Level.SEVERE, "loadEntregas failed", e);
		}
	}
	
	public void loadHistorial() {
		
    	try {
    		HistorialController historialController = new HistorialController();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historialComponent.fxml"));
    		loader.setController(historialController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
    		
    		loadPage(vbox);
			
			historialController.reload();
		} catch (Exception e) {
			this.loadShowError();
			logger.log(Level.SEVERE, "loadHistorial failed", e);
			
		}
	}
	
	public void loadStock() {
		try {
			StockController stockController = new StockController();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stockComponent.fxml"));
    		loader.setController(stockController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
    		
    		loadPage(vbox);

			stockController.reload();
		} catch (Exception e) {
			this.loadShowError();
			logger.log(Level.SEVERE, "loadStock failed", e);
			
		}
	}
	
	private void loadPage(VBox vbox) throws Exception{
		this.xVBoxMAIN.getChildren().clear();
		VBox.setVgrow(vbox, Priority.ALWAYS);
		this.xVBoxMAIN.getChildren().add(vbox);
	}
	
	public void loadToast(String toastMsg) {
		int toastMsgTime = 1000;
		int fadeInTime = 150; 
		int fadeOutTime= 300; 
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	public void loadToast(String toastMsg,int duracion) {
		int toastMsgTime = duracion;
		int fadeInTime = 150;
		int fadeOutTime= 300;
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	
	public void loadToastRED(String toastMsg) {
		int toastMsgTime = 1000; 
		int fadeInTime = 150; 
		int fadeOutTime= 300;
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	public void loadToastRED(String toastMsg,int duracion) {
		int toastMsgTime = duracion;
		int fadeInTime = 150;
		int fadeOutTime= 300;
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	
	
	

}
