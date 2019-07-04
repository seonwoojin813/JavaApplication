import java.sql.Date;

public class Product {

	private int num;
	private String name;
	private String sort;
	private String manager;
	private Date enter;
	
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
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Date getEnter() {
		return enter;
	}
	public void setEnter(Date enter) {
		this.enter = enter;
	}
	
	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", sort=" + sort + ", manager=" + manager + ", enter=" + enter
				+ "]";
	}
	
	
	
}
