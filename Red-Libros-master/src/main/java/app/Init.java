package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Init extends Application {

	private static Stage stage;
	
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
	        
	        this.stage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static Stage getStage() {
		return stage;
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
