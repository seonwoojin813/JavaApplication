import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectMain {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "kim", "m", 98));
		list.add(new Student(2, "bae", "f", 99));
		list.add(new Student(3, "cha", "m", 94));
		list.add(new Student(4, "van", "m", 87));
		list.add(new Student(5, "ru", "m", 93));
		list.add(new Student(6, "sha", "f", 78));
		list.add(new Student(7, "yuna", "f", 92));
		
		//성별이 남자인 데이터와 여자인 데이터를 분류해서 저장
		List <Student> manList = list.stream()
		.filter(student -> {return student.getGender().equals("m");})
		.collect(Collectors.toList());
		
		List <Student> womanList = list.stream()
		.filter(student -> {return student.getGender().equals("f");})
		.collect(Collectors.toList());
		
		//데이터 출력
		manList.parallelStream().forEach(System.out::println);
		System.out.printf("=============================================\n");
		womanList.parallelStream().forEach(System.out::println);
		System.out.printf("=============================================\n");
		
		//데이터 분석 라이브러리나 데이터베이스를 제작할 때는 위처럼 클라이언트의 요청을 복제를 해서 데이터를 넘겨줍니다.
		
		//Map은 이름과 함께 데이터를 저장하는 자료구조
		//Map을 이용해서 List 구조를 만드는 경우가 있습니다.
		//R 같은 언어에서는 Map을 List라고 합니다.
		//키를 데이터의 인덱스로 놓고 value를 데이터로 배치합니다.
		//Value를 Object 타입으로 놓지 않고 하나의 일반 자료형으로 설정
		//toMap을 대입할 때 첫번째 매개변수로 키가 될 데이터(메소드 이름)를 두번째 매개변수로 값이 될 데이터(원본 데이터 그대로)를 설
		
		Map<String, Student> map = list.parallelStream().collect(
				Collectors.toMap(Student::getName, item -> item));
		//System.out.printf("%s\n",map.get("yuna"));
		
		Set <String> keySet = map.keySet();
		for(String key : keySet) {
			System.out.printf("%s:%s\n",  key, map.get(key));
		}

	}

}
