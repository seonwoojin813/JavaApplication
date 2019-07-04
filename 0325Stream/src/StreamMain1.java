import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamMain1 {

	public static void main(String[] args) {
		//정수 List - < >안의 자료형은 참조형이어야 합니다. int -> Integer
		List<Integer> list = new ArrayList<Integer>();
		list.add(40);
		list.add(87);
		list.add(76);
		list.add(69);
		list.add(54);
		
		//스트림으로 변환
		Stream < Integer > stream = list.stream();
		
		//스트림 전체에 메소드를 적용해서 실행
		//스트림은 원본 소스를 변경하지 않습니다.
		//중간 연산은 여러번 수행할 수 있습니다. skip(long n), distinct(), limit(long n)
		//filter: 데이터를 걸러주는 중간 연산 - 매개변수가 1개이고 boolean을 리턴하는 메소드가 매개변수
		//메소드의 매개변수는 스트림 각각의 데이
		
		//스트림이 사용할 수 있는 모든 데이터를 순서대로 System.out.println()에 매개변수로 대입해서 실행
		//stream.forEach(System.out::println);
		
		//2개의 데이터를 넘기고 1개의 데이터 출
		//stream.skip(2).limit(1).forEach(System.out::println);
		
		//60이상인 데이터만 추출
		//stream.filter(data -> {return data >= 60 ; }).forEach(System.out::println);
		
		//짝수인 데이터만 추출
		//stream.filter(data -> {return data%2 == 0 ; }).forEach(System.out::println);
		
		//공백이 있는 문자열 2개로 배열을 생 
		String [] ar = { "friut apple", "vegetable tomato"};
		
		//내부에 List가 있었다면 data.stream()을 대입하면 List를 전부 분해해서 하나로 만들어 줍니다.
		Arrays.stream(ar)
		.flatMap(data -> Arrays.stream(data.split(" ")))
		.forEach(System.out::println);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
