package observer;

public class DataChanger extends Subject {
	
	int status;
	
	@Override
	public void execute() {
		status = status +1;
		System.out.printf("상태변화가 생겼습니다.\n");
		//상태변화를 통지
		this.notifyObservers();

	}

}
