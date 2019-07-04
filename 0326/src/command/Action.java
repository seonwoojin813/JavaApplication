package command;

//모든 Command 클래스가 공통으로 가져야 할 메소드를 소유한 인터페이
public interface Action {
	//Command 클래스에 구현할 메소드
	public void execute(Book book);

}
