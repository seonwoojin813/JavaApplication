package domain;

import java.sql.Date;

public class Sawon {
	 private int num;
	 private String name;
	private String phone;
	private String addr;
	private Date birth;
	private String email;
	
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	
	@Override
	public String toString() {
		return "Sawon [num=" + num + ", name=" + name + ", phone=" + phone + ", addr=" + addr + ", birth=" + birth
				+ ", email=" + email + "]";
	}
	
	
	
}
