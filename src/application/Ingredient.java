package application;

public class Ingredient {

	String name;
	Integer quantity;

	public Ingredient(String name, int quantity){
		this.name = name;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	//added 

	public void remove(Integer amount) {
		this.quantity = this.quantity- amount;
	}
	public void decrease() {
		this.quantity = this.quantity-1;
	}
	public void change(Integer amount) {
		this.quantity = amount;
	}

	public void add(Integer add) {
		this.quantity = this.quantity + add;
	}
}
