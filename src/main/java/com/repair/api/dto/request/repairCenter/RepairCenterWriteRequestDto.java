package com.repair.api.dto.request.repairCenter;

import lombok.Getter;

@Getter
public class RepairCenterWriteRequestDto {
    private Long userId;
    private String name;
    private String address;
    private String tell;
    private String type;
}
