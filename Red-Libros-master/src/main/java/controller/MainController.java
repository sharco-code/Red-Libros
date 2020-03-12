package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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


public class MainController implements Initializable {
	
	@FXML
    private BorderPane borderpane;

    @FXML
    private Text xLabelTitle;

    @FXML
    private HBox xHBoxSearch;

    @FXML
    private TextField xTextFieldSearch;
	
    public LibrosController librosController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	//LibrosController librosController = new LibrosController(borderpane);
    	
    	loadUI("librosComponent");
    	this.xLabelTitle.setText("Libros");
    	showSearch(true);
    	
    	
    }    

	@FXML
    private void LibrosCLICKED(MouseEvent event) {
    	loadUI("librosComponent");
    	this.xLabelTitle.setText("Libros");
    	showSearch(true);
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
    	/*
    	FXMLLoader root = null;
    	try {
			root = FXMLLoader.load(getClass().getResource("/view/"+ui+".fxml"));
			
			LibrosController l = new LibrosController("aaa");
			root.setController(l);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	borderpane.setCenter(root);
    	*/
    	Parent root = null;
    	try {
			root = FXMLLoader.load(getClass().getResource("/view/"+ui+".fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	borderpane.setCenter(root);
    }
    
    
}
