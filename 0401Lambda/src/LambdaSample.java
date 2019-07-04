
//인터페이스: 추상메소드(메소드의 원형만 있는 경우)와 final 변수만을 가진 객체
//추상메소드는 상속받은 클래스에서 재정의(Overriding) 해서 사용합니다.
interface LambdaSampl {
	public void method();
}

//매개변수가 있는 메소드를 소유한 인터페이스 
interface LambdaSampl2 {
	public void method2(int x);
}

//리턴 타입이 있는 메소드를 소유한 인터페이스
interface LambdaSampl3{
	public int method3(int x);
}

public class LambdaSample {

	public static void main(String[] args) {
		// 매개변수가 없을 때는 ()는 생략할 수 없습니다.
		// 아래와 같이 실행 문장이 한 줄이면 {}는 생략 할 수 있습니다.
		LambdaSampl ob1 = () -> {
			System.out.printf("매개변수와 리턴이 없는 메소드\n");
		};
		ob1.method();
		
		//람다에서는 실행할 때 메소드의 모양이 결정되기 때문에 매개변수의 자료형을 생략해도 됩니다.
		//매개변수가 1개일때는 () 생략가능
		//처리 구문이 한 줄이면 {} 생략가
		LambdaSampl2 ob2 = (x) -> System.out.printf("x:%s\n", x);
		ob2.method2(100);
		
		LambdaSampl3 ob3 = x -> {
			int result = x * x;
			return result;
		};
		System.out.printf("결과%d\n" ,ob3.method3(20));
		
	}

}
