package com.example.tasks.rest.model;

import com.example.tasks.persistence.TaskStatuses;
import com.example.tasks.persistence.model.User;

public class TaskResponseModel {

	private Long id;
	private String title;
	private TaskStatuses status;
	private User user;

	public TaskResponseModel(Long id, String title, TaskStatuses status, User user) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.user = user;
	}

	public TaskResponseModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskStatuses getStatus() {
		return status;
	}

	public void setStatus(TaskStatuses status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
