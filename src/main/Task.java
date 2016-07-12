package main;

import java.time.LocalDate;

public class Task {

	private int task_id;
	private String task_name; 
	private LocalDate task_assigned_date;
	private LocalDate task_completed_date;
	private Double task_hours_worked;
	private int task_FK_employee_id;
	
	public Task(int task_id, String task_name, LocalDate task_assigned_date, LocalDate task_completed_date,
			double task_hours_worked, int task_FK_employee_id) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.task_assigned_date = task_assigned_date;
		this.task_completed_date = task_completed_date;
		this.task_hours_worked = task_hours_worked;
		this.task_FK_employee_id = task_FK_employee_id;
	}

	public Task(int task_id, String task_name, LocalDate task_assigned_date, int task_FK_employee_id) {
		super();
		this.task_id = task_id;
		this.task_name = task_name;
		this.task_assigned_date = task_assigned_date;
		this.task_hours_worked = 0.0;
		this.task_FK_employee_id = task_FK_employee_id;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public LocalDate getTask_assigned_date() {
		return task_assigned_date;
	}

	public void setTask_assigned_date(LocalDate task_assigned_date) {
		this.task_assigned_date = task_assigned_date;
	}

	public LocalDate getTask_completed_date() {
		return task_completed_date;
	}

	public void setTask_completed_date(LocalDate task_completed_date) {
		this.task_completed_date = task_completed_date;
	}

	public double getTask_hours_worked() {
		return task_hours_worked;
	}

	public void setTask_hours_worked(double task_hours_worked) {
		this.task_hours_worked = task_hours_worked;
	}

	public int getTask_FK_employee_id() {
		return task_FK_employee_id;
	}

	public void setTask_FK_employee_id(int task_FK_employee_id) {
		this.task_FK_employee_id = task_FK_employee_id;
	}

	@Override
	public String toString() {
		return "Task1 [task_id=" + task_id + ", task_name=" + task_name + ", task_assigned_date=" + task_assigned_date
				+ ", task_completed_date=" + task_completed_date + ", task_hours_worked=" + task_hours_worked
				+ ", task_FK_employee_id=" + task_FK_employee_id + ", getTask_id()=" + getTask_id()
				+ ", getTask_name()=" + getTask_name() + ", getTask_assigned_date()=" + getTask_assigned_date()
				+ ", getTask_completed_date()=" + getTask_completed_date() + ", getTask_hours_worked()="
				+ getTask_hours_worked() + ", getTask_FK_employee_id()=" + getTask_FK_employee_id() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
