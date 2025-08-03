package com.nt.workforcemgmt.dto;


import lombok.Data;

@Data
public class CommentRequest {
    private String author;
    private String message;
}