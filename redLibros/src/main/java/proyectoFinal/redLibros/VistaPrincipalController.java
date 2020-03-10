package proyectoFinal.redLibros;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class VistaPrincipalController implements Initializable {
	
	@FXML
	BorderPane borderPaneMain;
	
	@FXML
	public void librosButtonClicked(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("librosView.fxml"));
        Parent root = loader.load();
        
        borderPaneMain.setCenter(root);
        
        
	}
	
	@FXML
	public void entregasButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("entregasView.fxml"));
        Parent root = loader.load();
        
        borderPaneMain.setCenter(root);
	}
	
	@FXML
	public void devolucionesButtonClicked(ActionEvent event) {
		System.out.println("libros");
	}
	
	@FXML
	public void stockButtonClicked(ActionEvent event) {
		System.out.println("libros");
	}
	
	@FXML
	public void historialButtonClicked(ActionEvent event) {
		System.out.println("libros");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
