package com.nt.workforcemgmt.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.workforcemgmt.dto.CommentRequest;
import com.nt.workforcemgmt.dto.CreateTaskRequest;
import com.nt.workforcemgmt.dto.PriorityUpdateRequest;
import com.nt.workforcemgmt.dto.TaskDto;
import com.nt.workforcemgmt.mapper.TaskMapper;
import com.nt.workforcemgmt.model.TaskPriority;
import com.nt.workforcemgmt.service.TaskService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Workflow Management APIs :", description = "--> { CRUD operation nad task workflow... }")
public class TaskController {
	
	
	//Swagger Doc API :	http://localhost:4041/swagger-ui.html
		

	private final TaskService taskService;

	//for create task
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CreateTaskRequest request) {
		TaskDto task = TaskMapper.toDto(taskService.createTask(request));
		return ResponseEntity.ok(task);
	}

	//get all tasks
	@GetMapping("/all")
	public ResponseEntity<List<TaskDto>> getAll() {
		List<TaskDto> list = taskService.getAll().stream().map(TaskMapper::toDto).toList();
		return ResponseEntity.ok(list);
	}

	//get tasks between two dates
	@GetMapping
	public ResponseEntity<List<TaskDto>> getTasks(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
			@RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
		List<TaskDto> list = taskService.getTasks(start, end).stream().map(TaskMapper::toDto).toList();
		return ResponseEntity.ok(list);
	}

	//get task by id
	@GetMapping("/{id}")
	public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id) {
		return ResponseEntity.ok(TaskMapper.toDto(taskService.getTask(id)));
	}

	//add comment to particular task
	@PostMapping("/{id}/comments")
	public ResponseEntity<String> addComment(@PathVariable("id") Long id, @RequestBody CommentRequest request) {
		taskService.addComment(id, request);
		return ResponseEntity.ok("Comment Added...");

	}

	//update the priority
	@PutMapping("/{id}/priority")
	public ResponseEntity<String> updatePriority(@PathVariable("id") Long id,
			@RequestBody PriorityUpdateRequest request) {
		taskService.updatePriority(id, request.getPriority());
		return ResponseEntity.ok("Priority is Updated...");
	}

	//get task by priority level wise
	@GetMapping("/priority/{level}")
	public List<TaskDto> getByPriority(@PathVariable("level") TaskPriority level) {
		return taskService.getByPriority(level).stream().map(TaskMapper::toDto).toList();
	}

	//update or reassign the task
	@PutMapping("/{id}/reassign")
	public ResponseEntity<?> reassign(@PathVariable("id") Long id, @RequestParam("newAssignee") String newAssignee) {
		try {
			taskService.reassignTask(id, newAssignee);
			return ResponseEntity.ok("Task reassigned...");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}

