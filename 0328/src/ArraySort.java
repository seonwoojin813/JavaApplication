
public class ArraySort {

	public static void main(String[] args) {
		//Int [] ar = {10,7,9,87,65} => {9,87,7,65,10} 98776510
		
		int [] ar = {10, 7, 9, 87, 65};
		for(int i=0; i<ar.length; i=i+1) {
			for(int j=1; j<ar.length-i; j=j+1) {
				if(ar[i] > ar[j]);
				int temp = ar[i];
				ar[i] = ar[j];
				ar[j]= temp;
			}
		}for(int imsi : ar) {
			System.out.printf("%d ", imsi);
		}

	}

}
