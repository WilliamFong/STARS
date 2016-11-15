package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/xml/BeginPage.fxml"));
			Scene scene = new Scene(root,1280,720);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.resizableProperty();
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("STARS Menu");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
