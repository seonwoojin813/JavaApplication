import java.util.Scanner;

public class CharTriangle {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 삼각형의 높이가 될 정수를 입력받기
		System.out.println("100이하의 정수를 입력하세요\n");
		int n = sc.nextInt();
		char ch = 'A';
		
		if (n > 100) {
			// 100이상의 정수를 입력받을 경우 다시 입력
			System.out.printf("100이하의 정수를 입력하세요\n");
			n = sc.nextInt();
			
		}
		for (int i = 0; i < n; i = i + 1) {
			int plus = 0;
			for (int j = 0; j < n - i; j = j + 1) {
				System.out.printf(" ");
				// 앞부분 공백 삼각형을 만들기
			}
			for(int j=0; j<i+1; j=j+1) {
				System.out.printf("%c", ch+((i+plus)%26));
				plus = n-(j+1)+plus;
			}
			System.out.printf("\n");
		}
	}
}
