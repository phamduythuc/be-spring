package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {
    private String message;
    private int status;
    private T data;

    public ResponseDTO(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
