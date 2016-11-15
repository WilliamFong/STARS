package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainModule {
	//changes the screen to the Menu Module
	public void changeToMenu(ActionEvent event) throws IOException{
		  Parent home = FXMLLoader.load(getClass().getResource("/xml/OrderPage.fxml"));
		  Scene scene = new Scene(home);
          Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		  stage.setScene(scene);
		  stage.show();
	}
}
