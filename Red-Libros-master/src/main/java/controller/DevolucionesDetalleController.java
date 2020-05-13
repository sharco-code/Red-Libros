package controller;
import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import pojo.Alumno;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;
public class DevolucionesDetalleController implements Initializable {
	@FXML
    private TextField xTextFieldNombre;

    @FXML
    private TextField xTextFieldNIA;

    @FXML
    private TextField xTextFieldExpediente;

    @FXML
    private TableView<?> xTableViewHistorial;

    @FXML
    private TextField xTextFieldCodigoEjemplar;

    @FXML
    private FontAwesomeIcon xButtonScan;

    @FXML
    private TreeView<?> xTreeViewLibros;

    @FXML
    private HBox xButtonDevolver;

    
    private Alumno alumno;

    @FXML
    void DevolverCLICKED(MouseEvent event) {

    }

    @FXML
    void ScanCLICKED(MouseEvent event) {

    }
    

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		reload();
	}
    
	
	private void reload(){
		this.xTextFieldExpediente.setText(this.alumno.getExpediente());
		this.xTextFieldNIA.setText(this.alumno.getNia());
		this.xTextFieldNombre.setText(this.alumno.getNombre()+" "+this.alumno.getApellido1()+" "+this.alumno.getApellido2());
		setTreeView();
	}
	
	private void setTreeView(){
		TreeItem root = new TreeItem();
		for(Matricula matricula: this.alumno.getMatriculas()) {
			TreeItem libros = new TreeItem<String>();
			libros.setValue(matricula.getContenidoBean().getNombreCas());
			for(Libro libro: matricula.getContenidoBean().getLibros()) {
				libros.getChildren().add(new TreeItem<Libro>(libro));
			}
			root.getChildren().add(libros);
		}
		xTreeViewLibros.setRoot(root);
		xTreeViewLibros.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
			// Body would go here
			if(newValue.getValue() instanceof Libro) {
				System.out.println(((Libro)newValue.getValue()).getCodigo());
			}
			

		});
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.xTreeViewLibros.setShowRoot(false);
		
	}
	
		
    
    
}
