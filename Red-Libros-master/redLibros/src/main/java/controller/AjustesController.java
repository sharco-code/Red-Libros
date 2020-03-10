package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;



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
    
    private final String filas[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final String columnas[] = { "1", "2", "3", "4"};
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	this.xChoiceBoxFilas.setItems(FXCollections.observableArrayList(filas));
    	this.xChoiceBoxColumnas.setItems(FXCollections.observableArrayList(columnas));
    	
    	try {
    		//comprobar si exsite json, si exsite poner los datos en los textfield
    		
        	String json_url = System.getProperty("user.dir") + "\\config\\settings.json";
	    	 Object object = new JSONParser().parse(new FileReader(json_url));
	         JSONObject jo = (JSONObject) object;
	         JSONArray ja = (JSONArray) jo.get("Settings");
	         JSONObject jsonObj = (JSONObject) ja.get(0);
	         
	         String ip = (String) jsonObj.get("ip");
	         String port = (String) jsonObj.get("port");
	         
	         String user = (String) jsonObj.get("user");
	         String password = (String) jsonObj.get("password");
	         
	         this.xTextFieldIP.setText(ip);
	         this.xTextFieldPuerto.setText(port);
	         

	         this.xTextFieldUser.setText(user);
	         this.xTextFieldPassword.setText(password);
	         
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
    	
    }  
    
    @FXML
    void ApplyCLICKED(MouseEvent event) {

    }

    @FXML
    void ImportCLICKED(MouseEvent event) {

    }

}
