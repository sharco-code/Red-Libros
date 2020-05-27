package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
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
    
    private StockController stockController;
    
    private HistorialController historialController;
    
    private Background background;
    
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.errorController = new ErrorController();
		setBackgroundMain();
		
	}
	
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
    		this.devolucionesController = new DevolucionesController();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/devolucionesComponent.fxml"));
    		loader.setController(this.devolucionesController);
    		VBox vbox = (VBox) loader.load();
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
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
    	try {
    		this.entregasController = new EntregasController();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/entregasComponent.fxml"));
    		loader.setController(this.entregasController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
			this.xVBoxCENTER.getChildren().add(vbox);
			
			this.entregasController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
    	
    }

    @FXML
    void HistorialCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	try {
    		this.historialController = new HistorialController();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historialComponent.fxml"));
    		loader.setController(this.historialController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
			this.xVBoxCENTER.getChildren().add(vbox);
			
			this.historialController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
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

    	try {
    		this.librosController = new LibrosController();
        	
        	this.xVBoxCENTER.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
    		loader.setController(this.librosController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
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
    	
		this.xVBoxCENTER.setBackground(this.background);
		
    }

    @FXML
    void StockCLICKED(MouseEvent event) {
    	try {
    		this.stockController = new StockController();
        	
        	this.xVBoxCENTER.getChildren().clear();
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stockComponent.fxml"));
    		loader.setController(this.stockController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		vbox.getStylesheets().add(getClass().getResource("/style/table_style.css").toExternalForm());
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);

			this.stockController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}

    }
    
    private void setBackgroundMain() {
    	BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/main-background2.jpg")),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		this.background = new Background(backgroundImage);
		
		
		this.xVBoxCENTER.setBackground(background);
	}

	
	
	private void showToastRED(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
    
}
