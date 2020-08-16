package com.example.tasks.rest.model;

public class UserRequestModel {

	private String name;
	private String surname;

	public UserRequestModel() {
	}

	public UserRequestModel(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
