package observer;

import java.util.ArrayList;
import java.util.List;

//Observing을 등록하는 메소드를 소유한 추상 클래스
//추상클래스: 자신의 객체(인스턴스)를 생성할 수 없는 클래
public abstract class Subject {
	private List <Observer> observers = new ArrayList<>();
	
	//감시할 객체를 등록하는 메소드
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	//상태가 변경되었을 때 리스트의 모든 데이터가 통지 메소드를 호출
	public void notifyObservers() {
		for(Observer observer : observers) {
			observer.update(this);
		}
	}
	
	//실제 작업을 수행할 메소드의 원형 
	public abstract void execute();
	

}
