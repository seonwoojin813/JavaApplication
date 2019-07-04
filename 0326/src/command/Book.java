package command;

public class Book {
	private int price;
	
	public Book(int price) {
		super();
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [price=" + price + "]";
	}
	
	

}
