package com.repair.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RepairException extends RuntimeException{
    private int status;
    private String message;
}
