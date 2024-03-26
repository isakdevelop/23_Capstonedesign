package com.repair.api.service.repairCenter;

import com.repair.api.dto.request.repairCenter.RepairCenterWriteRequestDto;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import com.repair.api.dto.response.repairCenter.RepairCenterWriteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepairCenterService {
    Page<RepairCenterListResponseDto> findAllCenterList(Pageable pageable);

    RepairCenterWriteResponseDto write(RepairCenterWriteRequestDto repairCenterWriteRequestDto);
}
