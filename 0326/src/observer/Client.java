package observer;


public class Client implements Observer {
	
	//Subject의 상태 변화가 생기면 수행할 메소드의 구
	@Override
	public void update(Subject subject) {
		System.out.printf("%s의 상태변화를 감지\n", subject);

	}


}
