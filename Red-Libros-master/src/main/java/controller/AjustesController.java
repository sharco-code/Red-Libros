package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import app.Main;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Options;
import pojo.Libro;
import view.Toast;
import utiles.hibernate.UtilesHibernate;
import utiles.xml.ModifyXMLFile;
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

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.xChoiceBoxFilas.setItems(FXCollections.observableArrayList(filas));
		this.xChoiceBoxColumnas.setItems(FXCollections.observableArrayList(columnas));
		Options options;

		try {

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
		} finally {
			options = null;
		}

	}

	@FXML
	void ApplyCLICKED(MouseEvent event) {

		try {
			writeSettings();
			showToast("Cambios aplicados");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; // 3.5 seconds
		int fadeInTime = 150; // 0.5 seconds
		int fadeOutTime = 300; // 0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}

	private void writeSettings() throws FileNotFoundException, IOException, ParseException {
		SettingsService.writeSettings(new Options(this.xTextFieldIP.getText(), this.xTextFieldPuerto.getText(),
				this.xTextFieldUser.getText(), this.xTextFieldPassword.getText(),
				this.xChoiceBoxColumnas.getSelectionModel().getSelectedItem(),
				this.xChoiceBoxFilas.getSelectionModel().getSelectedItem()));

	}

	@FXML
	void ImportCLICKED(MouseEvent event) {
		Parent root = null;
		try {

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("/view/importacionComponent.fxml"));

			ImportacionController importacionController = new ImportacionController();
			loader.setController(importacionController);
			root = loader.load();
			xVBoxMAIN.getChildren().clear();
			xVBoxMAIN.getChildren().add(root);

		} catch (Exception e) {
			showErrorComponent();
			e.printStackTrace();
			
		}
	}

	private void showErrorComponent() {
    	this.xVBoxMAIN.getChildren().clear();
    	try {
    		ErrorController errorController = new ErrorController();
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/errorComponent.fxml"));
    		loader.setController(errorController);
    		VBox vbox = (VBox) loader.load();
    		
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxMAIN.getChildren().add(vbox);

		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
    

}
