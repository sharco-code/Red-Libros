package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utiles.hibernate.UtilesHibernate;


public class MainController implements Initializable {
	
	@FXML
    private BorderPane borderpane;

    @FXML
    private Text xLabelTitle;

    @FXML
    private HBox xHBoxSearch;

    @FXML
    private TextField xTextFieldSearch;
    
    private LibrosController librosController;
    private EntregasController entregasController;
    private DevolucionesController devolucionesController;
    
    private AnchorPane librosAnchorPane;
    private AnchorPane entregasAnchorPane;
    private AnchorPane devolucionesAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	loadUI("mainComponent");
    	this.xLabelTitle.setText("Inicio");
    	showSearch(false);
    	
    	this.librosController = new LibrosController();
    	this.entregasController = new EntregasController();
    	this.devolucionesController = new DevolucionesController();
    	
    }    

	@FXML
    private void LibrosCLICKED(MouseEvent event) {
		this.xLabelTitle.setText("Libros");
    	showSearch(true);
		try {
			//SessionFactory factory = UtilesHibernate.getSessionFactory();
			//Session session = factory.getCurrentSession();
			
			FXMLLoader librosloader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
        	librosloader.setController(librosController);
        	this.librosAnchorPane = (AnchorPane) librosloader.load();
			
        	librosController.reload();
			Parent root = librosAnchorPane;
        	borderpane.setCenter(root);
        	xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        	    if(newValue != null) {
        	    	librosController.filtrar(newValue);
        	    }
        	});
        	
        	
		}  catch (Exception e) {
			showErrorComponent();
		}
    	
    	
    }
	@FXML
    void NameCLICKED(MouseEvent event) {
		loadUI("mainComponent");
    	this.xLabelTitle.setText("Inicio");
    	showSearch(false);
    }
	private void showErrorComponent() {
    	loadUI("errorComponent");
    	this.xLabelTitle.setText("Error");
    	showSearch(false);
    }

    @FXML
    private void EntregasCLICKED(MouseEvent event) {
    	/*
    	this.xLabelTitle.setText("Entregas");
    	showSearch(true);
		try {
			
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			Session session = factory.getCurrentSession();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/entregasComponent.fxml"));
        	loader.setController(entregasController);
        	this.entregasAnchorPane = (AnchorPane) loader.load();
			
        	entregasController.reload();
			Parent root = entregasAnchorPane;
        	borderpane.setCenter(root);
        	xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        	    if(newValue != null) {
        	    	entregasController.filtrar(newValue);
        	    }
        	});
        	
        	
		}  catch (Exception e) {
			showErrorComponent();
		}
		*/
    }

    @FXML
    private void DevolucionesCLICKED(MouseEvent event) {
    	this.xLabelTitle.setText("Devoluciones");
    	showSearch(true);
		try {
			//SessionFactory factory = UtilesHibernate.getSessionFactory();
			//Session session = factory.getCurrentSession();
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/devolucionesComponent.fxml"));
        	loader.setController(devolucionesController);
        	this.devolucionesAnchorPane = (AnchorPane) loader.load();
			
        	devolucionesController.reload();
			Parent root = devolucionesAnchorPane;
        	borderpane.setCenter(root);
        	xTextFieldSearch.setPromptText("Nombre");
        	xTextFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        	    if(newValue != null) {
        	    	devolucionesController.filtrar(newValue);
        	    }
        	});
        	
        	
		}  catch (Exception e) {
			showErrorComponent();
		}
    }

    @FXML
    private void HistorialCLICKED(MouseEvent event) {
    	loadUI("historialComponent");
    	this.xLabelTitle.setText("Historial");
    	showSearch(true);
    }

    @FXML
    private void StockCLICKED(MouseEvent event) {
    	loadUI("stockComponent");
    	this.xLabelTitle.setText("Stock");
    	
    }

    @FXML
    private void AjustesCLICKED(MouseEvent event) {
    	loadUI("ajustesComponent");
    	this.xLabelTitle.setText("Ajustes");
    	showSearch(false);
    }

    @FXML
    private void CreditosCLICKED(MouseEvent event) {
    	loadUI("creditosComponent");
    	this.xLabelTitle.setText("Creditos");
    	showSearch(false);
    }
    
    private void showSearch(boolean x) {
    	this.xHBoxSearch.setDisable(!x);
    	this.xHBoxSearch.setVisible(x);
    }
    

    private void loadUI(String ui) {
    	Parent root = null;
    	try {
			root = FXMLLoader.load(getClass().getResource("/view/"+ui+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	borderpane.setCenter(root);
    }

    
    
    
    
}
