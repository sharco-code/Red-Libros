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
    private AnchorPane librosAnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	loadUI("mainComponent");
    	this.xLabelTitle.setText("Inicio");
    	showSearch(false);
    	
    	this.librosController = new LibrosController();
    	
    }    

	@FXML
    private void LibrosCLICKED(MouseEvent event) {
		this.xLabelTitle.setText("Libros");
    	showSearch(true);
		try {
			SessionFactory factory = UtilesHibernate.getSessionFactory();
			Session session = factory.getCurrentSession();
			
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
	
	private void showErrorComponent() {
    	loadUI("errorComponent");
    	this.xLabelTitle.setText("Error");
    	showSearch(false);
    }

    @FXML
    private void EntregasCLICKED(MouseEvent event) {
    	loadUI("entregasComponent");
    	this.xLabelTitle.setText("Entegas");
    	showSearch(true);
    }

    @FXML
    private void DevolucionesCLICKED(MouseEvent event) {
    	loadUI("devolucionesComponent");
    	this.xLabelTitle.setText("Devoluciones");
    	showSearch(true);
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
