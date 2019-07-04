package command;

public class Main {

	public static void main(String[] args) {
		Book book = new Book(18000);
		Action action = new GeneralAction();
		action.execute(book);
		System.out.printf("%s\n",book);
		
		action = new SpecialAction();
		action.execute(book);
		System.out.printf("%s\n",  book);

	}

}
