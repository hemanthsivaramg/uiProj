package main;

public class Manager {
	private int mgr_id;
	private String mgr_name;
	private String mgr_department;
	public Manager(int mgr_id, String mgr_name, String mgr_department) {
		super();
		this.mgr_id = mgr_id;
		this.mgr_name = mgr_name;
		this.mgr_department = mgr_department;
	}
	public int getMgr_id() {
		return mgr_id;
	}
	public void setMgr_id(int mgr_id) {
		this.mgr_id = mgr_id;
	}
	public String getMgr_name() {
		return mgr_name;
	}
	public void setMgr_name(String mgr_name) {
		this.mgr_name = mgr_name;
	}
	public String getMgr_department() {
		return mgr_department;
	}
	public void setMgr_department(String mgr_department) {
		this.mgr_department = mgr_department;
	}
	@Override
	public String toString() {
		return "Manager [mgr_id=" + mgr_id + ", mgr_name=" + mgr_name + ", mgr_department=" + mgr_department
				+ ", getMgr_id()=" + getMgr_id() + ", getMgr_name()=" + getMgr_name() + ", getMgr_department()="
				+ getMgr_department() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
