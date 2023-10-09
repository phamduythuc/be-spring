package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private String message;
    private int status;
    private T data;
}
