import java.util.Arrays;

public class MiraeAsset {

	public static void main(String[] args) {
		
		int [] ar = {1,3,2,5,4};
		int money = 9;
		int result =0;
		int sum = 0;
		Arrays.sort(ar);
		for(int i=0; i<ar.length; i=i+1) {
			sum = sum+ar[i];
			if(sum > money) {
				result = i;
				break;
			}
		}
		System.out.printf("%d\n", result);
	}
}
