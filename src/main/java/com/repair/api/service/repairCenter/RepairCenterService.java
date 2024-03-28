package com.repair.api.service.repairCenter;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.repairCenter.RepairCenterDeleteRequestDto;
import com.repair.api.dto.request.repairCenter.RepairCenterUpdateRequestDto;
import com.repair.api.dto.request.repairCenter.RepairCenterWriteRequestDto;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import com.repair.api.dto.response.repairCenter.RepairCenterWriteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RepairCenterService {
    Page<RepairCenterListResponseDto> findAllCenterList(Pageable pageable);
    RepairCenterWriteResponseDto write(RepairCenterWriteRequestDto repairCenterWriteRequestDto);
    ResultResponseDto update(RepairCenterUpdateRequestDto repairCenterUpdateRequestDto);
    ResultResponseDto delete(RepairCenterDeleteRequestDto repairCenterDeleteRequestDto);
}
