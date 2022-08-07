package com.example.spring_batch.model;

import lombok.AllArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;


@Entity

public class UserBatch {

	@Id
    private Integer id;
    private String name;
    private String dept;
    private Integer salary;
    private Date time;

	public UserBatch() {
	}

	public UserBatch(Integer id, String name, String dept, Integer salary, Date time) {
		super();
		this.id = id;
		this.name = name;
		this.dept = dept;
		this.salary = salary;
		this.time = time;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
    
    
}