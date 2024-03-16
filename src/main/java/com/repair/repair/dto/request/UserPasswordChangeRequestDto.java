package com.repair.repair.dto.request;

import lombok.Getter;

@Getter
public class UserPasswordChangeRequestDto {
    private String id;
    private String password;
    private String newPassword;
}
