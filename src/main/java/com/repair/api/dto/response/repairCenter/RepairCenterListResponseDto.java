package com.repair.api.dto.response.repairCenter;

import com.repair.api.domain.value.CarBrand;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RepairCenterListResponseDto {
    private LocalDateTime creatAt;
    private String centerName;
    private String address;
    private String tell;
    private CarBrand type;

    public RepairCenterListResponseDto(LocalDateTime creatAt, String centerName, String address, String tell, CarBrand type) {
        this.creatAt = creatAt;
        this.centerName = centerName;
        this.address = address;
        this.tell = tell;
        this.type = type;
    }
}
