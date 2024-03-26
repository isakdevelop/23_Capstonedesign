package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {
    private String id;
    private String password;
    private String newPassword;
}
