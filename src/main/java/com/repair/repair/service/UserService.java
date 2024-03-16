package com.repair.repair.service;

import com.repair.repair.dto.request.UserLoginRequestDto;
import com.repair.repair.dto.request.UserPasswordChangeRequestDto;
import com.repair.repair.dto.request.UserSignupRequestDto;
import com.repair.repair.dto.response.UserLoginResponseDto;
import com.repair.repair.dto.response.UserPasswordChangeResponseDto;
import com.repair.repair.dto.response.UserSignupResponseDto;
import java.util.Optional;

public interface UserService {
    UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto);
    Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);
    Optional<UserPasswordChangeResponseDto> passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto);
}
