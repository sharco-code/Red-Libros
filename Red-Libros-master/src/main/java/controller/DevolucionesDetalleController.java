package controller;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import dao.AlumnoDAO;
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
import pojo.Ejemplare;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;
import service.DevolucionesService;
import view.Toast;
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
    private TreeView<Libro> xTreeViewLibros;

    @FXML
    private HBox xButtonDevolver;

    
    private Alumno alumno;
    
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    
    private Libro selectedLibro;
    
    private DevolucionesService devolucionesService;
    
    public DevolucionesDetalleController(){
    	this.devolucionesService = new DevolucionesService();
    }
    

    @FXML
    void DevolverCLICKED(MouseEvent event) {
    	if(this.selectedLibro == null) {
			return;
		}
		try {
			this.devolucionesService.devolverLibro(this.selectedLibro,this.alumno);
			
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
			TreeItem libros = new TreeItem<String>();
			libros.setValue(historial.getEjemplare().getLibro().getContenido().getNombreCas());
			if(isInRoot(root,libros)) continue;
			for(Historial historialLibro: this.alumno.getHistorials()) {
				if(historialLibro.getFechaFinal() != null) continue;
				if(!historialLibro.getEjemplare().getLibro().getContenido().getCodigo().equals(historial.getEjemplare().getLibro().getContenido().getCodigo())) {
					continue;
				}
				libros.getChildren().add(new TreeItem<Libro>(historialLibro.getEjemplare().getLibro()));
				
			}
			libros.setExpanded(true);
			root.getChildren().add(libros);
		}
		xTreeViewLibros.setRoot(root);
		xTreeViewLibros.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
			// Body would go here
			if(newValue != null && newValue.getValue() instanceof Libro) {
				this.selectedLibro = newValue.getValue();
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
