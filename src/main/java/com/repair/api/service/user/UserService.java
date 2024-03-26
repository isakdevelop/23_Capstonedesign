package com.repair.api.service.user;

import com.repair.api.dto.request.user.UserDeleteRequestDto;
import com.repair.api.dto.request.user.UserLoginRequestDto;
import com.repair.api.dto.request.user.UserMailRequestDto;
import com.repair.api.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.api.dto.request.user.UserSignupRequestDto;
import com.repair.api.dto.request.user.UserUpdateRequestDto;
import com.repair.api.dto.response.user.UserDeleteResponseDto;
import com.repair.api.dto.response.user.UserLoginResponseDto;
import com.repair.api.dto.response.user.UserMailResponseDto;
import com.repair.api.dto.response.user.UserPasswordChangeResponseDto;
import com.repair.api.dto.response.user.UserSignupResponseDto;
import com.repair.api.dto.response.user.UserUpdateResponseDto;
import java.util.Optional;

public interface UserService {
    UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto);
    Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);
    Optional<UserPasswordChangeResponseDto> passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto);
    Optional<UserUpdateResponseDto> updateUser(UserUpdateRequestDto userUpdateRequestDto);
    Optional<UserDeleteResponseDto> deleteUser(UserDeleteRequestDto userDeleteRequestDto);
    Optional<UserMailResponseDto> sendMail(UserMailRequestDto userMailRequestDto);
}
