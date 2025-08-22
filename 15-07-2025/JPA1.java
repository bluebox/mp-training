package com.prana;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="stu")
public class JPA1 {
	@Id
	@Column(name = "id")
    private int id;
	  
    @Column(name = "name")
	private String name;

	@Override
	public String toString() {
		return "JPA1 [id=" + id + ", name=" + name + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	  
	  
	  
}
