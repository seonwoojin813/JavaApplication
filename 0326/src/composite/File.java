package composite;

public class File implements Entry {
	//파일의 이름을 저장할 변수
	private String name;
	
	//생성자에서 이름을 넘겨받아서 저장 - 주입(Injection)
	//생성자나 setter 메소드를 이용해서 외부의 데이터를 받아 자신의 데이터를 설정하는 것
	
	public File(String name) {
		this.name = name;
	}
	
	//파일을 추가하는 메소드 - 파일은 이 메소드를 수행 못함 
	@Override
	public void add(Entry entry) {
		//강제로 예외 발생 - 메소드를 지원하지 않는 예외 
		throw new UnsupportedOperationException();

	}

	@Override
	public void remove() {
		System.out.printf("remove %s\n", name);
	}

	@Override
	public void rename(String name) {
		this.name = name;

	}

}
