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
import service.LoaderService;
import view.Toast;

public class ImportacionController {
	@FXML
    private VBox xVBoxCENTER;

    @FXML
    private Text xLabelTitle;

    ImportService importService = new ImportService();
    
    private LoaderService loaderService;
    
    
    
    public ImportacionController(LoaderService loaderService) {
		super();
		this.loaderService = loaderService;
	}

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
			this.loaderService.loadToast("Alumnos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			this.loaderService.loadToastRED(e.getMessage(),4500);
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
			this.loaderService.loadToast("Contenido importado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			this.loaderService.loadToastRED(e.getMessage(),4500);
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
			this.loaderService.loadToast("Cursos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			this.loaderService.loadToastRED(e.getMessage(),4500);
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
			this.loaderService.loadToast("Grupos importados correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			this.loaderService.loadToastRED(e.getMessage(),4500);
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
			this.loaderService.loadToast("Matriculas importadas correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			this.loaderService.loadToastRED(e.getMessage(),4500);
		}
    }

    @FXML
    void VolverCLICKED(MouseEvent event) {
    	this.loaderService.loadAjustes();
    }
    
    
	
}
