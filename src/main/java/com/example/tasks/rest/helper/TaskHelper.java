package com.example.tasks.rest.helper;

import com.example.tasks.persistence.model.Task;
import com.example.tasks.persistence.repository.UserRepository;
import com.example.tasks.rest.model.TaskRequestModel;

public class TaskHelper {

	public static void updateTask(TaskRequestModel updateTask, Task task, UserRepository userRepository) {

		if (updateTask.getTitle() == null) task.setTitle(task.getTitle());
		else task.setTitle(updateTask.getTitle());

		if (updateTask.getStatus() == null) task.setStatus(task.getStatus());
		else task.setStatus(updateTask.getStatus());

		if (updateTask.getUserId() == null) {
			task.setUser(task.getUser());
		} else {
			try {
				task.setUser(userRepository.findById(updateTask.getUserId()).get());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}
