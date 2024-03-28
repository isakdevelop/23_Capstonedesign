package com.repair.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RepairExceptionModel {
    private int errorCode;
    private String errorMessage;
}
