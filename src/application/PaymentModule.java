package application;

import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaymentModule {
	@FXML
	private Pane cash;
	@FXML
	private Pane card;
	@FXML
	private Pane phone;
	@FXML
	private Label message;
	@FXML
	private ImageView messageImage;
	@FXML
	private SplitPane choice;
	@FXML
	private AnchorPane pick;
	
	public void cash(ActionEvent event) throws IOException{
		System.out.println("The user has picked cash.");
		choice.setDisable(true);
		choice.setOpacity(0);
		choice.toBack();
		pick.setOpacity(100);
		pick.setDisable(false);
		message.setText("Please insert your cash into the machine.");
		Image image = new Image("/images/cash.png");
		messageImage.setImage(image);
	}
	public void card(ActionEvent event) throws IOException{
		System.out.println("The user has picked card.");
		choice.setDisable(true);
		choice.setOpacity(0);
		choice.toBack();
		pick.setOpacity(100);
		pick.setDisable(false);
		message.setText("Please insert your card into the machine.");
		Image image = new Image("/images/card.png");
		messageImage.setImage(image);
	}
	public void phone(ActionEvent event) throws IOException{
		System.out.println("The user has picked phone.");
		choice.setDisable(true);
		choice.setOpacity(0);
		choice.toBack();
		pick.setOpacity(100);
		pick.setDisable(false);
		message.setText("Please tap your phone on the machine.");
		Image image = new Image("/images/phone.png");
		messageImage.setImage(image);
		wait(event);
	}
	public void wait(ActionEvent event) throws IOException{
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	Platform.runLater(new Runnable() {
		                    public void run() {
				        		try {
									changeToConfirm(event);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    }
		                });
		            }
		        }, 
		        8000 
		);
	}
	public void returnToChoice(ActionEvent event) throws IOException{
		System.out.println("The user has gone back to Payment Choices.");
		choice.setDisable(false);
		choice.setOpacity(100);
		choice.toFront();
		pick.setDisable(true);
		pick.setOpacity(0);
	}
	public void changeToConfirm(ActionEvent event) throws IOException{
		System.out.println("The order has been submitted.");
		Parent home = FXMLLoader.load(getClass().getResource("/xml/ConfirmationPage.fxml"));
		Scene scene = new Scene(home);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
		new java.util.Timer().schedule( 
		        new java.util.TimerTask() {
		            @Override
		            public void run() {
		            	Platform.runLater(new Runnable() {
		                    public void run() {
				        		Parent home2;
								try {
									home2 = FXMLLoader.load(getClass().getResource("/xml/BeginPage.fxml"));
					        		Scene scene2 = new Scene(home2);
					        		stage.setScene(scene2);
					        		stage.show();
								} catch (IOException e) {
									e.printStackTrace();
								}
		                    }
		                });
		            }
		        }, 
		        5000 
		);
	}
	public void changeToMenu(ActionEvent event) throws IOException{
		Parent home = FXMLLoader.load(getClass().getResource("/xml/OrderPage.fxml"));
		Scene scene = new Scene(home);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		System.out.println("Going back to Menu.");
	}
}