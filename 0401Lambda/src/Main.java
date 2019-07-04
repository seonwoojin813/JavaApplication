import java.util.Random;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

	public static void main(String[] args) {
		//Supplier 인터페이
		//T(-generic) get() 메소드 소유
		Supplier<Integer> ob1 = () -> {
			//랜점한 1부터 45사이의 숫자를 리턴
			Random r = new Random();
			int su = r.nextInt(45) + 1;
			return su;
		};
		//Object를 int에 대입할 때는 Integer로 형 변환한 후 대입 
		int result = ob1.get();
		System.out.printf("result: %d\n", result);
		
		
		//Student를 String으로 변환해서 리턴
		Function<Student, String> ob2 = (t) -> {
			return t.getName();
		};
		System.out.printf("%s\n", ob2.apply(new Student(1, "배유나", 99)));
		
		//Operator 인터페이스
		//데이터를 1개나 2개를 받아서 연산한 후 결과를 리턴하는 인터페이스(집계를 위한 인터페이스)
		
		//IntOperator: int 1개를 받아서 int 1개를 리턴하는 인터페이
		IntUnaryOperator ob3 = (int x) -> {
			x= x+1;
			return x;
		};
		int r = ob3.applyAsInt(10);
		System.out.printf("r: %d\n",r);
		
		
		//Predicate 인터페이스
		//Generic으로 1개를 받아서 boolean으로 리턴하는 메소드를 소유한 인터페이스 filtering을 위한 인터페이스
		
		//Student객체를 받아서 score가 80이상인지 확인 
		Predicate <Student> ob4 = (Student student) -> {
			return student.getScore() >= 80;
		};
		System.out.printf("%b\n", ob4.test(new Student(1,"문정원", 89)));
	}

}
