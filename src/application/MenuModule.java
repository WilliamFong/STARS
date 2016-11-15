package application;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuModule {
	public ObservableList<Dish> order = FXCollections.observableArrayList();
	public Ingredient[] ingredientsDatabase = initializeIngredients();
	public Dish[] dish = initializeDish(ingredientsDatabase);

	@FXML
	private Accordion menu;
	@FXML
	private TitledPane entreeTab;
	@FXML
	private TableView<Dish> currentOrder;
	@FXML
	private TableColumn orderDishName;
	@FXML
	private TableColumn orderPrice;
	@FXML
	private Label price;

	@FXML
	private Pane cash;
	@FXML
	private Pane card;
	@FXML
	private Pane phone;
	@FXML
	private FXMLLoader orderTotal;
	String totalPrice;

	@FXML
	public void initialize(){
		//pull order information
		menu.setExpandedPane(entreeTab);
	}

	public void addBT(){
		addItem(0);
	}
	public void addKP(){
		addItem(1);
	}
	public void addLC(){
		addItem(2);
	}
	public void addOC(){
		addItem(3);
	}
	public void addTT(){
		addItem(4);
	}
	public void addYC(){
		addItem(5);
	}
	public void addChai(){
		addItem(6);
	}
	public void addCoffee(){
		addItem(7);
	}
	public void addHor(){
		addItem(8);
	}
	public void addMat(){
		addItem(9);
	}
	public void addSoda(){
		addItem(10);
	}
	public void addChoco(){
		addItem(11);
	}

	public void addItem(int ID){
		order.add(dish[ID]);
		currentOrder.setItems(order);

		orderDishName.setCellValueFactory(new PropertyValueFactory("name"));
		orderPrice.setCellValueFactory(new PropertyValueFactory("price"));

		System.out.println("You have ordered " + dish[ID].getName() + " - $" + dish[ID].getPrice() + ".");
		updatePrice();
	}

	public void updatePrice(){
		double total = 0.00;
		for (int i = 0; i < order.size(); i++){
			total += Double.parseDouble(order.get(i).price);
		}
		NumberFormat format = NumberFormat.getCurrencyInstance();
		price.setText(format.format(total));
	}
	public void reset(ActionEvent event) throws IOException{
		if(!order.isEmpty()){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Reset?");
			String s = "Are you sure you want to reset?";
			alert.setContentText(s);

			Optional<ButtonType> result = alert.showAndWait();

			if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
				order.clear();
				updatePrice();
				System.out.println("The order has been reset.");
			}
		}
	}

	public void changeToPayment(ActionEvent event) throws IOException{
		if (!order.isEmpty()){
			updateOrdersDatabase();
			Parent home = FXMLLoader.load(getClass().getResource("/xml/PaymentPage.fxml"));
			Scene scene = new Scene(home);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setUserData(getPrice());
			stage.setScene(scene);
			stage.show();
			System.out.println("The order has been sent to Payment.");
		}
	}

	public double getPrice(){
		double total = 0.00;
		for (int i = 0; i < order.size(); i++){
			total += Double.parseDouble(order.get(i).price);
		}
		String priceText = String.format("$%.2f", total);
		price.setText(priceText);		
		return total;
	}
	public void updateOrdersDatabase(){
		String fileName = "Orders.csv";
		File sourceFile = new File(fileName);

		if(!sourceFile.exists()){
			try{
				PrintWriter writer = new PrintWriter(fileName);
				writer.println("1," + getPrice());
				writer.close();
			}
			catch(Exception e){ e.printStackTrace(); }
		}
		else{
			try{
				int count = 1;
				PrintWriter writer = new PrintWriter("temp.csv");
				Scanner input = new Scanner(sourceFile);
				String addString = "";
				while(input.hasNext()){
					addString = input.next();
					writer.println(addString);
					count++;
				}
				addString = count + "," + String.format("$%.2f", getPrice());
				writer.println(addString);
				writer.close();

				File newFile = new File("temp.csv");
				sourceFile.delete();
				newFile.renameTo(new File(fileName));
			}
			catch(Exception e){ e.printStackTrace(); }
		}
	}

	public Ingredient[] initializeIngredients() {
		Ingredient[] ingredientsDatabase = new Ingredient[23];
		ingredientsDatabase[0] = new Ingredient("Beef Tongue", 10);
		ingredientsDatabase[1] = new Ingredient("Chicken", 10);
		ingredientsDatabase[22] = new Ingredient("Rice", 10);
		ingredientsDatabase[2] = new Ingredient("Kung Pao Sauce", 10);
		ingredientsDatabase[3] = new Ingredient("Chili", 10);
		ingredientsDatabase[4] = new Ingredient("Cheese", 10);
		ingredientsDatabase[22] = new Ingredient("Fries", 10);
		ingredientsDatabase[5] = new Ingredient("Lenti", 10);
		ingredientsDatabase[6] = new Ingredient("Orange Sauce", 10);
		ingredientsDatabase[7] = new Ingredient("Tortilla", 10);
		ingredientsDatabase[8] = new Ingredient("Tandoori", 10);
		ingredientsDatabase[9] = new Ingredient("Takoyaki", 10);
		ingredientsDatabase[10] = new Ingredient("Yellow Curry", 10);
		ingredientsDatabase[11] = new Ingredient("Chai", 10);
		ingredientsDatabase[12] = new Ingredient("Milk", 10);
		ingredientsDatabase[13] = new Ingredient("Sugar", 10);
		ingredientsDatabase[14] = new Ingredient("Coffee", 10);
		ingredientsDatabase[15] = new Ingredient("Creamer", 10);
		ingredientsDatabase[16] = new Ingredient("Cinnamon", 10);
		ingredientsDatabase[17] = new Ingredient("Rice", 10);
		ingredientsDatabase[18] = new Ingredient("Matcha Powder", 10);
		ingredientsDatabase[19] = new Ingredient("Soda", 10);
		ingredientsDatabase[20] = new Ingredient("Spices", 10);
		ingredientsDatabase[21] = new Ingredient("Chocolate", 10);

		return ingredientsDatabase;
	}

	public Dish[] initializeDish(Ingredient[] ingredientsDatabase) {
		Dish[] database = new Dish[12];	

		database[0] = new Dish("Beef Tongue Fried Rice", "15.00", (new Ingredient[] {ingredientsDatabase[0], 
				ingredientsDatabase[1], ingredientsDatabase[22]}) );
		database[1] = new Dish("Kung Pao Chili Cheese Fries", "13.00", (new Ingredient[] {ingredientsDatabase[2], 
				ingredientsDatabase[1], ingredientsDatabase[3], 
				ingredientsDatabase[4], ingredientsDatabase[22]}) );
		database[2] = new Dish("Lenti Chili", "7.00", (new Ingredient[] {ingredientsDatabase[5], ingredientsDatabase[3]} ));
		database[3] = new Dish("Orange Chicken Burrito", "8.00", (new Ingredient[] {ingredientsDatabase[6], 
				ingredientsDatabase[1], ingredientsDatabase[7]}) );
		database[4] = new Dish("Tandoori Takoyaki", "5.00", (new Ingredient[] {ingredientsDatabase[8], ingredientsDatabase[1],
				ingredientsDatabase[9]}) );
		database[5] = new Dish("Yellow Curry Chicken Tacos", "5.00", (new Ingredient[] {ingredientsDatabase[10],
				ingredientsDatabase[1], ingredientsDatabase[7]}) );
		database[6] = new Dish("Chai", "2.75", (new Ingredient[] {ingredientsDatabase[11], ingredientsDatabase[12],
				ingredientsDatabase[13]}) );
		database[7] = new Dish("Coffee", "2.50", (new Ingredient[] {ingredientsDatabase[14], ingredientsDatabase[15],
				ingredientsDatabase[12], ingredientsDatabase[13]}) );
		database[8] = new Dish("Horchata", "3.00", (new Ingredient[] {ingredientsDatabase[16], ingredientsDatabase[17],
				ingredientsDatabase[12], ingredientsDatabase[13]}) );
		database[9] = new Dish("Matcha Green Tea", "4.00", (new Ingredient[] {ingredientsDatabase[18], ingredientsDatabase[12],
				ingredientsDatabase[13]}) );
		database[10] = new Dish("Soda", "1.50", (new Ingredient[] {ingredientsDatabase[19]}) );
		database[11] = new Dish("Spiced Hot Chocolate", "2.50", (new Ingredient[] {ingredientsDatabase[20], ingredientsDatabase[21]}) );

		return database;
	}

}