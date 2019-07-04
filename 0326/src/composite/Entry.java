package composite;

//파일과 디렉토리가 수행해야 할 메소드의 원형을 갖는 인터페이
public interface Entry {
	public void add(Entry entry);
	public void remove();
	public void rename(String name);

}
