package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {
    private Long userId;
    private String password;
    private String newPassword;
}
