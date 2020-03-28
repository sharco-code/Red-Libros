package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import dao.CursoDAO;
import dao.LibroDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import pojo.Contenido;
import pojo.Curso;
import pojo.Ejemplare;
import pojo.Libro;
import utiles.hibernate.UtilesHibernate;

public class LibroDetalleController implements Initializable{

    @FXML
    private TextField xTextFieldCodigo;

    @FXML
    private TextField xTextFieldNombre;

    @FXML
    private TextField xTextFieldISBN;

    @FXML
    private ComboBox<Integer> xComboBoxCursoEscolar;

    @FXML
    private ComboBox<Curso> xComboBoxCurso;

    @FXML
    private ComboBox<Contenido> xComboBoxAsignatura;
    
    @FXML
    private TextField xTextFieldUnidadesTotales;

    @FXML
    private TextField xTextFieldPrecio;

    @FXML
    private CheckBox xCheckBoxObsoleto;
    
    @FXML
    private ListView<?> xListViewEjemplar;
    
    private List<Curso> listaCursos = new ArrayList<>();
    
    private boolean nuevoLibro = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		try {
			listaCursos = CursoDAO.getCursos();
			
			
			
			xComboBoxCurso.setDisable(true);
			xComboBoxAsignatura.setDisable(true);
	        
	        rellenarCursoEscolar();
	        
	        setMostrarComboBoxAsignatura();
	        
	        setMostrarComboBoxCurso();
	        
	        setListenerComboBoxCurso();
	        setListenerComboBoxCursoEscolar();
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		this.xComboBoxAsignatura.setDisable(true);
    	this.xCheckBoxObsoleto.setDisable(true);
    	this.xComboBoxCursoEscolar.setDisable(true);
    	this.xComboBoxCurso.setDisable(true);
    	this.xCheckBoxObsoleto.setDisable(true);
    	this.xListViewEjemplar.setDisable(true);
	}
	
	@FXML
    private HBox xButtonEDITAR;

    @FXML
    private HBox xButtonGUARDAR;

    @FXML
    private HBox xButtonBORRAR;
	
    private boolean isButtonGuardarEnabled = false;
    
	@FXML
    void BorrarCLICKED(MouseEvent event) {

    }

    @FXML
    void EditarCLICKED(MouseEvent event) {

    	
    	isButtonGuardarEnabled = true;
    	this.xButtonGUARDAR.setStyle("-fx-background-color: #00d142;");
    	this.xButtonGUARDAR.setDisable(!isButtonGuardarEnabled);
    	
    	this.xComboBoxAsignatura.setDisable(false);
    	this.xCheckBoxObsoleto.setDisable(false);
    	this.xComboBoxCursoEscolar.setDisable(false);
    	this.xComboBoxCurso.setDisable(false);
    	
    	this.xTextFieldCodigo.setEditable(true);
    	
    	this.xTextFieldISBN.setEditable(true);
    	
    	this.xTextFieldNombre.setEditable(true);
    	this.xTextFieldUnidadesTotales.setEditable(true);
    	this.xTextFieldPrecio.setEditable(true);
    	
    	this.xTextFieldPrecio.setStyle("-fx-background-color: WHITE;");
    	this.xTextFieldCodigo.setStyle("-fx-background-color: WHITE;");
    	this.xTextFieldISBN.setStyle("-fx-background-color: WHITE;");
    	this.xTextFieldNombre.setStyle("-fx-background-color: WHITE;");
    	this.xTextFieldUnidadesTotales.setStyle("-fx-background-color: WHITE;");
    	
    	this.xButtonEDITAR.setDisable(true);
    	this.xButtonEDITAR.setStyle("-fx-background-color: GRAY;");
    }
    
    @FXML
    void EditarENTERED(MouseEvent event) {
    	if(!isButtonGuardarEnabled) {
    		this.xButtonEDITAR.setStyle("-fx-background-color:#0fbcf9; ");
    	}
    }

    @FXML
    void EditarEXITED(MouseEvent event) {
    	if(!isButtonGuardarEnabled) {
    		this.xButtonEDITAR.setStyle("-fx-background-color:#1f93ff; ");
    	}
    }

    
    @FXML
    void GuardarCLICKED(MouseEvent event) {

    	
    	isButtonGuardarEnabled = false;
    	this.xButtonGUARDAR.setDisable(!isButtonGuardarEnabled);
    	this.xButtonGUARDAR.setStyle("-fx-background-color: GRAY;");
    	
    	this.xComboBoxAsignatura.setDisable(true);
    	this.xCheckBoxObsoleto.setDisable(true);
    	this.xComboBoxCursoEscolar.setDisable(true);
    	this.xComboBoxCurso.setDisable(true);
    	this.xTextFieldCodigo.setEditable(true);
    	this.xTextFieldISBN.setEditable(true);
    	this.xTextFieldNombre.setEditable(true);
    	this.xTextFieldUnidadesTotales.setEditable(true);
    	this.xTextFieldPrecio.setEditable(true);
    	
    	this.xTextFieldPrecio.setStyle("-fx-background-color: TRANSPARENT;");
    	this.xTextFieldCodigo.setStyle("-fx-background-color: TRANSPARENT;");
    	this.xTextFieldISBN.setStyle("-fx-background-color: TRANSPARENT;");
    	this.xTextFieldNombre.setStyle("-fx-background-color: TRANSPARENT;");
    	this.xTextFieldUnidadesTotales.setStyle("-fx-background-color: TRANSPARENT;");
    	
    	this.xButtonEDITAR.setStyle("-fx-background-color: #1f93ff;");
    	this.xButtonEDITAR.setDisable(false);
    }
    
