package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;


public class MainController implements Initializable {

	@FXML
    private BorderPane borderpane;
	
	@FXML
	private Text xLabelTitle;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadUI("librosView");
    	this.xLabelTitle.setText("Libros");
    }    

    @FXML
    private void LibrosCLICKED(MouseEvent event) {
    	loadUI("librosView");
    	this.xLabelTitle.setText("Libros");
    }

    @FXML
    private void EntregasCLICKED(MouseEvent event) {
    	loadUI("entregasView");
    	this.xLabelTitle.setText("Entegas");
    }

    @FXML
    private void DevolucionesCLICKED(MouseEvent event) {
    	loadUI("devolucionesView");
    	this.xLabelTitle.setText("Devoluciones");
    }

    @FXML
    private void HistorialCLICKED(MouseEvent event) {
    	loadUI("historialView");
    	this.xLabelTitle.setText("Historial");
    }

    @FXML
    private void StockCLICKED(MouseEvent event) {
    	loadUI("stockView");
    	this.xLabelTitle.setText("Stock");
    }

    @FXML
    private void AjustesCLICKED(MouseEvent event) {
    	loadUI("ajustesView");
    	this.xLabelTitle.setText("Ajustes");
    }

    @FXML
    private void CreditosCLICKED(MouseEvent event) {
    	loadUI("creditosView");
    	this.xLabelTitle.setText("Creditos");
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
