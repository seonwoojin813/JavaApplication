import org.junit.Test;

import junit.framework.TestCase;

//TestCase로 부터 상속받은 클래스의 메소드는 Main이 없어도 실행 가능 
//일반 클래스의 경우는 메소드 위에 @Test를 추가하면 됩니다.

public class TestSample extends TestCase {
	
	@Test
	public void testMethod() {
		System.out.printf("test method\n");
	}
	@Test
	public void secondMethod() {
		System.out.printf("Second Method\n");
	}
}

