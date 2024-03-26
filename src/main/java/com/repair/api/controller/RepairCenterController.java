package com.repair.api.controller;

import com.repair.api.domain.value.Error;
import com.repair.api.dto.request.repairCenter.RepairCenterWriteRequestDto;
import com.repair.api.dto.response.repairCenter.RepairCenterListResponseDto;
import com.repair.api.dto.response.repairCenter.RepairCenterWriteResponseDto;
import com.repair.api.exception.RepairException;
import com.repair.api.service.repairCenter.RepairCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/repair_center")
public class RepairCenterController {
    private final RepairCenterService repairCenterService;

    @PostMapping("/write")
    public RepairCenterWriteResponseDto write(@RequestBody RepairCenterWriteRequestDto repairCenterWriteRequestDto) {
        String type = repairCenterWriteRequestDto.getType();
        if ("기아".equals(type) || "현대".equals(type)) {
            return repairCenterService.write(repairCenterWriteRequestDto);
        }
        throw new RepairException(Error.BRAND_NOT_FOUND.getStatus(), Error.BRAND_NOT_FOUND.getMessage());
    }



    @GetMapping("/list")
    public Page<RepairCenterListResponseDto> list(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return repairCenterService.findAllCenterList(pageable);
    }
}
