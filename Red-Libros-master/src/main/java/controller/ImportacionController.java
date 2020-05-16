package controller;

import java.io.File;
import java.io.IOException;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import service.ImportService;
import view.Toast;

public class ImportacionController {
	@FXML
    private VBox xVBoxCENTER;

    @FXML
    private Text xLabelTitle;

    ImportService importService = new ImportService();
    
    @FXML
    void ImportAlumnosCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		
		try {
			importService.importarAlumno(selectedDirectory.getAbsolutePath());
			showToast("Alumnos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED(e.getMessage());
			return;
		}
		
		
    }

    @FXML
    void ImportContenidoCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		try {
			importService.importarContenido(selectedDirectory.getAbsolutePath());
			showToast("Contenido importado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED(e.getMessage());
			return;
		}
    }

    @FXML
    void ImportCursosCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		try {
			importService.importarCurso(selectedDirectory.getAbsolutePath());
			showToast("Cursos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED(e.getMessage());
			return;
		}
    }

    @FXML
    void ImportGruposCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		try {
			importService.importarGrupo(selectedDirectory.getAbsolutePath());
			showToast("Grupos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED(e.getMessage());
			return;
		}
    }

   

    @FXML
    void ImportMatriculasCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		try {
			importService.importarMatricula(selectedDirectory.getAbsolutePath());
			showToast("Matriculas importadas correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED(e.getMessage());
			return;
		}
    }

    @FXML
    void VolverCLICKED(MouseEvent event) {
    	this.xVBoxCENTER.getChildren().clear();
    	this.xVBoxCENTER.setPadding(new Insets(0, 0, 0, 0));
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ajustesComponent.fxml"));
    		VBox vbox = (VBox) loader.load();
    		
    		VBox.setVgrow(vbox, Priority.ALWAYS);
			
			this.xVBoxCENTER.getChildren().add(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void showToastRED(String toastMsg) {
		int toastMsgTime = 4500; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeTextRED(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
	private void showToast(String toastMsg) {
		int toastMsgTime = 1000; //3.5 seconds
		int fadeInTime = 150; //0.5 seconds
		int fadeOutTime= 300; //0.5 seconds
		Toast.makeText(Main.getStage(), toastMsg, toastMsgTime, fadeInTime, fadeOutTime);
	}
	
}
