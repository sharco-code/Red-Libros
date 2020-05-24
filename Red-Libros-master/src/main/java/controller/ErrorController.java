package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ErrorController {
	
	 @FXML
	 private VBox xVBoxMAIN;
	
	@FXML
    void ajustesCLICKED(MouseEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajustesComponent.fxml"));
    		VBox anchorPane = (VBox) loader.load();
    		
    		VBox.setVgrow(anchorPane, Priority.ALWAYS);
			
    		this.xVBoxMAIN.getChildren().clear();
    		this.xVBoxMAIN.setPadding(new Insets(0, 0, 0, 0));
			this.xVBoxMAIN.getChildren().add(anchorPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
}
