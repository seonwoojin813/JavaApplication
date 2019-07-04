package observer;

//객체를 감시하다가 객체에 변화가 생겼을 때 호출될 메소드의 원형을 가진 인터페이
public interface Observer {
	public void update(Subject subject);

}
