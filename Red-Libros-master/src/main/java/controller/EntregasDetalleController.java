package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.EntregaTabla;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Libro;
import pojo.Matricula;
import service.EntregasService;
import service.LoaderService;
import view.Toast;

public class EntregasDetalleController implements Initializable {
	
	@FXML
    private TextField xTextFieldNIA;

    @FXML
    private TextField xTextFieldExpediente;

    @FXML
    private TextField xTextFieldNombre;

    @FXML
    private TreeView<Libro> xTreeViewLibros;
    
    @FXML
    private HBox xButtonEntregar;
    
    @FXML
    private TableView<EntregaTabla> xTableViewHistorial;
    
    @FXML
	private HBox xButtonScan;
    
    @FXML
    private TextField xTextFieldCodigoEjemplar;

    private EntregasService entregasService;
    
    private Alumno alumno;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private TableColumn<EntregaTabla, String> asignaturaColumn = new TableColumn("Asignatura");
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private TableColumn<EntregaTabla,String> fechaInicialColumn = new TableColumn("Fecha Inicial");
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private TableColumn<EntregaTabla, String> ejemplarColumn = new TableColumn("Ejemplar");
    
	private Libro selectedLibro;
	
	private List<Libro> listaLibros = new ArrayList<>();
	
	private LoaderService loaderService;

	public EntregasDetalleController(LoaderService loaderService) {
		entregasService = new EntregasService();
		this.loaderService = loaderService;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.xTreeViewLibros.setShowRoot(false);
		this.xTableViewHistorial.setPlaceholder(new Label("No hay entregas"));
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
		reload();
	}

	@FXML
    public void onEnter(ActionEvent ae){
       ScanCLICKED(null);
    }
	
	private void reload(){
		this.xTextFieldExpediente.setText(this.alumno.getExpediente());
		this.xTextFieldNIA.setText(this.alumno.getNia());
		this.xTextFieldNombre.setText(this.alumno.getNombre()+" "+this.alumno.getApellido1()+" "+this.alumno.getApellido2());
		setTreeView();
		setTable();

	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setTreeView(){
		TreeItem root = new TreeItem();
		for(Matricula matricula: this.alumno.getMatriculas()) {
			if(!this.entregasService.currentMatricula(matricula)) {
				continue;
			}
			
			TreeItem libros = new TreeItem<String>();
			libros.setValue(matricula.getContenidoBean().getNombreCas());
			if(isInRoot(root,libros)) continue;
			for(Libro libro:this.entregasService.getLibros(matricula)) {
				libros.getChildren().add(new TreeItem<>(libro));
				this.listaLibros.add(libro);
			}
			libros.setExpanded(true);
			root.getChildren().add(libros);
			
		}
		root.setExpanded(true);
		xTreeViewLibros.setRoot(root);
		xTreeViewLibros.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> { 
			// Body would go here
			if(newValue != null && newValue.getValue() instanceof Libro) {
				this.selectedLibro = newValue.getValue();
			}else {
				this.selectedLibro = null;
			}
		});
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void setTable() {
		xTableViewHistorial.getItems().clear();
		
		xTableViewHistorial.getColumns().clear();



		ejemplarColumn = new TableColumn("Ejemplar");
		
		ejemplarColumn.setCellValueFactory(new PropertyValueFactory<>("idEjemplar"));

		
		fechaInicialColumn = new TableColumn("Fecha Inicial");
		fechaInicialColumn.setCellValueFactory(new PropertyValueFactory("fecha_inicial"));
		
		asignaturaColumn = new TableColumn("Asignatura");
		asignaturaColumn.setCellValueFactory(new PropertyValueFactory("asignatura"));
		
		
		xTableViewHistorial.getColumns().addAll(asignaturaColumn,ejemplarColumn,fechaInicialColumn);
		
		xTableViewHistorial.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		xTableViewHistorial.setEditable(true);


		xTableViewHistorial.getItems().addAll(entregasService.getHistorialTabla(this.alumno.getId()));
	}
	
	@FXML
    void EntregarCLICKED(MouseEvent event) {
		if(this.selectedLibro == null) {
			this.loaderService.loadToastRED("Debes seleccionar un libro para entregar");
			return;
		}
		try {
			this.entregasService.entregarLibro(this.selectedLibro,this.alumno);
			this.listaLibros.remove(this.selectedLibro);
			
			reload();
			this.loaderService.loadToast("Libro entregado correctamente");
		} catch (Exception e) {
			this.loaderService.loadToastRED(e.getMessage());
		}
		

    }
	
	@FXML
    void ScanCLICKED(MouseEvent event) {
		if(this.xTextFieldCodigoEjemplar.getText().equals("")) {
			return;
		}
		try {
			Ejemplare ejemplar = this.entregasService.scanEjemplar(this.xTextFieldCodigoEjemplar.getText());
			if(ejemplar == null) {
				throw new Exception("No se encontro el ejemplar");
			}
			for(Libro libro: this.listaLibros) {
				if(libro.getCodigo().equals(ejemplar.getLibro().getCodigo())) {
					this.entregasService.entregarLibroScaneado(ejemplar,this.alumno);
					
					this.listaLibros.remove(libro);
					reload();
					this.xTextFieldCodigoEjemplar.setText("");
					this.loaderService.loadToast("Libro entregado correctamente");
					return;
				}
			}
			
			throw new Exception("El alumno no esta matriculado en la asignatura de este libro");
		} catch (Exception e) {
			this.loaderService.loadToastRED(e.getMessage());
		}
		

    }
	
	@SuppressWarnings({  "rawtypes" })
	private boolean isInRoot(TreeItem root,TreeItem libros) {
		for(Object item:root.getChildren()) {
			if(((TreeItem)item).getValue().equals(libros.getValue())){
				return true;
			}
		}
		return false;
	}
	
	


	
		
    
    
}
