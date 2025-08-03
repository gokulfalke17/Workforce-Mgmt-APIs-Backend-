package com.nt.workforcemgmt.dto;

import com.nt.workforcemgmt.model.TaskPriority;

import lombok.Data;

@Data
public class PriorityUpdateRequest {
    private TaskPriority priority;
}