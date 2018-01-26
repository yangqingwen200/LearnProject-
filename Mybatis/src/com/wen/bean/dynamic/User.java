package com.wen.bean.dynamic;

public class User {

	private Integer id;
	private String name;
	private Integer age;
	private String lastName;

	public User() {
		super();
	}

	public User(Integer id, String name, Integer age, String lastName) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.lastName = lastName;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", lastName=" + lastName + "]";
	}
	
}
