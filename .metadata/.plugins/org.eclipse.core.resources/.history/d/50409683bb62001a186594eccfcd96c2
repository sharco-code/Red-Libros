package proyectoFinal.redLibros;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PaginaPrincipal.fxml"));
	        Parent root = loader.load();;
	        
	        primaryStage.setTitle("Prueba");
	        primaryStage.setScene(new Scene(root, 800, 650));
	        primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
}