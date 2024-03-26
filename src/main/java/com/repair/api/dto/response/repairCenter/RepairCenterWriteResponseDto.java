package com.repair.api.dto.response.repairCenter;

import lombok.Getter;

@Getter
public class RepairCenterWriteResponseDto {
    private int status;
    private String message;

    public RepairCenterWriteResponseDto(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
