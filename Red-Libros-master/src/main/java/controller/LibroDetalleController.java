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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    
    
    
    private List<Curso> listaCursos = new ArrayList<>();
    
    private Session session;
    
    private boolean nuevoLibro = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
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
			xComboBoxAsignatura.setDisable(true);
	        
	        rellenarCursoEscolar();
	        
	        setMostrarComboBoxAsignatura();
	        
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
		
		SessionFactory factory;
		try {
			factory = UtilesHibernate.getSessionFactory();
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			nuevoLibro = false;
			Libro libro = session.get(Libro.class, libroViejo.getId());
			
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

}
