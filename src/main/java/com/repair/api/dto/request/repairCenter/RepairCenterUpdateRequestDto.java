package com.repair.api.dto.request.repairCenter;

import lombok.Getter;

@Getter
public class RepairCenterUpdateRequestDto {
    private Long repairCenterId;
    private Long userId;
    private String name;
    private String address;
    private String tell;
    private String type;
}
