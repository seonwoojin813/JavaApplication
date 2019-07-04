
//DTO 클래스: 여러 개의 컬럼을 묶어서 하나로 표현하기 위한 클래스
//VO(Variable Object) 또는 Domain 클래스라고도 합니다.
public class Student {
	//번호, 이름, 점수를 저장 
	private int num;
	private String name;
	private int score;
	
	public Student() {
		super();
	}

	public Student(int num, String name, int score) {
		super();
		this.num = num;
		this.name = name;
		this.score = score;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", score=" + score + "]";
	}
	
	
}
