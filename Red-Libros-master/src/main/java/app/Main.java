package app;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage stage;
	
	@SuppressWarnings({ "exports", "static-access" })
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/mainView.fxml"));
	        Parent root = loader.load();
	        Scene scene = new Scene(root);
	        scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
	        primaryStage.setTitle("Red de Libros");
	        primaryStage.setScene(scene);
	        
	        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon.png")));
	        
	        
	        primaryStage.setMinHeight(570);
	        primaryStage.setMinWidth(1100);
	        
	        primaryStage.show();
	        
	        this.stage = primaryStage;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("exports")
	public static Stage getStage() {
		return stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
