package com.nt.workforcemgmt.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nt.workforcemgmt.dto.CommentRequest;
import com.nt.workforcemgmt.dto.CreateTaskRequest;
import com.nt.workforcemgmt.model.ActivityLog;
import com.nt.workforcemgmt.model.Comment;
import com.nt.workforcemgmt.model.Task;
import com.nt.workforcemgmt.model.TaskPriority;
import com.nt.workforcemgmt.model.TaskStatus;

@Service
public class TaskService {
	private final Map<Long, Task> taskMap = new HashMap<Long, Task>();
	private Long count = 1L;

	public Task createTask(CreateTaskRequest req) {
		Task task = new Task();
		task.setId(count++);
		task.setTitle(req.getTitle());
		task.setAssignedTo(req.getAssignedTo());
		task.setStartDate(req.getStartDate());
		task.setDueDate(req.getDueDate());
		task.getActivityLogs().add(new ActivityLog("Task created", LocalDateTime.now()));
		taskMap.put(task.getId(), task);
		System.err.println("Added task is :: " + task);
		return task;
	}

	public List<Task> getAll() {
		List<Task> list = taskMap.values().stream().collect(Collectors.toList());
		System.err.println("All task list :: " + list);
		return list;
	}

	public List<Task> getTasks(LocalDate start, LocalDate end) {
		List<Task> list = taskMap.values().stream().filter(task -> task.getStatus() != TaskStatus.CANCELLED)
				.filter(task -> (task.getStartDate().compareTo(start) >= 0 && task.getStartDate().compareTo(end) <= 0)
						|| (task.getStartDate().isBefore(start) && task.getStatus() == TaskStatus.ACTIVE))
				.collect(Collectors.toList());

		System.err.println("Get task b/w tow dates :: " + list);

		return list;
	}

	public Task getTask(Long id) {
		Task task = taskMap.get(id);
		if (task == null) {
			throw new RuntimeException("Task with ID :: " + id + " not found");
		}
		System.err.println("Get task by ID wise :: " + task);
		return task;
	}

	public void addComment(Long id, CommentRequest request) {
		Task task = taskMap.get(id);
		System.err.println("Added commnet task Id  :: " + task.getId() + " comment is :: " + request);
		task.getComments().add(new Comment(request.getAuthor(), request.getMessage(), LocalDateTime.now()));
		task.getActivityLogs().add(new ActivityLog("Comment added by :: " + request.getAuthor(), LocalDateTime.now()));
	}

	public void updatePriority(Long id, TaskPriority priority) {
		Task task = taskMap.get(id);
		System.out.println("Task ID :: " + task.getId() + " update priority :: " + priority);
		task.setPriority(priority);
		task.getActivityLogs().add(new ActivityLog("Priority changed to  :: " + priority, LocalDateTime.now()));
	}

	public List<Task> getByPriority(TaskPriority priority) {
		List<Task> list = taskMap.values().stream().filter(task -> task.getPriority() == priority)
				.collect(Collectors.toList());
		System.err.println("Get task by priority wise :: " + list);
		return list;
	}

	public void reassignTask(Long id, String newAssign) {
		Task oldTask = taskMap.get(id);
		if (oldTask == null) {
			throw new NoSuchElementException("Task with ID :: " + id + " not found");
		}

		oldTask.setStatus(TaskStatus.CANCELLED);
		oldTask.getActivityLogs().add(new ActivityLog("Task reassigned to :: " + newAssign, LocalDateTime.now()));

		Task newTask = new Task();
		newTask.setId(count++);
		newTask.setTitle(oldTask.getTitle());
		newTask.setAssignedTo(newAssign);
		newTask.setStartDate(LocalDate.now());
		newTask.setDueDate(oldTask.getDueDate());
		newTask.getActivityLogs().add(new ActivityLog(
				"Task reassigned from :: " + oldTask.getAssignedTo() + " to :: " + newAssign, LocalDateTime.now()));
		taskMap.put(newTask.getId(), newTask);

		System.out.println("Reassigined task is :: " + newTask);
	}
}