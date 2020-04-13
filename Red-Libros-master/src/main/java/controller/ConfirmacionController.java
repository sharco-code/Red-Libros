package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import dao.LibroDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pojo.Libro;

public class ConfirmacionController {

	private Libro libro;
	
	@FXML
	private VBox xVBoxMAIN;
	
	@FXML
    private Text xLabelTitle;

    @FXML
    private Text xLabelTexto;

    @FXML
    private HBox xButtonOK;

    @FXML
    private Text xButtonBorrarTEXT;

    @FXML
    private HBox xButtonCANCEL;

    @FXML
    void CancelCLICKED(MouseEvent event) {
    	this.xVBoxMAIN.getChildren().clear();
    	Parent root = null;

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/libroDetalleComponent.fxml"));

			LibroDetalleController libroDetalleController = new LibroDetalleController();
			loader.setController(libroDetalleController);
			// root =
			// FXMLLoader.load(getClass().getResource("/view/libroDetalleComponent.fxml"));
			root = loader.load();
			libroDetalleController.setLibro(this.libro);

			this.xVBoxMAIN.getChildren().clear();
			this.xVBoxMAIN.getChildren().add(root);

			libroDetalleController.disableComboBoxes();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void OkCLICKED(MouseEvent event) {
    	try {
			LibroDAO.deleteLibro(this.libro);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    public void setLibro(Libro libro) {
    	this.libro = libro;
    }
}
