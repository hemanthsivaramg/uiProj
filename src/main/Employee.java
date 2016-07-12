package main;

public class Employee {
private int emp_id;
private String emp_name;
private int emp_mgr_id;



public Employee(int emp_id, String emp_name, int emp_mgr_id) {
	super();
	this.emp_id = emp_id;
	this.emp_name = emp_name;
	this.emp_mgr_id = emp_mgr_id;
}
public int getEmp_id() {
	return emp_id;
}
public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}

public String getEmp_name() {
	return emp_name;
}


public void setEmp_name(String emp_name) {
	this.emp_name = emp_name;
}


public int getEmp_mgr_id() {
	return emp_mgr_id;
}
public void setEmp_mgr_id(int emp_mgr_id) {
	this.emp_mgr_id = emp_mgr_id;
}
@Override
public String toString() {
	return "Employee [emp_id=" + emp_id + ", emp_name=" + emp_name + ", emp_mgr_id=" + emp_mgr_id + ", getEmp_id()="
			+ getEmp_id() + ", getEmp_name()=" + getEmp_name() + ", getEmp_mgr_id()=" + getEmp_mgr_id()
			+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}


}
