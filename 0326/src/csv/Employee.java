package csv;

import java.util.Date;

//DTO 클래스
//변수명은 컬럼이름과 동일하게 만드는 것이 나중에 기억하기가 쉽습니다.
//기본형보다는 참조형을 사용하는 것이 좋습니다.
//데이터는 null을 가질 수 있지만 기본형은 null을 저장할 수 없기 때문에 잘못하면 NullPointerException이 발생합니다.
public class Employee {
	private String name;
	private Integer age;
	private Date birth;
	private String email;
	private String note;
	
	//직접 입력하는 경우에는 생성자를 추가하는 경우가 많지만 외부에서 데이터를 받아오는 경우는 기본 생성로 충분합니다.
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + ", birth=" + birth + ", email=" + email + ", note=" + note
				+ "]";
	}

	

}
