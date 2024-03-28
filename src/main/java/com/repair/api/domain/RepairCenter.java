package com.repair.api.domain;

import com.repair.api.domain.value.CarBrand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "repair_center")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class RepairCenter extends BaseEntity {
    @Column(name = "repairCenter_name")
    private String name;
    private String address;
    private String tell;
    @Enumerated(value = EnumType.STRING)
    private CarBrand brand;

    public void updateRepairCenter(String name, String address, String tell, CarBrand type) {
        this.name = name;
        this.address = address;
        this.tell = tell;
        this.brand = type;
    }

}
