package com.repair.api.repository.repairCenter;

import com.repair.api.domain.RepairCenter;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepairCenterRepository extends JpaRepository<RepairCenter, String> {
    @Query("select new com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto(createAt, name, address, tell, type) from repair_center ")
    Page<RepairCenterListResponseDto> findAllCenterList(Pageable pageable);
}
