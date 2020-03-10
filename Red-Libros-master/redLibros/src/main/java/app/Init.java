package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Init extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
	        Parent root = loader.load();
	        
	        primaryStage.setTitle("Red de Libros");
	        primaryStage.setScene(new Scene(root));
	        
	        primaryStage.setMinHeight(570);
	        primaryStage.setMinWidth(1100);
	        
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
