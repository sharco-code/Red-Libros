package controller;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.parser.ParseException;

import app.Init;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import pojo.Alumno;
import pojo.Curso;
import utiles.hibernate.UtilesHibernate;


public class DevolucionesController implements Initializable {

	@FXML
    private AnchorPane anchorpane;
	
	@FXML
    private TableView<Alumno> xTableMain;
	
	private Session session;
	
	private List<Alumno> listaAlumnos = new ArrayList<>();
	
	private List<Alumno> alumnosFiltrados = new ArrayList<>();
	
	@FXML
    private ComboBox<Curso> xComboBoxCurso;
	
	@FXML
    private ComboBox<Integer> xComboBoxCursoEscolar;
	
	private List<Curso> listaCursos = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SessionFactory factory;
		try {
			factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			Query q = session.createQuery("SELECT e FROM Curso e");
			listaCursos = q.getResultList();
			
			
			//Si pones esta linea peta
	        //session.getTransaction().commit();
			
			xComboBoxCurso.setDisable(true);
	        
	        rellenarCursoEscolar();
	        
	        
	        setMostrarComboBoxCurso();
	        
	        setListenerComboBoxCurso();
	        setListenerComboBoxCursoEscolar();
	        
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			/*
			if (newSelection != null) {
		    	
		    	xComboBoxAsignatura.getItems().clear();
		    	xComboBoxAsignatura.getItems().addAll(((Curso)newSelection).getContenidos());
		    	xComboBoxAsignatura.setDisable(false);
		    }else {
		    	xComboBoxAsignatura.setDisable(true);
		    }
		    */
		});
	}
	
	public void reload() throws SQLException, Exception {
		
		SessionFactory factory = UtilesHibernate.getSessionFactory();
		session = factory.getCurrentSession();
		
		xTableMain.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	/*
	        	Parent root = null;
	        	try {
	        		
	        		FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/alumnoDetalleComponent.fxml"));
	        		
	        		AlumnoDetalleController alumnoDetalleController = new AlumnoDetalleController();
	    			loader.setController(alumnoDetalleController);
	    			//root = FXMLLoader.load(getClass().getResource("/view/alumnoDetalleComponent.fxml"));
	    			root = loader.load();
	    			alumnoDetalleController.setAlumno(newSelection);
	    			anchorpane.getChildren().clear();
	    			anchorpane.getChildren().add(root);
	    			
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		}
	    		*/
	            
	        }
        });
		
		xTableMain.getItems().clear();
		
		 TableColumn apellido1Column = new TableColumn("Apellido1");
	        apellido1Column.setCellValueFactory(new PropertyValueFactory("apellido1"));
	        
		TableColumn apellido2Column = new TableColumn("Apellido2");
		apellido2Column.setCellValueFactory(new PropertyValueFactory("apellido2"));
        
        
        TableColumn nombreColumn = new TableColumn("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory("nombre"));
        
        xTableMain.getColumns().addAll(apellido1Column,apellido2Column,nombreColumn);
        xTableMain.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        getAlumnos();
        
        
    	xTableMain.getItems().addAll(listaAlumnos);

	}

	private void getAlumnos() {
		session.beginTransaction();
		Query q = session.createQuery("SELECT e FROM Alumno e");
        listaAlumnos = q.getResultList();
        session.getTransaction().commit();
	}

	public void filtrar(String newValue) {
		// TODO Auto-generated method stub
		alumnosFiltrados = listaAlumnos.stream()
				.filter(alumno -> alumno.getNombre().contains(newValue))
				.collect((Collectors.toList()));
		xTableMain.getItems().clear();
		xTableMain.getItems().addAll(alumnosFiltrados);
		
		
	}

	

}