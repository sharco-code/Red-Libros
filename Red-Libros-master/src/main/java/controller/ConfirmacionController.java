package controller;

import dao.LibroDAO;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pojo.Libro;
import service.LoaderService;

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
	
	private LoaderService loaderService;
	
	
	

	public ConfirmacionController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}

	@FXML
	void CancelCLICKED(MouseEvent event) {
		

		this.loaderService.loadLibroDetalle(this.libro);
	}

	@FXML
	void OkCLICKED(MouseEvent event) {
		libroDAO.delete(this.libro);

		this.loaderService.loadToast("Libro borrado");

		
		this.loaderService.loadListaLibros();
	}





	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}
