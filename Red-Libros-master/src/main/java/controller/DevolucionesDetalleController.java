package controller;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import dao.AlumnoDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;
import service.DevolucionesService;
import view.Toast;
public class DevolucionesDetalleController implements Initializable {
	
	@FXML
    private VBox xVBoxMAIN;
	
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
    private TreeView<Ejemplare> xTreeViewLibros;

    @FXML
    private HBox xButtonDevolver;

    @FXML
    private ComboBox<String> xComboBoxEstado;
    
    private Alumno alumno;
    
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    
    private Ejemplare selectedEjemplar;
    
    private DevolucionesService devolucionesService;
    
    public DevolucionesDetalleController(){
    	this.devolucionesService = new DevolucionesService();
    }
    

    @FXML
    void DevolverCLICKED(MouseEvent event) {
    	if(this.selectedEjemplar == null) {
    		showToastRED("Selecciona un libro para devolver");
			return;
		}
    	if(this.xComboBoxEstado.getSelectionModel().getSelectedItem() == null) {
    		showToastRED("Debes seleccionar el estado del libro");
			return;
    	}
    	
		try {
			int estado = 0;
			
			switch (this.xComboBoxEstado.getSelectionModel().getSelectedItem()) {
			case "Perfecto":
				estado = 0;
				break;
			case "Regular":
				estado = 1;
				break;
			case "Mal":
				estado = 2;
				break;
			}
			
			this.devolucionesService.devolverLibro(this.selectedEjemplar,this.alumno, estado);
			
			reload();
			showToast("Libro devuelto correctamente");
		} catch (Exception e) {
			// TODO: handle exception
			showToastRED(e.getMessage());
		}
		
    }

    @FXML
    void ScanCLICKED(MouseEvent event) {
    	if(this.xTextFieldCodigoEjemplar.getText().equals("")) {
			return;
		}
		try {
			Ejemplare ejemplar = this.devolucionesService.scanEjemplar(this.xTextFieldCodigoEjemplar.getText());
			if(ejemplar == null) {
				throw new Exception("No se encontro el ejemplar");
			}
			this.devolucionesService.devolverLibroScaneado(ejemplar,this.alumno);
			
			reload();
			this.xTextFieldCodigoEjemplar.setText("");
			showToast("Libro entregado correctamente");
			return;
			
		} catch (Exception e) {
			// TODO: handle exception
			showToastRED(e.getMessage());
		}
    }
    

	public void setAlumno(Alumno alumno) {
		this.alumno = this.alumnoDAO.hidratar(alumno.getId());
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
		for(Historial historial: this.alumno.getHistorials()) {
			if(historial.getFechaFinal() != null) continue;
			TreeItem ejemplares = new TreeItem<String>();
			ejemplares.setValue(historial.getEjemplare().getLibro().getContenido().getNombreCas());
			if(isInRoot(root,ejemplares)) continue;
			for(Historial historialLibro: this.alumno.getHistorials()) {
				if(historialLibro.getFechaFinal() != null) continue;
				if(!historialLibro.getEjemplare().getLibro().getContenido().getCodigo().equals(historial.getEjemplare().getLibro().getContenido().getCodigo())) {
					continue;
				}
				ejemplares.getChildren().add(new TreeItem<Ejemplare>(historialLibro.getEjemplare()));
				
			}
			ejemplares.setExpanded(true);
			root.getChildren().add(ejemplares);
		}
		if(root.getChildren().isEmpty()) {
			root.getChildren().add(new TreeItem<String>("Alumno no tiene libros prestados"));
		}
		xTreeViewLibros.setRoot(root);
		xTreeViewLibros.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
			// Body would go here
			if(newValue != null && newValue.getValue() instanceof Ejemplare) {
				this.selectedEjemplar = newValue.getValue();
			}
			

		});
		
	}
	
	private boolean isInRoot(TreeItem root,TreeItem libros) {
		for(Object item:root.getChildren()) {
			if(((TreeItem)item).getValue().equals(libros.getValue())){
				return true;
			};
		}
		
		return false;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.xTreeViewLibros.setShowRoot(false);

		this.xComboBoxEstado.getItems().addAll(
			    "Perfecto",
			    "Regular",
			    "Mal"
			);
		
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
