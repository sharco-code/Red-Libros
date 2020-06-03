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
import service.LoaderService;
import view.Toast;


public class MainController implements Initializable {
	
	@FXML
    private BorderPane xBorderPaneMAIN;

    @FXML
    private VBox xVBoxLEFT;

    @FXML
    private VBox xVBoxCENTER;
    
    private Background background;
    
    private LoaderService loaderService;
    
    
    
    public MainController() {
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBackgroundMain();
		this.loaderService = new LoaderService(xVBoxCENTER);
		
		
	}
	
    @FXML
    void AjustesCLICKED(MouseEvent event) {
    	this.loaderService.loadAjustes();
    	this.xVBoxCENTER.setBackground(null);
    	
    }

    @FXML
    void CreditosCLICKED(MouseEvent event) {
    	this.loaderService.loadCreditos();
    	this.xVBoxCENTER.setBackground(null);
    }

    @FXML
    void DevolucionesCLICKED(MouseEvent event) {
    	this.loaderService.loadDevoluciones();
    	this.xVBoxCENTER.setBackground(null);
    }

    @FXML
    void EntregasCLICKED(MouseEvent event) {
    	this.loaderService.loadEntregas();
    	this.xVBoxCENTER.setBackground(null);
    	
    }

    @FXML
    void HistorialCLICKED(MouseEvent event) {
    	this.loaderService.loadHistorial();
    	this.xVBoxCENTER.setBackground(null);
    }

   
    
    @FXML
    void LibrosCLICKED(MouseEvent event) {
    	this.loaderService.loadListaLibros();
    	this.xVBoxCENTER.setBackground(null);
    }

    @FXML
    void NameCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
		this.xVBoxCENTER.setBackground(this.background);
		
    }

    @FXML
    void StockCLICKED(MouseEvent event) {
    	this.loaderService.loadStock();
    	this.xVBoxCENTER.setBackground(null);
    }
    
    private void setBackgroundMain() {
    	BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResourceAsStream("/images/main-background2.jpg")),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
				backgroundSize);
		this.background = new Background(backgroundImage);
		
		
		this.xVBoxCENTER.setBackground(background);
	}

	
	
	
    
}
