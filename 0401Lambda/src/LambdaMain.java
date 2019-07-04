import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaMain {

	public static void main(String[] args) {
		//Student 클래스의 인스턴스 List를 생성
		List<Student> list = new ArrayList<>();
		list.add(new Student(1,"문정원", 98));
		list.add(new Student(2,"배유나 ", 99));
		list.add(new Student(3,"박정아 ", 93));
		list.add(new Student(4,"김나희",88));
		list.add(new Student(5,"양효진 ", 89));
		list.add(new Student(6, "고예림 ", 76));
		list.add(new Student(7,"엘레나 ", 96));
		list.add(new Student(8, "강소휘 ", 94));
		list.add(new Student(9, "이소영 ", 95));
		
		//위의 데이터를 이름의 오름차순으로 정렬
		//데이터를 정렬하고자하면 객체를 비교할 수 있는 메소드가 필요
		//1.7이전까지에서는 함수형 프로그래밍이 지원되지 않았기 때문에 객체를 비교할 수 있는 메소드를 객체에 저장해서 전달
		/*
		1.7이전까지의 버전
		실제로 필요한 것은 데이터를 비교하기 위한 메소드 1개인데 메소드 단위로 대입이 안되기 때문에 new를 이용해서 인스턴스를 생성하고 메소드를 구현해서 대
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {
				return o1.getName().compareTo(o2.getName());
			}			
		});
		*/
		
		//1.7에서부터는 메소드가 한 개인 인터페이스에 한해서 람다 표현식으로 메소드만 대입이 가능합니
		Collections.sort(list, (Student o1, Student o2) -> {
			return o1.getName().compareTo(o2.getName());
		});
		
		/*
		//데이터를 하나씩 출력해서 정렬 확인
		for(Student student : list) {
			System.out.printf("%s\n", student);
		}
		*/
		
		//forEach는 매개변수가 1개이고 리턴타입이 void 인 메소드를 매개변수로 받아서 리스트 내의 모든 데이터들을 메소드에 대입한 후 호출해서 실행합니다.
		//forEach 메소드의 매개변수가 Consumer 인터페이스 타입입니다.
		//Consumer 인터페이스는 void accept(T t) 메소드 1개만 소유
		//Consumer 인터페이스를 implements한 객체를 만들어서 대입가능
		//메소드가 1개만 있으므로 람다식을 작성해서 대입가능
		//accept와 동일한 원형의 메소드를 대입가능
		list.forEach(System.out::println);
		
		
		

	}

}
