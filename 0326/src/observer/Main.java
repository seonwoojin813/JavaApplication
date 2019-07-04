package observer;

public class Main {

	public static void main(String[] args) {
		//감시자 객체를 생성
		Observer observer = new Client();
		//감시 당할 대상을 생성
		Subject subject = new DataChanger();
		//감시당할 대상을 감시자 객체에 등록
		subject.addObserver(observer);
		
		subject.execute();

	}

}