    @FXML
    void GuardarENTERED(MouseEvent event) {
    	if(isButtonGuardarEnabled) {
    		this.xButtonGUARDAR.setStyle("-fx-background-color: #66ff66;");
    	}
    	
    }

    @FXML
    void GuardarEXITED(MouseEvent event) {
    	if(isButtonGuardarEnabled) {
    		this.xButtonGUARDAR.setStyle("-fx-background-color: #33cc33;");
    	}
    	
    	
    }
    
	private void setMostrarComboBoxAsignatura() {
		Callback<ListView<Contenido>, ListCell<Contenido>> cellFactory = new Callback<ListView<Contenido>, ListCell<Contenido>>() {

		    @Override
		    public ListCell<Contenido> call(ListView<Contenido> l) {
		        return new ListCell<Contenido>() {

		            @Override
		            protected void updateItem(Contenido item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item == null || empty) {
		                    setGraphic(null);
		                } else {
		                    setText(item.getCodigo()+" - "+item.getNombreCas());
		                }
		            }
		        } ;
		    }
		};
		xComboBoxAsignatura.setButtonCell(cellFactory.call(null));
		xComboBoxAsignatura.setCellFactory(cellFactory);
	}

	private void setMostrarComboBoxCurso() {
		Callback<ListView<Curso>, ListCell<Curso>> cellFactory = new Callback<ListView<Curso>, ListCell<Curso>>() {

		    @Override
		    public ListCell<Curso> call(ListView<Curso> l) {
		        return new ListCell<Curso>() {

		            @Override
		            protected void updateItem(Curso item, boolean empty) {
		                super.updateItem(item, empty);
		                if (item == null || empty) {
		                    setGraphic(null);
		                } else {
		                    setText(item.getAbreviatura()+" - "+item.getNombreCas());
		                }
		            }
		        } ;
		    }
		};
		xComboBoxCurso.setButtonCell(cellFactory.call(null));
		xComboBoxCurso.setCellFactory(cellFactory);
	}

	private void setListenerComboBoxCurso() {
		xComboBoxCurso.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	xComboBoxAsignatura.getItems().clear();
		    	xComboBoxAsignatura.getItems().addAll(((Curso)newSelection).getContenidos());
		    	xComboBoxAsignatura.setDisable(false);
		    }else {
		    	xComboBoxAsignatura.setDisable(true);
		    }
		});
	}

	private void setListenerComboBoxCursoEscolar() {
		xComboBoxCursoEscolar.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	xComboBoxCurso.getItems().clear();
		    	xComboBoxCurso.setDisable(false);
		        for(Curso curso:listaCursos) {
		        	if(curso.getCursoEscolar() == newSelection) {
		        		xComboBoxCurso.getItems().add(curso);
		        	}
		        } 
		    }
		});
	}

	private void rellenarCursoEscolar() {
		HashSet<Integer> listaYears = new HashSet<>();
		for(Curso curso: listaCursos) {
			listaYears.add(curso.getCursoEscolar());
		}
		Iterator<Integer> it = listaYears.iterator();
		
		xComboBoxCursoEscolar.getItems().clear();
		while(it.hasNext()) {
			int year = it.next();
			xComboBoxCursoEscolar.getItems().add(year);
		}
	}
	
	public void setLibro(Libro libroViejo)  {
		
		try {
			nuevoLibro = false;
			Libro libro = LibroDAO.getLibro(libroViejo.getId());
			
			xTextFieldCodigo.setText(libro.getCodigo());
			xTextFieldISBN.setText(libro.getIsbn());
			xTextFieldNombre.setText(libro.getNombre());
			xTextFieldPrecio.setText(""+libro.getPrecio());
			xTextFieldUnidadesTotales.setText(""+libro.getUnidades());
			
			
			
			xComboBoxCursoEscolar.getSelectionModel()
				.select(xComboBoxCursoEscolar.getItems().indexOf(libro.getContenido()
								.getCursoBean()
								.getCursoEscolar()));
			
			//Hay que hacerlo asi por que la instacia es de otra sesion entonces no coinciden cuando haces un == (Por la serializacion)
			int cursoIndex = -1;
			for(Curso curso:xComboBoxCurso.getItems()) {
				cursoIndex++;
				if(curso.getId().equals(libro.getContenido()
						.getCursoBean().getId())) {
					break;
				}
				
			}
			
			xComboBoxCurso.getSelectionModel()
				.select(cursoIndex);
			
			int asignaturaIndex = -1;
			for(Contenido asignatura:xComboBoxAsignatura.getItems()) {
				asignaturaIndex++;
				if(asignatura.getId() == libro.getContenido().getId()) {
					break;
				}
				
			}
			
			xComboBoxAsignatura.getSelectionModel()
				.select(asignaturaIndex);
			
			
			if(libro.getObsoleto() != 0) {
				xCheckBoxObsoleto.setSelected(true);
			}
	        
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
