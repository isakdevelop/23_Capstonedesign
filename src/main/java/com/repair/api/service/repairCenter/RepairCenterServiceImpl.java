package com.repair.api.service.repairCenter;

import static com.repair.api.domain.value.CarBrand.carBrand;

import com.repair.api.domain.RepairCenter;
import com.repair.api.dto.request.repairCenter.RepairCenterWriteRequestDto;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import com.repair.api.dto.response.repairCenter.RepairCenterWriteResponseDto;
import com.repair.api.repository.repairCenter.RepairCenterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepairCenterServiceImpl implements RepairCenterService{
    private final RepairCenterRepository repairCenterRepository;

    @Override
    public Page<RepairCenterListResponseDto> findAllCenterList(Pageable pageable) {
        return repairCenterRepository.findAllCenterList(pageable);
    }

    @Override
    public RepairCenterWriteResponseDto write(RepairCenterWriteRequestDto repairCenterWriteRequestDto) {
        RepairCenter repairCenter = RepairCenter.builder()
                .name(repairCenterWriteRequestDto.getName())
                .address(repairCenterWriteRequestDto.getAddress())
                .tell(repairCenterWriteRequestDto.getTell())
                .type(carBrand(repairCenterWriteRequestDto.getType()))
                .build();

        repairCenterRepository.save(repairCenter);
        return new RepairCenterWriteResponseDto(1, "입력 성공");
    }
}
