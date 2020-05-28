package controller;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import dao.AlumnoDAO;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    private HBox xButtonScan;

    @FXML
    private TreeView<Ejemplare> xTreeViewLibros;

    @FXML
    private HBox xButtonDevolver;

    @FXML
    private RadioButton xRadioButtonPerfecto;

    @FXML
    private RadioButton xRadioButtonRegular;

    @FXML
    private RadioButton xRadioButtonMal;
    
    private Alumno alumno;
    
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    
    private Ejemplare selectedEjemplar;
    
    private DevolucionesService devolucionesService;
    
    public DevolucionesDetalleController(){
    	this.devolucionesService = new DevolucionesService();
    }
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		this.xTreeViewLibros.setShowRoot(false);
		
		ToggleGroup toggleGroup = new ToggleGroup();
		
		this.xRadioButtonMal.setToggleGroup(toggleGroup);
		this.xRadioButtonPerfecto.setToggleGroup(toggleGroup);
		this.xRadioButtonRegular.setToggleGroup(toggleGroup);
	}

    @FXML
    public void onEnter(ActionEvent ae){
       ScanCLICKED(null);
    }
    
    @FXML
    void DevolverCLICKED(MouseEvent event) {
    	if(this.selectedEjemplar == null) {
    		showToastRED("Selecciona un libro para devolver");
			return;
		}
		try {
			int estado = 0;
			if(this.xRadioButtonPerfecto.isSelected()) {
				estado = 0;
				
			} else if(this.xRadioButtonRegular.isSelected()) {
				estado = 1;
				
			}else if(this.xRadioButtonMal.isSelected()) {
				estado = 2;
			}
			
			this.devolucionesService.devolverLibro(this.selectedEjemplar,this.alumno, estado);
			
			reload();
			showToast("Libro devuelto correctamente");
		} catch (Exception e) {
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
			
		} catch (Exception e) {
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTreeView(){
		TreeItem root = new TreeItem();
		for(Historial historial: this.alumno.getHistorials()) {
			if(historial.getFechaFinal() != null) continue;
			
			TreeItem ejemplares = new TreeItem<String>();
			
			ejemplares.setValue(historial.getEjemplare().getLibro().getContenido().getNombreCas());
			
			if(isInRoot(root,ejemplares)) continue;
			
			rellenarTreeView(historial, ejemplares);
			
			ejemplares.setExpanded(true);
			root.getChildren().add(ejemplares);
		}
		if(root.getChildren().isEmpty()) {
			root.getChildren().add(new TreeItem<String>("El alumno no tiene libros prestados"));
		}
		xTreeViewLibros.setRoot(root);
		xTreeViewLibros.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
			// Body would go here
			if(newValue != null && newValue.getValue() instanceof Ejemplare) {
				seleccionaRadioButton(newValue);
			}
			

		});
		
	}

	private void seleccionaRadioButton(TreeItem<Ejemplare> newValue) {
		this.selectedEjemplar = newValue.getValue();
		if(this.selectedEjemplar.getEstado() == 0) {
			this.xRadioButtonPerfecto.setSelected(true);
			
		} else if(this.selectedEjemplar.getEstado() == 1) {
			this.xRadioButtonRegular.setSelected(true);
			
		}else if(this.selectedEjemplar.getEstado() == 2) {
			this.xRadioButtonMal.setSelected(true);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void rellenarTreeView(Historial historial, TreeItem ejemplares) {
		for(Historial historialLibro: this.alumno.getHistorials()) {
			if(historialLibro.getFechaFinal() != null || !historialLibro.getEjemplare().getLibro().getContenido().getCodigo().equals(historial.getEjemplare().getLibro().getContenido().getCodigo())) {
				continue;
			}
			ejemplares.getChildren().add(new TreeItem<Ejemplare>(historialLibro.getEjemplare()));
			
		}
	}
	@SuppressWarnings({ "rawtypes" })
	private boolean isInRoot(TreeItem root,TreeItem libros) {
		for(Object item:root.getChildren()) {
			if(((TreeItem)item).getValue().equals(libros.getValue())){
				return true;
			}
		}
		return false;
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
