package com.repair.api.service.user;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.user.UserDeleteRequestDto;
import com.repair.api.dto.request.user.UserLoginRequestDto;
import com.repair.api.dto.request.user.UserMailRequestDto;
import com.repair.api.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.api.dto.request.user.UserSignupRequestDto;
import com.repair.api.dto.request.user.UserUpdateRequestDto;
import com.repair.api.dto.response.user.UserLoginResponseDto;
import java.util.Optional;

public interface UserService {
    ResultResponseDto signUp(UserSignupRequestDto userSignupRequestDto);
    Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);
    ResultResponseDto passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto);
    ResultResponseDto updateUser(UserUpdateRequestDto userUpdateRequestDto);
    ResultResponseDto deleteUser(UserDeleteRequestDto userDeleteRequestDto);
    ResultResponseDto sendMail(UserMailRequestDto userMailRequestDto);
}
