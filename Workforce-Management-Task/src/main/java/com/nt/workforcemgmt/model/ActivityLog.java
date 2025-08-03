package com.nt.workforcemgmt.model;


import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ActivityLog {
    private String description;
    private LocalDateTime timestamp;
}