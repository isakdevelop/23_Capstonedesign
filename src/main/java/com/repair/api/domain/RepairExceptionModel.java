package com.repair.api.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RepairExceptionModel {
    private int status;
    private String message;
}
