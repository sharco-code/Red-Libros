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
			//importService.ImportarContenido(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Alumnos importados correctamente");
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
			importService.ImportarContenido(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Contenido importado correctamente");
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
			importService.ImportarCurso(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Cursos importados correctamente");
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
			importService.ImportarGrupo(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Grupos importados correctamente");
    }

    @FXML
    void ImportLibrosCLICKED(MouseEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Selecciona xml a importar");
    	
    	File selectedDirectory = fileChooser.showOpenDialog(Main.getStage());
		if (selectedDirectory == null) {
			return;
		}
		try {
			//importService.ImportarLibro(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Libros importadas correctamente");
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
			//importService.ImportarGrupo(selectedDirectory.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
			showToastRED("Error en la importacion");
		}
		showToast("Matriculas importadas correctamente");
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
		int toastMsgTime = 1000; //3.5 seconds
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
