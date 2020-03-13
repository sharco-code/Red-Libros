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

import app.Init;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import pojo.Libro;
import view.Toast;
import utiles.hibernate.UtilesHibernate;
import utiles.xml.ModifyXMLFile;

public class AjustesController implements Initializable {

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

	private final String filas[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	private final String columnas[] = { "1", "2", "3", "4" };

	private final String JSON_URL = System.getProperty("user.dir") + "\\config\\settings.json";
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		this.xChoiceBoxFilas.setItems(FXCollections.observableArrayList(filas));
		this.xChoiceBoxColumnas.setItems(FXCollections.observableArrayList(columnas));

		try {

			Object object = new JSONParser().parse(new FileReader(JSON_URL));
			JSONObject jo = (JSONObject) object;

			String ip = (String) jo.get("ip");
			String port = (String) jo.get("port");
			String user = (String) jo.get("user");
			String password = (String) jo.get("password");
			
			this.xTextFieldIP.setText(ip);
			this.xTextFieldPuerto.setText(port);
			this.xTextFieldUser.setText(user);
			this.xTextFieldPassword.setText(password);
			
			if(jo.get("columnas")!=null) {
				String columnas = (String) jo.get("columnas");
				this.xChoiceBoxColumnas.getSelectionModel().select(this.xChoiceBoxColumnas.getItems().indexOf(columnas));
			}
			if(jo.get("filas")!=null) {
				String filas = (String) jo.get("filas");
				this.xChoiceBoxFilas.getSelectionModel().select(this.xChoiceBoxFilas.getItems().indexOf(filas));
			}
			
		} catch (FileNotFoundException e) {
			try {
				
				createFile();
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createFile() throws IOException {
		FileWriter w = new FileWriter(JSON_URL);
		w.write("{}");
		w.close();
	}

	@FXML
	void ApplyCLICKED(MouseEvent event) {
		try {
			
			writeSettings();
			updateHibernateCfg();
			
		} catch (FileNotFoundException e) {
			try {
				
				createFile();
				writeSettings();
				
				updateHibernateCfg();
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		
		
		showToast("Cambios aplicados");
		
		
		
	}

	private void updateHibernateCfg() throws IOException, ParseException, FileNotFoundException {
		Object object = new JSONParser().parse(new FileReader(JSON_URL));
		JSONObject jo = (JSONObject) object;

		String ip = (String) jo.get("ip");
		String port = (String) jo.get("port");
		String user = (String) jo.get("user");
		String password = (String) jo.get("password");
	}

	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeText(Init.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}

	private void writeSettings() throws IOException, ParseException, FileNotFoundException {
		Object object = new JSONParser().parse(new FileReader(new File(JSON_URL)));
		JSONObject jo = (JSONObject) object;

		jo.put("ip", this.xTextFieldIP.getText());
		jo.put("port", this.xTextFieldPuerto.getText());
		jo.put("user", this.xTextFieldUser.getText());
		jo.put("password", this.xTextFieldPassword.getText());
		jo.put("columnas", this.xChoiceBoxColumnas.getSelectionModel().getSelectedItem());
		jo.put("filas", this.xChoiceBoxFilas.getSelectionModel().getSelectedItem());
		
		PrintWriter pw = new PrintWriter(JSON_URL);
		pw.write(jo.toJSONString());
		pw.flush();
		pw.close();
	}

	@FXML
	void ImportCLICKED(MouseEvent event) {

	}

}
