package com.example.tasks.rest.model;

import com.example.tasks.persistence.TaskStatuses;

public class TaskRequestModel {

	private String title;
	private TaskStatuses status;
	private Long userId;

	public TaskRequestModel(String title, TaskStatuses status, Long userId) {
		this.title = title;
		this.status = status;
		this.userId = userId;
	}

	public TaskRequestModel() {
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
