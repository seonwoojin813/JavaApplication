package composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Directory implements Entry {
	
	private String name;
	
	//디렉토리는 다른 파일이나 디렉토리를 소유할 수 있습니다.
	private List <Entry> list;
	
	public Directory(String name) {
		this.name = name;
		list = new ArrayList<>();
	}

	@Override
	public void add(Entry entry) {
		list.add(entry);

	}

	@Override
	public void remove() {
		//list의 데이터를 이터레이터를 이용해서 전부 삭제
		Iterator<Entry> itr = list.iterator();
		//iterator를 순회하면서 데이터를 가지고 온 후 remove 메소드 호출
		while(itr.hasNext()) {
			Entry entry = itr.next();
			entry.remove();
		}

	}

	@Override
	public void rename(String name) {
		this.name = name;

	}

}
