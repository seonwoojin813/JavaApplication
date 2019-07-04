import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class StreamReduce {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "kim", "m", 98));
		list.add(new Student(2, "bae", "f", 99));
		list.add(new Student(3, "cha", "m", 94));
		list.add(new Student(4, "van", "m", 87));
		list.add(new Student(5, "ru", "m", 93));
		list.add(new Student(6, "sha", "f", 78));
		list.add(new Student(7, "yuna", "f", 92));

		// list.stream().filter(data -> {return
		// data.getGender().equals("m");}).forEach(System.out::println);
		
		/*
		// 성별이 남자인 데이터의 name만 출력
		list.stream().filter(data -> {return data.getGender().equals("m");}).map((Student data) -> {return data.getName();})
		.forEach(System.out::println);
		
		
		// 성별이 남자인 데이터의 score만 출력
		list.stream().filter(data -> {
			return data.getGender().equals("m");
		}).mapToInt(data -> {
			return data.getScore();
		}).forEach(System.out::println);
		
		List < Integer > intList = new ArrayList <> ();
		intList.add(30);
		intList.add(47);
		intList.add(32);
		
		//intList.stream().sorted().forEach(System.out::println);
		
		//intList.stream().sorted((Integer a, Integer b) -> {return a-b;}).forEach(System.out::println);
		
		//list.stream().sorted((Student a, Student b) -> {return a.getName().compareTo(b.getName());}).forEach(System.out::println);
		
		list.stream().filter(data ->{return data.getGender().contentEquals("f");})
		.sorted((Student a, Student b) -> {return a.getName().compareTo(b.getName());}).skip(1).forEach(System.out::println);
		//중간 연산의 순서에 상관없이 결과는 동일하게 나올 수는 있지만 순서에 따라 작업 시간은 달라지게 됩니다.
		*/
		
		//list의 모든 데이터가 name이 3자 이상인지 확인
		//predicate: 매개변수가 1개이고 boolean을 리턴하는 메소드를 소유한 인터페이스 
		boolean result = list.stream().allMatch((Student student) -> {
			return student.getName().length() < 5;
		});
		
		//System.out.printf("result: %b\n", result);
		
		//데이터가 Optional일 때는 get을 이용해서 데이터를 원래 자료형으로 변환한 후 사용해야 합니다.
		Optional <Student> t = list.parallelStream().findFirst();
		Student student = t.get();

		//데이터 개수
		long count = list.stream().count();
		System.out.printf("count: %d\n", count);
		
		//Student는 합계를 구할 수 없습니다.
		//Student 안에서 합계를 구할 수 있는 데이터로 변환을 해주어야 합니다.
		long sum = list.stream()
				.mapToInt((Student st) -> {return st.getScore();})
				.sum();
		System.out.printf("sum: %d\n", sum);
		
		//성별이 여자인 학생의 score 합계 구하기
		long fsum = list.stream()
				.filter((Student st) -> {return st.getGender().equals("f");})
				.mapToInt((Student st) -> {return st.getScore();})
				.sum();
		System.out.printf("fsum: %d\n", fsum);
		
		
		//성별이 여자인 학생의 score 평균 구하기
		OptionalDouble avg = list.stream()
				.filter((Student st) -> {return st.getGender().equals("f");})
				.mapToInt((Student st) -> {return st.getScore();})
				.average();
		System.out.printf("averge: %d\n", avg.getAsDouble());
		
		//평균, 최대값, 최소값은 Optional 타입으로 리턴되므로 내부에 저장된 값을 가져와서 사용 
		//kotlin이나 swift에서도 동일
		//Optional은 바로 사용할 수 없고 원래의 자료형 데이터를 가져와서 사용해야 합니다.
		
		
		//list에 있는 여자인 데이터의 score의 곱을 계산
		int r = list.parallelStream()
		.filter((Student st) -> {return st.getGender().equals("f");})
		.mapToInt((Student st) -> {return st.getScore();})
		.reduce(1,(st1,st2) -> st1 * st2);
		System.out.printf("r: %d\n", r);
		
		

	}

}
