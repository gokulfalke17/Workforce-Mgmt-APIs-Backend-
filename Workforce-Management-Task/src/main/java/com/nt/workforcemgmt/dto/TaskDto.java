package com.nt.workforcemgmt.dto;

import java.time.LocalDate;
import java.util.List;

import com.nt.workforcemgmt.model.TaskPriority;
import com.nt.workforcemgmt.model.TaskStatus;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String assignedTo;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskStatus status;
    private TaskPriority priority;
    private List<String> comments;
    private List<String> activityLogs;
}