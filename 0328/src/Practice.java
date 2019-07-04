import java.util.Scanner;

public class Practice {

	public static void main(String[] args) {
		
		//정수를 하나 입력받아서 2의 지수승인지 판단
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("정수를 입력하세요\n");
		int su = sc.nextInt();
		while(su/2 < 2) {
			su = su - su/2;		
		}if(su%2 == 0) {
			System.out.printf("2의 지수승 입니다.\n");
		}else {
			System.out.printf("2의 지수승이 아닙니다.\n");
		}
		
		
		System.out.printf("정수를 입력하세요\n");
		int num = sc.nextInt();
		if(num%3 == 0 && num%15 != 0) {			
			System.out.printf("F\n");
		}
		else if(num%5 == 0 && num%15 != 0) {
			System.out.printf("B\n");
		}
		else if(num%15 == 0 ) {
			System.out.printf("FB\n");
		}
	}

}

