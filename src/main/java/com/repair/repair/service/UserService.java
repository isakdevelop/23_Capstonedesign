package com.repair.repair.service;

import com.repair.repair.dto.request.UserSignupRequestDto;
import com.repair.repair.dto.response.UserSignupResponseDto;

public interface UserService {
    UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto);
}
