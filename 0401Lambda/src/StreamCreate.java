import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamCreate {

	public static void main(String[] args) {
		//문자열 배열 만들기
		String [] ar = {"java","javascript","swift","c++","java","Python","Java","Kotlin"};
		Stream<String> stream1 = Arrays.stream(ar);
		stream1.forEach(System.out::println);
		
		System.out.printf("===============================\n");
		
		//ar에서 1개의 데이터를 건너뛰고 2개의 데이터를 가져오기 
		Stream<String> stream4 = Arrays.stream(ar);
		stream4.skip(1).limit(2).forEach(System.out::println);
		
		System.out.printf("===============================\n");
		
		//ar에서 중복을 제거한 후 데이터 가져오기:distinct()-대소문자를 구분하지 못함 
		Stream<String> stream5 = Arrays.stream(ar);
		stream5.distinct().forEach(System.out::println);
		
		System.out.printf("===============================\n");
		
		//ar에서 중복을 제거한 후 데이터 가져오기: equals메소드 이용 - 대소문자를 구분
		Stream<String> stream6 = Arrays.stream(ar);
		
				
		System.out.printf("===============================\n");
		
		
		Stream<String> stream7 = Arrays.stream(ar);
		stream7.filter((language) -> {
			return language.length() >= 5;
		}).forEach(System.out::println);
		
		System.out.printf("===============================\n");
		
		//데이터를 2개씩 연속해서 출력		
		Stream<String> stream8 = null;
		int size = ar.length;
		int cnt = 2; //한 페이지에 출력할 데이터 개수 
		int page = 0; //현재 출력 중인 페이지 번호 
		while(true) {
			stream1 = Arrays.stream(ar);
			//페이지 번호에 해당하는 데이터 개수만큼 건너 뛰면서 cnt 만큼 출력 
			stream1.skip(page * 2).limit(cnt).forEach(System.out::println);
			//페이지 번호 +1 씩 증
			page = page +1;
			//페이지 번호가 전체 페이지 개수보다 많으면 종료 
			if(page >= size/cnt) {
				break;
			}
		}
		
		System.out.printf("===============================\n");
		
		//문자열 List 생성
		List<String> list = new ArrayList<>();
		list.add("Oracle");
		list.add("MySQL");
		list.add("MongoDB");
		Stream <String> stream2 = list.stream();
		stream2.forEach(System.out::println);
		
		System.out.printf("===============================\n");
		
		//정수 범위를 가지고 만들기
		IntStream stream3 = IntStream.range(10, 20);
		stream3.forEach(System.out::println);
		
		System.out.printf("===============================\n");

	}

}
