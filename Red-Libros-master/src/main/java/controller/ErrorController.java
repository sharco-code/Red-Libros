package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ErrorController {

	 @FXML
	private AnchorPane AnchorPane;
	
	@FXML
    void ajustesCLICKED(MouseEvent event) {
		System.out.println("ajustes clicked");
		Parent root = null;
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajustesComponent.fxml"));
    		
    		//LibroDetalleController libroDetalleController = new LibroDetalleController();
			//loader.setController(libroDetalleController);
			//root = FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
			root = loader.load();
			
			AnchorPane.getChildren().clear();
			AnchorPane.getChildren().setAll(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
	
}
