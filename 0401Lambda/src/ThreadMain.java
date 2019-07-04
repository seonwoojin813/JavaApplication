
public class ThreadMain {

	public static void main(String[] args) {
		//기존의 annoymous 객체를 이용한 스레드의 생성
		Runnable r = new Runnable() {
			//스레드로 수행 할 내용 
			@Override
		public void run() {
				//1부터 10까지 1초 마다 출력하기
				try {
					for(int i=1; i<=10; i=i+1) {
						Thread.sleep(1000);
					System.out.printf("i: %d\n", i);
					}
				}catch(Exception e) {
					System.out.printf("%s\n", e.getMessage());
					e.printStackTrace();
				}
				
			}
		};
		Thread th = new Thread(r);
		th.start();
		
		//스레드를 람다 한수를 이용해서 작성 
		//람다 함수를 이용하면 필요한 내용만 구현하지만 이전의 anonymous를 이용하면 최상위 클래스의 메소드도 구현된 상태
		//오랜 시간 동안 사용하는 애플리케이션이나 많은 양의 처리를 수행하는 애플리케이션에서는 람다를 선호
		th = new Thread (() ->  {
			//1부터 10까지 1초마다 출력하기
			try {
				for(int i=1; i<=10; i=i+1) {
					Thread.sleep(1000);
				System.out.printf("i: %d\n", i);
				}
			}catch(Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
				}
		});
		th.start();
	}

}
