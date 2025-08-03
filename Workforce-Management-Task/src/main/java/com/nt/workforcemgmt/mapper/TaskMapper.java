package com.nt.workforcemgmt.mapper;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.nt.workforcemgmt.dto.TaskDto;
import com.nt.workforcemgmt.model.ActivityLog;
import com.nt.workforcemgmt.model.Comment;
import com.nt.workforcemgmt.model.Task;

public class TaskMapper {
	public static TaskDto toDto(Task task) {
		if (task == null) {
			return null;
		}

		TaskDto dto = new TaskDto();
		dto.setId(task.getId());
		dto.setTitle(task.getTitle());
		dto.setAssignedTo(task.getAssignedTo());
		dto.setStartDate(task.getStartDate());
		dto.setDueDate(task.getDueDate());
		dto.setStatus(task.getStatus());
		dto.setPriority(task.getPriority());

		dto.setComments(task.getComments() != null
				? task.getComments().stream().map(Comment::getMessage).collect(Collectors.toList())
				: new ArrayList<>());

		dto.setActivityLogs(task.getActivityLogs() != null
				? task.getActivityLogs().stream().map(ActivityLog::getDescription).collect(Collectors.toList())
				: new ArrayList<>());

		return dto;
	}
}