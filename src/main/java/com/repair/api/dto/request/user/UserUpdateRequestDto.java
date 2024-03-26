package com.repair.api.dto.request.user;

import lombok.Getter;

@Getter
public class UserUpdateRequestDto {
    private String id;
    private String name;
    private String password;
}
