package com.example.tasks.rest;

import com.example.tasks.persistence.model.Task;
import com.example.tasks.persistence.model.User;
import com.example.tasks.persistence.repository.TaskRepository;
import com.example.tasks.persistence.repository.UserRepository;
import com.example.tasks.rest.converter.TaskConverter;
import com.example.tasks.rest.helper.TaskHelper;
import com.example.tasks.rest.model.TaskRequestModel;
import com.example.tasks.rest.model.TaskResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.tasks.persistence.TaskStatuses.*;

@RestController
public class TaskController {

	private final UserRepository userRepository;
	private final TaskRepository taskRepository;

	public TaskController(UserRepository userRepository, TaskRepository taskRepository) {
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
	}

	@PostMapping(value = "tasks")
	public ResponseEntity<TaskResponseModel> create(@RequestBody TaskRequestModel createRequest) {
		User user = null;
		try {
			user = userRepository.findById(createRequest.getUserId()).get();
		} catch (Exception e) {
			System.out.println("User with given Id does not exist. Please use update to add user to this task");
		}
		return ResponseEntity.ok(TaskConverter.convertToResponseModel(taskRepository.save(new Task(createRequest.getTitle(), NEW))));
	}

	@PutMapping(value = "tasks/{id}")
	public ResponseEntity<TaskResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody TaskRequestModel updateRequst) {
		Task task = taskRepository.findById(id).get();
		TaskHelper.updateTask(updateRequst, task, userRepository);
		return ResponseEntity.ok(TaskConverter.convertToResponseModel(taskRepository.save(task)));
	}

	@PutMapping(value = "tasks/bulkAssign")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<List<TaskResponseModel>> assignTasks(@RequestBody Map<String, List<Long>> userTask) {

		//		{"UserId": [1],
		//		"TaskIds":[1,2,3]}

		User userToAssign = userRepository.findById(userTask.get("UserId").get(0)).get();
		List<Task> taskIds = taskRepository.findAllById(userTask.get("TaskIds"));
		taskIds.forEach(task -> task.setUser(userToAssign));
		List<Task> savedTasks = taskRepository.saveAll(taskIds);
		return ResponseEntity.ok(TaskConverter.convertToListOfResponseModel(savedTasks));

	}

	@GetMapping(value = "tasks/{id}")
	public ResponseEntity<TaskResponseModel> getById(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok(TaskConverter.convertToResponseModel(taskRepository.findById(id).get()));
	}

	@GetMapping(value = "tasks")
	public ResponseEntity<List<TaskResponseModel>> getAll() {
		return ResponseEntity.ok(taskRepository.findAll().stream().map(TaskConverter::convertToResponseModel).collect(Collectors.toList()));
	}

	@DeleteMapping(value = "tasks/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") Long id) {
		taskRepository.deleteById(id);
		return ResponseEntity.ok(!taskRepository.existsById(id));
	}


}
