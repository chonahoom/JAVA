package Eagle_Breed;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Eagle_Breed_main extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage primaryStage) throws Exception {
		
		
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUI.fxml"));	
		Parent pt = loader.load();
	
		Scene scene = new Scene(pt);
		
		primaryStage.setResizable(false);
		primaryStage.setTitle("AppMain");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	
		
	}
}