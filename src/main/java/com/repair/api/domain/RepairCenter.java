package com.repair.api.domain;

import com.repair.api.domain.value.CarBrand;
import com.repair.api.domain.value.Domain;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

@Entity(name = "repair_center")
@Getter
public class RepairCenter extends BaseEntity implements Persistable<String> {
    private String name;
    private String address;
    private String tell;
    @Enumerated(value = EnumType.STRING)
    private CarBrand type;

    protected RepairCenter()    {
        super(Domain.REPAIR_CENTER);
    }

    @Builder
    protected RepairCenter(String name, String address, String tell, CarBrand type) {
        this();
        this.name = name;
        this.address = address;
        this.tell = tell;
        this.type = type;
    }


    @Override
    public boolean isNew() {
        return getCreateAt() == null;
    }
}
