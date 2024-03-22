package com.repair.repair.service.user;

import com.repair.repair.dto.request.user.UserDeleteRequestDto;
import com.repair.repair.dto.request.user.UserLoginRequestDto;
import com.repair.repair.dto.request.user.UserMailRequestDto;
import com.repair.repair.dto.request.user.UserPasswordChangeRequestDto;
import com.repair.repair.dto.request.user.UserSignupRequestDto;
import com.repair.repair.dto.request.user.UserUpdateRequestDto;
import com.repair.repair.dto.response.user.UserDeleteResponseDto;
import com.repair.repair.dto.response.user.UserLoginResponseDto;
import com.repair.repair.dto.response.user.UserMailResponseDto;
import com.repair.repair.dto.response.user.UserPasswordChangeResponseDto;
import com.repair.repair.dto.response.user.UserSignupResponseDto;
import com.repair.repair.dto.response.user.UserUpdateResponseDto;
import java.util.Optional;

public interface UserService {
    UserSignupResponseDto signUp(UserSignupRequestDto userSignupRequestDto);
    Optional<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequestDto);
    Optional<UserPasswordChangeResponseDto> passwordUpdate(UserPasswordChangeRequestDto userPasswordChangeRequestDto);
    Optional<UserUpdateResponseDto> updateUser(UserUpdateRequestDto userUpdateRequestDto);
    Optional<UserDeleteResponseDto> deleteUser(UserDeleteRequestDto userDeleteRequestDto);
    Optional<UserMailResponseDto> sendMail(UserMailRequestDto userMailRequestDto);
}
