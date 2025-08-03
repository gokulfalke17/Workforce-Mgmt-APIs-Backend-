package com.nt.workforcemgmt.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private String author;
    private String message;
    private LocalDateTime timestamp;
}