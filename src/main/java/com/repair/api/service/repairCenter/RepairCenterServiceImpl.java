package com.repair.api.service.repairCenter;

import static com.repair.api.domain.value.CarBrand.carBrand;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.domain.RepairCenter;
import com.repair.api.domain.User;
import com.repair.api.domain.value.Error;
import com.repair.api.domain.value.Role;
import com.repair.api.dto.request.repairCenter.RepairCenterDeleteRequestDto;
import com.repair.api.dto.request.repairCenter.RepairCenterUpdateRequestDto;
import com.repair.api.dto.request.repairCenter.RepairCenterWriteRequestDto;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import com.repair.api.dto.response.repairCenter.RepairCenterWriteResponseDto;
import com.repair.api.exception.RepairException;
import com.repair.api.repository.repairCenter.RepairCenterRepository;
import com.repair.api.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RepairCenterServiceImpl implements RepairCenterService{
    private final UserRepository userRepository;
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
                .brand(carBrand(repairCenterWriteRequestDto.getType()))
                .build();

        repairCenterRepository.save(repairCenter);
        return new RepairCenterWriteResponseDto(200, "입력 성공");
    }

    @Override
    @Transactional
    public ResultResponseDto update(RepairCenterUpdateRequestDto repairCenterUpdateRequestDto) {
        RepairCenter updateRepairCenter =
                getRepairCenterOrThrow(repairCenterUpdateRequestDto.getRepairCenterId(), repairCenterUpdateRequestDto.getUserId());

        updateRepairCenter.updateRepairCenter(repairCenterUpdateRequestDto.getName(),
                repairCenterUpdateRequestDto.getAddress(), repairCenterUpdateRequestDto.getTell(),
                carBrand(repairCenterUpdateRequestDto.getType()));

        return new ResultResponseDto(200, "수리점 정보에 대한 수정이 완료되었습니다.");
    }

    @Override
    public ResultResponseDto delete(RepairCenterDeleteRequestDto repairCenterDeleteRequestDto) {
        RepairCenter deleteRepairCenter = getRepairCenterOrThrow(repairCenterDeleteRequestDto.getRepairCenterId(),
                repairCenterDeleteRequestDto.getUserId());

        repairCenterRepository.deleteById(deleteRepairCenter.getId());

        return new ResultResponseDto(200, "수리점 정보에 대한 삭제가 완료되었습니다.");
    }

    private RepairCenter getRepairCenterOrThrow(Long repairCenterId, Long userId) {
        Optional<User> adminUser = userRepository.findById(userId);
        System.out.println(adminUser.get().getRole());

        if (adminUser.get().getRole().equals("ROLE_USER")) {
            throw new RepairException(Error.ADMIN_FORBIDDEN.getStatus(), Error.ADMIN_FORBIDDEN.getMessage());
        }

        Optional<RepairCenter> repairCenter = repairCenterRepository.findById(repairCenterId);
        if (repairCenter.isEmpty()) {
            throw new RepairException(Error.REPAIR_CENTER_NOT_FOUND.getStatus(), Error.REPAIR_CENTER_NOT_FOUND.getMessage());
        }

        return repairCenter.get();
    }

}
