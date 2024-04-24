package com.example.truyenapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APIResponse <T> {
    private Integer code;
    private String message;
    private T result;
}
