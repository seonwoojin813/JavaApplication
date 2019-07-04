package designpattern;

public class NewSystem extends OldSystem implements Target {

	@Override
	public void process() {
		oldMethod();
		
	}

}
