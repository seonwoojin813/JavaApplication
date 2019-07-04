public class GuideVO {

	private int num;
	private String sort;
	private String name;
	private String loc;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	@Override
	public String toString() {
		return "GuideVO [num=" + num + ", sort=" + sort + ", name=" + name + ", loc=" + loc + "]";
	}
	
	
}
