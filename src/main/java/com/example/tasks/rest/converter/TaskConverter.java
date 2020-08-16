package com.example.tasks.rest.converter;

import com.example.tasks.persistence.model.Task;
import com.example.tasks.rest.model.TaskResponseModel;

import java.util.List;
import java.util.stream.Collectors;

public class TaskConverter {
	public static TaskResponseModel convertToResponseModel(Task task) {
		return new TaskResponseModel(task.getId(), task.getTitle(), task.getStatus(), task.getUser());
	}

	public static List<TaskResponseModel> convertToListOfResponseModel(List<Task> tasks) {
		return tasks.stream().map(TaskConverter::convertToResponseModel).collect(Collectors.toList());
	}
}
