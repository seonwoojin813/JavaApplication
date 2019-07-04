import java.util.Scanner;

public class InsertSortPractice {

	public static void main(String[] args) {
		int [] ar = {7, 4, 2, 5, 6};
		//두번째부터 끝까지
		
		for(int i=0; i<ar.length; i=i+1) {
			//두번째 데이터부터 복사
			int st = ar[i];
			//st와 그 앞의 데이터들을 비교해서 앞의 데이터가 크면 그 데이터를 그 다음 장소로 복사 앞의 데이터가 작으면 반복문 종
			int aux = i-1;
			
			while(aux >=0 && st < ar[aux]) {
				ar[aux+1] = ar[aux];
				aux = aux-1;
			}
			ar[aux+1] = st;
		}
		//데이터 출력
		for(int x : ar) {
			System.out.printf("%d\n",  x);
		}
		
	}

}
