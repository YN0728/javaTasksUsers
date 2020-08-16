package com.example.tasks.persistence.model;

import com.example.tasks.persistence.TaskStatuses;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
public class Task extends AbstractBaseEntity {

	private String title;
	@Enumerated(EnumType.STRING)
	private TaskStatuses status;
	@ManyToOne
	private User user;

	public Task() {
	}

	public Task(String title, TaskStatuses status, User user) {
		this.title = title;
		this.status = status;
		this.user = user;
	}

	public Task(String title, TaskStatuses status) {
		this.title = title;
		this.status = status;
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
