
public class Student {

	// 번호, 이름, 성별, 점수 를 저장할 수 있는 클래스
	// clone 메소드의 구현: Stream을 사용하면 clone을 구현할 필요가 없습니다.
	// compareTo 메소드의 구현: sort할 때 대입하면 됩니다.
	// equals(동일성 여부 확인): 메소드의 구현
	// Serializable의 구현: 파일이나 네트워크에 객체 단위 전송을 위해서 구현
	// 안드로이드에서 Activity끼리 데이터를 주고 받는 것도 네트워크 전송으로 간

	private int num;
	private String name;
	private String gender;
	private int score;

	public Student() {
		super();
	}

	public Student(int num, String name, String gender, int score) {
		super();
		this.num = num;
		this.name = name;
		this.gender = gender;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		//비교하는 객체와 내가 동일한 해시코드를 갖는다면 비교할 필요가 없이 동일한 객
		if (this == obj)
			return true;
		//비교 대상이 null이면 비교할 필요없이 다른 객체
		//이 구문이 없으면 비교 대상이 null일 때 NullPointerException 발
		if (obj == null)
			return false;
		//2개 객체의 클래스가 다르면 비교할 필요없이 다른 객
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		//위의 조건들을 만족하지 않으면 num을 비교해서 일치하면 동일한 객체 그렇지 않으면 다른 객
		if (num != other.num)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", gender=" + gender + ", score=" + score + "]";
	}

}
