package com.repair.api.dto.response.user;

import com.repair.api.domain.value.Role;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Long userId;
    private String email;
    private String name;
    private String type;

    public UserLoginResponseDto(Long id, String email, String name, String type) {
        this.userId = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }
}
