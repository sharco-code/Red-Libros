package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utiles.hibernate.UtilesHibernate;
import view.Toast;


public class MainController implements Initializable {
	
	@FXML
    private BorderPane xBorderPaneMAIN;

    @FXML
    private VBox xVBoxLEFT;

    @FXML
    private VBox xVBoxCENTER;

    private LibrosController librosController;
    private EntregasController entregasController;
    private DevolucionesController devolucionesController;
    private ErrorController errorController;
    
    @FXML
    void AjustesCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	this.xVBoxCENTER.setPadding(new Insets(0, 0, 0, 0));
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajustesComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void CreditosCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/creditosComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void DevolucionesCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	try {

    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/devolucionesComponent.fxml"));
    		loader.setController(this.devolucionesController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
			
			this.devolucionesController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
    }

    @FXML
    void EntregasCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    }

    @FXML
    void HistorialCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    }

    private void showErrorComponent() {
    	this.xVBoxCENTER.getChildren().clear();
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/errorComponent.fxml"));
    		loader.setController(this.errorController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
    
    @FXML
    void LibrosCLICKED(MouseEvent event) {
    	
    	/*
    	Estaría guay que esto salier mientras carga
    	showToast("Conectando a BBDD");
    	 */
    	
    	
    	this.xVBoxCENTER.getChildren().clear();
    	try {

    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
    		loader.setController(this.librosController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);

			this.librosController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
    }

    @FXML
    void NameCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void StockCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		this.librosController = new LibrosController();
    	this.entregasController = new EntregasController();
    	this.devolucionesController = new DevolucionesController();
		this.errorController = new ErrorController();
		
		try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
    
}
