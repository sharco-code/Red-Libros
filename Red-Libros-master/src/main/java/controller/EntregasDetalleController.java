package controller;
import java.util.ArrayList;
import java.util.List;

import dao.HistorialDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.converter.DefaultStringConverter;
import model.EjemplarTabla;
import model.EntregaTabla;
import pojo.Alumno;
import pojo.Ejemplare;
import pojo.Historial;
import pojo.Libro;
import pojo.Matricula;
import service.BarcodeService;
import service.EntregasService;
public class EntregasDetalleController {
	@FXML
    private TextField xTextFieldNIA;

    @FXML
    private TextField xTextFieldExpediente;

    @FXML
    private TextField xTextFieldNombre;

    @FXML
    private TreeView<Libro> xTreeViewLibros;

    private EntregasService entregasService;
    
    private Alumno alumno;
    
    @FXML
    private HBox xButtonEntregar;
    
    @FXML
    private TableView<EntregaTabla> xTableViewHistorial;
    
    @FXML
    private HBox xButtonScan;
    
    @FXML
    private TextField xTextFieldCodigoEjemplar;
    
    @SuppressWarnings("unchecked")
	private TableColumn<EntregaTabla, String> asignaturaColumn = new TableColumn("Asignatura");
	@SuppressWarnings("unchecked")
	private TableColumn<EntregaTabla,String> fechaInicialColumn = new TableColumn("Fecha Inicial");
	@SuppressWarnings("unchecked")
	private TableColumn<EntregaTabla, String> estadoInicialColumn = new TableColumn("Estado");
	@SuppressWarnings("unchecked")
	private TableColumn<EntregaTabla, String> cursoColumn = new TableColumn("Curso");
	@SuppressWarnings("unchecked")
	private TableColumn<EntregaTabla, String> ejemplarColumn = new TableColumn("Ejemplar");
    
	private Libro selectedLibro;
	private List<Libro> listaLibros = new ArrayList<>();

	public EntregasDetalleController() {
		entregasService = new EntregasService();
		
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
		setTable();

	}
	
	private void setTreeView(){
		TreeItem root = new TreeItem();
		for(Matricula matricula: this.alumno.getMatriculas()) {
			TreeItem libros = new TreeItem<String>();
			libros.setValue(matricula.getContenidoBean().getNombreCas());
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
			}
			

		});
		
	}
	
	private void setTable() {
		xTableViewHistorial.getItems().clear();
		xTableViewHistorial.getColumns().clear();



		ejemplarColumn = new TableColumn("Ejemplar");
		ejemplarColumn.setCellValueFactory(new PropertyValueFactory<>("idEjemplar"));

		estadoInicialColumn = new TableColumn("Estado");
		estadoInicialColumn.setCellValueFactory(new PropertyValueFactory<>("estadoInicial"));
		
		fechaInicialColumn = new TableColumn("Fecha Inicial");
		fechaInicialColumn.setCellValueFactory(new PropertyValueFactory("fecha_inicial"));
		
		asignaturaColumn = new TableColumn("Asignatura");
		asignaturaColumn.setCellValueFactory(new PropertyValueFactory("asignatura"));
		
		cursoColumn = new TableColumn("Curso");
		cursoColumn.setCellValueFactory(new PropertyValueFactory("curso"));
		
		xTableViewHistorial.getColumns().addAll(cursoColumn,asignaturaColumn,ejemplarColumn,estadoInicialColumn,fechaInicialColumn);
		xTableViewHistorial.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		xTableViewHistorial.setEditable(true);

	
		

		xTableViewHistorial.getItems().addAll(entregasService.getHistorialTabla(this.alumno.getId()));
	}
	
	@FXML
    void EntregarCLICKED(MouseEvent event) {
		if(this.selectedLibro == null) {
			return;
		}
		try {
			this.entregasService.entregarLibro(this.selectedLibro,this.alumno);
			this.listaLibros.remove(this.selectedLibro);
			reload();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

    }
	
	@FXML
    void ScanCLICKED(MouseEvent event) throws Exception {
		if(this.xTextFieldCodigoEjemplar.getText().equals("")) {
			return;
		}
		
		Ejemplare ejemplar = this.entregasService.scanEjemplar(this.xTextFieldCodigoEjemplar.getText());
		if(ejemplar == null) {
			throw new Exception("No se encontro el ejemplar");
		}
		
		for(Libro libro: this.listaLibros) {
			if(libro.getCodigo().equals(ejemplar.getLibro().getCodigo())) {
				this.entregasService.entregarLibroScaneado(ejemplar,this.alumno);
				
				this.listaLibros.remove(libro);
				reload();
				return;
			}
		}
		
		throw new Exception("El alumno no esta matriculado en la asignatura de este libro");
		
		
		

    }
		
    
    
}