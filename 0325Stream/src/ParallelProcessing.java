import java.util.Arrays;
import java.util.List;

public class ParallelProcessing {
	//정수 1개를 매개변수로 받아서 1초 쉰 후에 출력하는 메소드
	public static void method(int value) {
		try {
			Thread.sleep(1000);
			System.out.println(value);
		}catch(Exception e) {}
	}

	public static void main(String[] args) {
		
		List <Integer> list = Arrays.asList(0,1,2,3,4,5,6,7,8,9);
		
		long start = System.currentTimeMillis();
		
		/*
		//일반적인 List 처리 
		for(Integer i : list) {
			method(i);
		}
		*/
		list.parallelStream().forEach(ParallelProcessing::method);
		long end = System.currentTimeMillis();
		System.out.printf("take time: %d\n",end-start);
		//parallelStream()의 경우 멀티 코어 CPU 환경에서 하나의 작업을 분할해서 각각의 코어가 병렬적으로 처리할 수 있음
		//순차적으로 처리해야 하는 경우에는 병렬 스트림으로 사용하면 안됩니다.
		//다운로드 등 동시에 연산을 사용하는, 순서와는 상관이 없는 작업에 병렬 스트림을 사용하면 효율적입니다.
		
		//현재 컴퓨터의 코어 개수 확인
		System.out.println(Runtime.getRuntime().availableProcessors());

	}

}
