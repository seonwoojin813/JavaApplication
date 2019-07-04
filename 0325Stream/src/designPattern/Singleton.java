package designPattern;

public class Singleton {
	//외부에서 객체를 생성할 수 없도록 생성자를 private으로 생
	private Singleton() {}
	
	//만들어진 객체의 참조를 저장할 static 변수를 생성
	private static Singleton singleton;
	
	//객체를 만들어 줄 static 메소드
	public static Singleton getInstance() {
		//static 변수의 값이 null일 때 만 생성
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}
