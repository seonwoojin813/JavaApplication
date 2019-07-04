package command;

public class GeneralAction implements Action {
	
	@Override
	public void execute(Book book) {
		book.setPrice((int)(book.getPrice()*0.8));
	}

}
