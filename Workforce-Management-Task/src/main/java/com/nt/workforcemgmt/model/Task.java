package com.nt.workforcemgmt.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Task {
    private Long id;
    private String title;
    private String assignedTo;
    private LocalDate startDate;
    private LocalDate dueDate;
    private TaskStatus status = TaskStatus.ACTIVE;
    private TaskPriority priority = TaskPriority.MEDIUM;
    private List<Comment> comments = new ArrayList<>();
    private List<ActivityLog> activityLogs = new ArrayList<>();
}