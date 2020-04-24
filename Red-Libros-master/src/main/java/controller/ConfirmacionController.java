package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import app.Main;
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
import view.Toast;

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

	private LibroDAO libroDAO = new LibroDAO();

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
		System.out.println(this.libro.getId());
		libroDAO.delete(this.libro);

		showToast("Libro borrado");

		this.xVBoxMAIN.getChildren().clear();
		try {
			LibrosController librosController = new LibrosController();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/librosComponent.fxml"));
			loader.setController(librosController);
			VBox vbox = (VBox) loader.load();

			VBox.setVgrow(vbox, Priority.ALWAYS);

			this.xVBoxMAIN.getChildren().add(vbox);

			librosController.reload();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();

		}
	}

	private void showErrorComponent() {
		this.xVBoxMAIN.getChildren().clear();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/errorComponent.fxml"));
			loader.setController(new ErrorController());
			VBox vbox = (VBox) loader.load();

			VBox.setVgrow(vbox, Priority.ALWAYS);

			this.xVBoxMAIN.getChildren().add(vbox);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; // 3.5 seconds
		int fadeInTime = 150; // 0.5 seconds
		int fadeOutTime = 300; // 0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}
