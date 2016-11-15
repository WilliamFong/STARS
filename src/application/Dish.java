package application;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Dish {

	String name;
	File image;
	List<Ingredient> ingredientsList;
	String price;
	boolean available;
	
	public Dish(String name, String price, Ingredient[] ingredientsList) {
		this.name = name;
		this.price = price;

		List<Ingredient> addToIngrediants = new ArrayList<Ingredient>();
		for (int i = 0; i < ingredientsList.length; i++) {
			addToIngrediants.add(ingredientsList[i]);
		}
		this.ingredientsList = addToIngrediants;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}


	public List<Ingredient> getIngredientsList() {
		return ingredientsList;
	}
	public void setIngredientsList(List<Ingredient> ingredientsList) {
		this.ingredientsList = ingredientsList;
	}


	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}


	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void checkAvailable(){
		for(Ingredient ingredient : this.ingredientsList){
			if(ingredient.getQuantity() == 0)
				this.available = false;
		}
	}

}