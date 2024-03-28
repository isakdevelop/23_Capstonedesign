package com.repair.api.exception;

import com.repair.api.exception.RepairExceptionModel;
import com.repair.api.exception.RepairException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RepairException.class)
    public final ResponseEntity<RepairExceptionModel> handleRepairException(RepairException repairException) {
        RepairExceptionModel exceptionModel = new RepairExceptionModel(
                repairException.getStatus(),
                repairException.getMessage()
        );
        return new ResponseEntity<>(exceptionModel, HttpStatus.valueOf(repairException.getStatus()));
    }
}
