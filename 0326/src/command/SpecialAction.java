package command;

public class SpecialAction implements Action {

	@Override
	public void execute(Book book) {
		book.setPrice((int)(book.getPrice()*0.6));

	}

}
