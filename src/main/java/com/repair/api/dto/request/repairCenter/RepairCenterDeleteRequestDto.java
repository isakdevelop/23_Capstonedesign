package com.repair.api.dto.request.repairCenter;

import lombok.Getter;

@Getter
public class RepairCenterDeleteRequestDto {
    private Long repairCenterId;
    private Long userId;
}
