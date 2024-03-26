package com.repair.api.domain.value;

import lombok.Getter;

@Getter
public enum CarBrand {
    KIA("기아"),
    HYUNDAI("현대"),
    ERROR("잘못된 값");

    private final String koreanName;

    CarBrand(String koreanName) {
        this.koreanName = koreanName;
    }

    public static CarBrand carBrand(String type) {
        for (CarBrand brand : CarBrand.values()) {
            if (brand.koreanName.equals(type)) {
                return brand;
            }
        }
        return ERROR;
    }
}