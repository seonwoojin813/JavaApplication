package composite;

public class Composite {

	public static void main(String[] args) {
		File f1 = new File ("file1");
		File f2 = new File ("file2");
		File f3 = new File ("file3");
		
		Directory d1 = new Directory("sub directory");
		d1.add(f1);
		d1.add(f2);
		
		Directory d2 = new Directory("main directory");
		d2.add(f3);
		d2.add(d1);
		
		d2.remove();
		
		//1개를 가지고 처리할 수 있는 클래스와 n개를 가지고 처리할 수 있는 클래스를 동일한 인터페이스를 이용해서
		//구현하는 디자인 패턴을 Composite 디자인 패턴이라고 합니다.
	}

}
