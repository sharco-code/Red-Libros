package controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import service.LoaderService;

public class ErrorController {
	
	 @FXML
	 private VBox xVBoxMAIN;
	 
	 private LoaderService loaderService;
	 
	 
	
	public ErrorController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}



	@FXML
    void ajustesCLICKED(MouseEvent event) {
    	
    	this.loaderService.loadAjustes();
    }
	
}
