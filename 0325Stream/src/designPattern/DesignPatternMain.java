package designPattern;

import facotry.DB;
import facotry.DBFactory;

public class DesignPatternMain {

	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		//2개의 해시코드 비교
		System.out.printf("s1: %s\n", System.identityHashCode(s1));
		System.out.printf("s2: %s\n", System.identityHashCode(s2));
		
		Runtime r1 = Runtime.getRuntime();
		Runtime r2 = Runtime.getRuntime();
		System.out.printf("r1: %s\n", System.identityHashCode(r1));
		System.out.printf("r2: %s\n", System.identityHashCode(r2));
		
		System.out.printf("10: %s\n", System.identityHashCode(10));
		System.out.printf("10: %s\n", System.identityHashCode(10));
		
		System.out.printf("Java: %s\n", System.identityHashCode("Java"));
		System.out.printf("Java: %s\n", System.identityHashCode("Java"));
		
		DB db = DBFactory.create("Oracle");
		System.out.println(db);

	}

}
