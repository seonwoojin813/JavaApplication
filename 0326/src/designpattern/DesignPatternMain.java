package designpattern;

public class DesignPatternMain {

	public static void main(String[] args) {
		
		/*
		Service service = new ServiceImpl ();
		service.method();
		*/
		
		Target target = new NewSystem();
		target.process();
	}

}
