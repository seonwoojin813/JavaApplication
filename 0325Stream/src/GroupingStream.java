import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GroupingStream {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "kim", "m", 98));
		list.add(new Student(2, "bae", "f", 99));
		list.add(new Student(3, "cha", "m", 94));
		list.add(new Student(4, "van", "m", 87));
		list.add(new Student(5, "ru", "m", 93));
		list.add(new Student(6, "sha", "f", 78));
		list.add(new Student(7, "yuna", "f", 92));
		
		//list의 데이터를 gender 별로 그룹화 
		Map <String, List<Student>> map = list.stream().collect(
				Collectors.groupingBy(Student::getGender));
		System.out.printf("%s\n", map.get("m"));
		System.out.printf("%s\n", map.get("f"));
		
		//list의 데이터에서 gender 별로 score의 평균을 구하기 
		Map <String,Double > result = list.stream().collect(
				Collectors.groupingBy(Student::getGender,
				Collectors.averagingDouble(Student::getScore)));
		
		Set <String> keySet = result.keySet();
		for(String key : keySet) {
			System.out.printf("%s: %/2f\n", key, result.get(key));
		}
		

	}

}
