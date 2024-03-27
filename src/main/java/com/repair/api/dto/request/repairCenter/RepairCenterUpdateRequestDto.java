package com.repair.api.dto.request.repairCenter;

import lombok.Getter;

@Getter
public class RepairCenterUpdateRequestDto {
    private String repairCenterId;
    private String userId;
    private String name;
    private String address;
    private String tell;
    private String type;
}
