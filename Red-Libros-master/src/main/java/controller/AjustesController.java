package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javax.persistence.Query;
import org.json.simple.parser.ParseException;
import app.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Options;
import view.Toast;
import service.LoaderService;
import service.SettingsService;

public class AjustesController implements Initializable {

	
	@FXML
	private VBox xVBoxMAIN;
	
	@FXML
	private TextField xTextFieldIP;

	@FXML
	private TextField xTextFieldPuerto;

	@FXML
	private TextField xTextFieldUser;

	@FXML
	private TextField xTextFieldPassword;

	@FXML
	private ChoiceBox<String> xChoiceBoxFilas;

	@FXML
	private ChoiceBox<String> xChoiceBoxColumnas;

	@FXML
	private AnchorPane anchorpane;

	private final String filas[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private final String columnas[] = { "1", "2", "3", "4" };
	
	private LoaderService loaderService;
	

	public AjustesController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}



	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setFields();
	}

	

	@FXML
	void ApplyCLICKED(MouseEvent event) {

		try {
			writeSettings();
			this.loaderService.loadToast("Cambios aplicados");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	private void writeSettings() throws  IOException, ParseException {
		SettingsService.writeSettings(new Options(this.xTextFieldIP.getText(), this.xTextFieldPuerto.getText(),
				this.xTextFieldUser.getText(), this.xTextFieldPassword.getText(),
				this.xChoiceBoxColumnas.getSelectionModel().getSelectedItem(),
				this.xChoiceBoxFilas.getSelectionModel().getSelectedItem()));

	}

	@FXML
	void ImportCLICKED(MouseEvent event) {
		
		try {
			this.loaderService.loadImportacion();
		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
	}
	
	private void setFields() {
		try {
			Options options;
			this.xChoiceBoxFilas.setItems(FXCollections.observableArrayList(filas));
			this.xChoiceBoxColumnas.setItems(FXCollections.observableArrayList(columnas));
			

			options = SettingsService.getOptions();

			this.xTextFieldIP.setText(options.getIp());
			this.xTextFieldPuerto.setText(options.getPort());
			this.xTextFieldUser.setText(options.getUser());
			this.xTextFieldPassword.setText(options.getPassword());

			if (options.getColumnas() != null) {
				this.xChoiceBoxColumnas.getSelectionModel()
						.select(this.xChoiceBoxColumnas.getItems().indexOf(options.getColumnas()));
			}
			if (options.getFilas() != null) {
				this.xChoiceBoxFilas.getSelectionModel()
						.select(this.xChoiceBoxFilas.getItems().indexOf(options.getFilas()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showErrorComponent() {
    	this.loaderService.loadShowError();
    }
    

}
